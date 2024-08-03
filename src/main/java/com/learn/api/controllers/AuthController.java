package com.learn.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.learn.api.models.authModel.UserModel;
import com.learn.api.service.authService.authService;
import com.learn.api.dto.RoleDTO;
import com.learn.api.dto.authDto.LoginRequest;
import com.learn.api.dto.authDto.UserDTO;
import com.learn.api.dto.authDto.UserDTORequestCreate;
import com.learn.api.dto.authDto.UserDataWithToken;
import com.learn.api.jwt.JwtUtil;
import com.learn.api.dto.ResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private authService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {

            UserModel user = userService.findByEmail(loginRequest.getEmail());

            if (user == null) {
                // logger.error("User not found for email: {}", loginRequest.getEmail());
                // return ResponseEntity.status(404).body("User not found");
                System.out.println("User not found for email: " + loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseWrapper<>(404, "Email not found", ""));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("User retrieved: " + user);

            // logger.error("User not found");

            String token = jwtUtil.generateToken(loginRequest.getEmail());
            RoleDTO roleDTO = new RoleDTO(user.getRole().getId(), user.getRole().getRoleName(),
                    user.getRole().getDescriptionRole());
            // UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(),
            // roleDTO);

            UserDTO userDTO = new UserDTO(user.getUserId(), roleDTO, user.getFName(), user.getLName(), user.getEmail());

            // logger.debug("Authentication successful for user: {}",
            // loginRequest.getEmail());

            UserDataWithToken userDataWithToken = (new UserDataWithToken<>(token, userDTO));

            return ResponseEntity.ok(new ResponseWrapper<>(200, "Response successfully", userDataWithToken));
        } catch (UsernameNotFoundException ex) {
            // logger.error("Invalid email or password", ex);
            return ResponseEntity.status(404).body("Invalid email or password");
        } catch (AuthenticationServiceException ex) {
            // logger.error("Invalid email or password", ex);
            return ResponseEntity.status(401).body("Invalid email or password");
        } catch (Exception ex) {
            ex.printStackTrace(); // Add this to see the actual error in the logs
            // // logger.error("An error occurred during authentication", ex);
            // return ResponseEntity.status(500).body("An error occurred during
            // authentication");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "An error occurred during authentication", null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTORequestCreate request) {
        try {
            // Check if user with the given email already exists
            UserModel existingUser = userService.findByEmail(request.getEmail());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ResponseWrapper<>(409, "Email already in use", ""));
            }

            // Proceed with user creation
            UserModel createdUser = userService.createUserWithRole(request);
            if (createdUser != null) {
                RoleDTO roleDTO = new RoleDTO(createdUser.getRole().getId(), createdUser.getRole().getRoleName(),
                        createdUser.getRole().getDescriptionRole());
                // RoleDTO roleDTO = new RoleDTO(null, null, null)
                // UserDTO userDTO = new UserDTO(createdUser.getUserId(), createdUser.getName(),
                // createdUser.getEmail(),
                // roleDTO);
                UserDTO userDTO = new UserDTO(createdUser.getUserId(), roleDTO, createdUser.getFName(),
                        createdUser.getLName(), createdUser.getEmail());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseWrapper<>(201, "User registered successfully", userDTO));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseWrapper<>(404, "Role not found", null));
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Add this to see the actual error in the logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "An error occurred during registration", null));
        }
    }

}
