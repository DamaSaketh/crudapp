package com.example.crudapp.dto;

import org.junit.jupiter.api.Test;

import com.example.crudapp.controller.JwtResponse;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseTest {

    @Test
    void testNoArgsConstructor() {
        AuthResponse res = new AuthResponse();

        assertNull(res.getToken());
    }

    @Test
    void testAllArgsConstructor() {
        AuthResponse res = new AuthResponse("token123");

        assertEquals("token123", res.getToken());
    }

    @Test
    void testSetterAndGetter() {
        AuthResponse res = new AuthResponse();

        res.setToken("newToken");

        assertEquals("newToken", res.getToken());
    }
    @Test
void testJwtResponse() {
    JwtResponse response = new JwtResponse("token123");
    assertEquals("token123", response.getToken());
}
}
