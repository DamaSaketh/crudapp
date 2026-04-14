package com.example.crudapp.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testLoginRequest() {
        LoginRequest req = new LoginRequest();

        req.setEmail("test@mail.com");
        req.setPassword("1234");

        assertEquals("test@mail.com", req.getEmail());
        assertEquals("1234", req.getPassword());
    }
}
