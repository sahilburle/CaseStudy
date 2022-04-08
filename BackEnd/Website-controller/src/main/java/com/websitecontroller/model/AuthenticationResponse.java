package com.websitecontroller.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private final String role;
    private final UserDetails userId;

    public AuthenticationResponse(String jwt, String role, UserDetails userId) {
        this.jwt = jwt;
        this.role = role;
        this.userId = userId;
    }
}