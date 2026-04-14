package com.example.crudapp.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleRuntime() {
        RuntimeException ex = new RuntimeException("Bad request");

        ResponseEntity<?> response = handler.handleRuntime(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error: Bad request", response.getBody());
    }

    @Test
    void testHandleAll() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<?> response = handler.handleAll(ex);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Internal Server Error: Something went wrong", response.getBody());
    }

    @Test
    void testConstructorCoverage() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        assertNotNull(handler);
    }
}
