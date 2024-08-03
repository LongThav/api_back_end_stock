package com.learn.api.dto.authDto;

import com.learn.api.dto.RoleDTO;

public class UserDTO {
    private Long user_id;

    private String f_name;

    private String l_name;

    private String email;

    // private String password; // Add this field

    private RoleDTO role;

    // Default constructor
    public UserDTO(Long userId, RoleDTO roleDTO, String fName, String lName, String email) {
        this.user_id = userId;
        this.role = roleDTO;
        this.l_name = lName;
        this.f_name = fName;
        this.email = email;
    }

    // Getters and Setters
    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO roleDTO) {
        this.role = roleDTO;
    }

    public String getFName() {
        return f_name;
    }

    public void setFName(String fName) {
        this.f_name = fName;
    }


    public String getLName() {
        return l_name;
    }

    public void setLName(String LName) {
        this.l_name = LName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }
}
