package com.example.crudapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific exception
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: " + ex.getMessage());
    }

    // Handle all exceptions (log properly)
    @ExceptionHandler(Exception.class)
    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<?> handleAll(Exception ex) {

        ex.printStackTrace(); // 🔥 VERY IMPORTANT (see real error in console)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + ex.getMessage());
    }
}