package com.learn.api.models.AuthModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    private String role_name;

    private String description;

    // Constructors
    public RoleModel() {
    }

    // Getters and Setters
    public Long getId() {
        return role_id;
    }

    public void setRoleId(Long roleId) {
        this.role_id = roleId;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String roleName) {
        this.role_name = roleName;
    }

    public void setDescriptionRole(String descriptionRole) {
        this.description = descriptionRole;
    }

    public String getDescriptionRole() {
        return description;
    }
}
