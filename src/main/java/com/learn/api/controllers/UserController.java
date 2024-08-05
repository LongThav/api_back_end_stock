package com.learn.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.api.dto.ResponseWrapper;
import com.learn.api.dto.RoleDTO;
import com.learn.api.dto.authDto.AuthRespone;
import com.learn.api.dto.authDto.UserDTO;
import com.learn.api.dto.authDto.UserDTORequestCreate;
import com.learn.api.models.authModel.RoleModel;
import com.learn.api.models.authModel.UserModel;
import com.learn.api.service.authService.authService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private authService userService;

    public UserController(authService userService) {
        this.userService = userService;
    }

    private boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    @PostMapping
    public ResponseEntity<?> createUserWithRole(@RequestBody UserDTORequestCreate request, HttpServletRequest httpRequest) {
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        
        UserModel createdUser = userService.createUserWithRole(request);
        if (createdUser != null) {
            return ResponseEntity.ok(convertToDTO(createdUser));
        } else {
            return ResponseEntity.notFound().build(); // Handle the case when the role is not found or user creation fails
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpRequest) {
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response successfully", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, HttpServletRequest httpRequest) {
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, HttpServletRequest httpRequest) {
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }
        
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return new ResponseEntity<>(new AuthRespone(true, "User deleted successfully"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AuthRespone(false, "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    // @PatchMapping("/{id}/password")
    // public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody String password, HttpServletRequest httpRequest) {
    //     if (isTokenMissing(httpRequest)) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
    //     }
        
    //     UserModel updatedUser = userService.updatePassword(id, password);
    //     if (updatedUser != null) {
    //         // return ResponseEntity.ok(new UserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), null));
    //         return ResponseEntity.ok(new UserDTO(updatedUser.getUserId(), null, null, null, null));
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @PatchMapping("/{id}")
    // public ResponseEntity<?> updateUserPartial(@PathVariable Long id, @RequestBody UserModel user, HttpServletRequest httpRequest) {
    //     if (isTokenMissing(httpRequest)) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
    //     }
        
    //     UserModel updatedUser = userService.updateUserPartial(id, user);
    //     if (updatedUser != null) {
    //         return ResponseEntity.ok(new UserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), null));
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    private UserDTO convertToDTO(UserModel user) {
        RoleModel role = user.getRole();
        RoleDTO roleDTO = null;
        if (role != null) {
            roleDTO = new RoleDTO(role.getId(), role.getRoleName(), role.getDescriptionRole());
        }
        // return new UserDTO(user.getId(), user.getName(), user.getEmail(), roleDTO);
        return new UserDTO(user.getUserId(), roleDTO, user.getFName(), user.getLName(), user.getEmail(), user.getImage());
    }
}
