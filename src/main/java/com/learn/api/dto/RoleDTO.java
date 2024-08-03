package com.learn.api.dto;

public class RoleDTO {
    private Long role_id;

    private String role_name;

    private String description;

    // Constructors
    public RoleDTO(Long roleId, String roleName, String roleDescription) {
        this.role_id = roleId;
        this.role_name = roleName;
        this.description = roleDescription;
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
