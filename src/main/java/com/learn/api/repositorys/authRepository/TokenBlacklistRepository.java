package com.learn.api.repositorys.authRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.models.AuthModel.TokenBlackListModel;

// @Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlackListModel, String> {
    boolean existsByToken(String token);
}
