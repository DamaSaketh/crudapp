package com.example.crudapp.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        UserNotFoundException ex =
                new UserNotFoundException(1L);

        assertEquals("User not found with id: 1", ex.getMessage());
    }

    @Test
    void testThrowException() {
        assertThrows(UserNotFoundException.class, () -> {
            throw new UserNotFoundException(99L);
        });
    }
}