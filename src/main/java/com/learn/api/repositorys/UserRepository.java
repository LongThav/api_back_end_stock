package com.learn.api.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.authModel.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}