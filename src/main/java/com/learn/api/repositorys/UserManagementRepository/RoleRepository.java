package com.learn.api.repositorys.UserManagementRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.AuthModel.RoleModel;


public interface RoleRepository extends JpaRepository<RoleModel, Long> {

}