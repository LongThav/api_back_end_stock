package com.learn.api.controllers;

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

import com.learn.api.service.authService.UserProfileService;
import com.learn.api.service.authService.authService;

import jakarta.servlet.http.HttpServletRequest;

import com.learn.api.dto.RoleDTO;
import com.learn.api.dto.authDto.AuthRespone;
import com.learn.api.dto.authDto.LoginRequest;
import com.learn.api.dto.authDto.UserDTO;
import com.learn.api.dto.authDto.UserDTORequestCreate;
import com.learn.api.dto.authDto.UserDataWithToken;
import com.learn.api.jwt.JwtUtil;
import com.learn.api.models.AuthModel.UserModel;
import com.learn.api.dto.ResponseWrapper;

@RestController
@RequestMapping("api/item")
@CrossOrigin(origins = "http://localhost:3000")

public class Test {

    @Autowired
    private authService userService;


    private boolean isTokenMissing(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(HttpServletRequest httpRequest) {
        if (isTokenMissing(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthRespone(false, "User not authorized"));
        }

        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponseWrapper<>(200, "Response successfully", users));
    }
}
