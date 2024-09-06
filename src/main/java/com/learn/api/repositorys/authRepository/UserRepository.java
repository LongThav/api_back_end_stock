package com.learn.api.repositorys.AuthRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.AuthModel.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}