package com.example.crudapp.dto;

public class AuthResponse {
    private String token;

    // No-arg constructor (required by Spring)
    public AuthResponse() {}

    // Constructor that takes token
    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}