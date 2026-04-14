package com.example.crudapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    void testNoArgsConstructor() {
        AuthRequest req = new AuthRequest();

        assertNull(req.getEmail());
        assertNull(req.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        AuthRequest req = new AuthRequest("test@gmail.com", "123");

        assertEquals("test@gmail.com", req.getEmail());
        assertEquals("123", req.getPassword());
    }

    @Test
    void testSetters() {
        AuthRequest req = new AuthRequest();

        req.setEmail("abc@gmail.com");
        req.setPassword("pass");

        assertEquals("abc@gmail.com", req.getEmail());
        assertEquals("pass", req.getPassword());
    }
}