package com.example.crudapp.controller;

public class JwtResponse {
    @SuppressWarnings("FieldMayBeFinal")
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
}
