package com.learn.api.service.authService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.api.dto.authDto.UserDTO;
import com.learn.api.jwt.JwtUtil;

@Service
public class UserProfileService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private authService userService; // Your service to interact with the user repository

    public UserDTO getUserProfile(String token) {
        // Extract the username (email) from the token
        String username = jwtUtil.extractUsername(token);

        // Retrieve user profile using the extracted email
        return userService.getUserByEmail(username);
    }
}