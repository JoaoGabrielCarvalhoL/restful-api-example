package br.com.carv.restful.model.dto.response;

import java.time.LocalDateTime;
import java.util.Date;

public class UserResponse {

    private String username;
    private Boolean isAuthenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public UserResponse() {
    }

    public UserResponse(String username, Boolean isAuthenticated, Date created, Date expiration, String accessToken, String refreshToken) {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
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
