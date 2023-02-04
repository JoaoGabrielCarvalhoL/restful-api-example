package br.com.carv.restful.model.dto.response;

import java.time.LocalDateTime;

public class UserResponse {

    private String username;
    private Boolean isAuthenticated;
    private LocalDateTime created;
    private LocalDateTime expiration;
    private String accessToken;
    private String refreshToken;

    public UserResponse() {
    }

    public UserResponse(String username, Boolean isAuthenticated, LocalDateTime created, LocalDateTime expiration, String accessToken, String refreshToken) {
        this.username = username;
        this.isAuthenticated = isAuthenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
