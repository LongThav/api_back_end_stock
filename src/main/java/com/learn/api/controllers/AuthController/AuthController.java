package com.learn.api.controllers.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import jakarta.servlet.http.HttpServletRequest;

import com.learn.api.dto.AuthDto.AuthRespone;
import com.learn.api.dto.AuthDto.LoginRequest;
import com.learn.api.dto.AuthDto.UserDTO;
import com.learn.api.dto.AuthDto.UserDTORequestCreate;
import com.learn.api.dto.AuthDto.UserDataWithToken;
import com.learn.api.dto.RoleDTO.RoleDTO;
import com.learn.api.jwt.JwtUtil;
import com.learn.api.models.AuthModel.UserModel;
import com.learn.api.service.AuthService.UserProfileService;
import com.learn.api.service.AuthService.authService;
import com.learn.api.dto.ResponseWrapper;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private authService userService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JwtUtil jwtUtil;

    private boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<UserDataWithToken>> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest) {
        // No need to manually check if email and password are null or empty, @Valid
        // will handle this
        try {
            // Find user by email
            UserModel user = userService.findByEmail(loginRequest.getEmail());
            if (user == null) {
                // Return 404 if user not found
                logger.info("User not found for email: {}", loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseWrapper<>(404, "Email not found", null));
            }

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtil.generateToken(loginRequest.getEmail());

            // Create role and user DTOs
            RoleDTO roleDTO = new RoleDTO(user.getRole().getId(), user.getRole().getRoleName(),
                    user.getRole().getDescriptionRole());
            UserDTO userDTO = new UserDTO(user.getUserId(), roleDTO, user.getFName(), user.getLName(), user.getEmail(), user.getImage());

            // Create user data with token
            UserDataWithToken userDataWithToken = new UserDataWithToken<>(token, userDTO);

            // Return success response
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Login successful", userDataWithToken));

        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            // Return 401 for invalid email or password
            logger.error("Authentication failed: ", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseWrapper<>(401, "Invalid email or password", null));
        } catch (Exception ex) {
            // Log the exception and return 500 for internal server error
            logger.error("Internal server error: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "An error occurred during authentication", null));
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpRequest) {
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }

        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response successfully", users));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<?>> registerUser(@Valid @RequestBody UserDTORequestCreate request,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            ResponseWrapper<Map<String, String>> response = new ResponseWrapper<>(HttpStatus.BAD_REQUEST.value(),
                    "Validation failed", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            UserModel existingUser = userService.findByEmail(request.getEmail());
            if (existingUser != null) {
                ResponseWrapper<String> response = new ResponseWrapper<>(HttpStatus.CONFLICT.value(),
                        "Email already in use", null);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            UserModel createdUser = userService.createUserWithRole(request);
            if (createdUser != null) {
                RoleDTO roleDTO = new RoleDTO(createdUser.getRole().getId(), createdUser.getRole().getRoleName(),
                        createdUser.getRole().getDescriptionRole());
                UserDTO userDTO = new UserDTO(createdUser.getUserId(), roleDTO, createdUser.getFName(),
                        createdUser.getLName(), createdUser.getEmail(), createdUser.getImage());
                ResponseWrapper<UserDTO> response = new ResponseWrapper<>(HttpStatus.CREATED.value(),
                        "User registered successfully", userDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                ResponseWrapper<String> response = new ResponseWrapper<>(HttpStatus.NOT_FOUND.value(), "Role not found",
                        null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Validation Error: {}", e.getMessage());
            ResponseWrapper<String> response = new ResponseWrapper<>(HttpStatus.BAD_REQUEST.value(),
                    "Validation failed", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e); // Log the full stack trace
            ResponseWrapper<String> response = new ResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An unexpected error occurred", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseWrapper<UserDTO>> getProfile(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix if present
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        try {
            // Get user profile using the token
            UserDTO userProfile = userProfileService.getUserProfile(jwtToken);

            if (userProfile == null) {
                // Return 404 if the user profile is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseWrapper<>(404, "User profile not found", null));
            }

            // Return success response with user profile
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Profile fetched successfully", userProfile));
        } catch (Exception e) {
            // Log and handle the exception
            e.printStackTrace(); // Make sure to log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "An error occurred while fetching the profile", null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseWrapper<String>> logout(@RequestHeader("Authorization") String token) {
        try {
            // Remove "Bearer " prefix if present
            String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

            // Call service to blacklist the token
            // authService.blacklistToken(jwtToken);
            
            userService.blacklistToken(jwtToken);

            // Return success response
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Logout successful", "Token has been blacklisted"));
        } catch (Exception e) {
            // Log and handle the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "An error occurred during logout", null));
        }
    }

}
