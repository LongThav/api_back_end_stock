package com.learn.api.models.authModel;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_token_black_list")

public class TokenBlackListModel {
    @Id
    private String token; // Token value

    private Instant  expiration; // Token expiration date

    public TokenBlackListModel(String token, Instant  expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant  getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant  expiration) {
        this.expiration = expiration;
    }
}
