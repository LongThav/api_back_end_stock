package com.learn.api.dto.authDto;

public class UserDTORequestCreate {
    private String f_name;
    private String l_name;
    private String email;
    private String password;
    private long roleId;

    // Default constructor
    public UserDTORequestCreate() {
    }

    // Getters and Setters
    public String getFName() {
        return f_name;
    }

    public void setFName(String fName) {
        this.f_name = fName;
    }

    public String getLName() {
        return l_name;
    }

    public void setLName(String lName) {
        this.l_name = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
