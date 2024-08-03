package com.learn.api.models.authModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String f_name;

    private String l_name;

    private String email;

    private String password; // Field for storing hashed password

    @ManyToOne
    @JoinColumn(name = "role_id") // Foreign key column in tbl_user table
    private RoleModel role;

    // Default constructor
    public UserModel() {
    }

    // Getters and Setters
    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
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

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id=" + user_id +
                ", fName='" + f_name + '\'' +
                ", lName='" + l_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
