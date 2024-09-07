package com.learn.api.dto.AuthDto;

import com.learn.api.dto.RoleDTO;

public class UserDTO {
    private Long user_id;

    private String f_name;

    private String l_name;

    private String email;
    private String image;

    // private String password; // Add this field

    private RoleDTO role;

    // Default constructor
    public UserDTO(Long userId, RoleDTO roleDTO, String f_name, String l_name, String email, String image) {
        this.user_id = userId;
        this.role = roleDTO;
        this.l_name = l_name;
        this.f_name = f_name;
        this.email = email;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFName() {
        return f_name;
    }

    public void setFName(String f_name) {
        this.f_name = f_name;
    }


    public String getLName() {
        return l_name;
    }

    public void setLName(String l_name) {
        this.l_name = l_name;
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
