package com.example.crudapp.service;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private final UserRepository repo = mock(UserRepository.class);
    private final CustomUserDetailsService service = new CustomUserDetailsService(repo);

    @Test
    void testLoadUserSuccess() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("encodedPassword");

        when(repo.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(user));

        UserDetails result = service.loadUserByUsername("test@mail.com");

        assertEquals("test@mail.com", result.getUsername());
    }

    @Test
    void testLoadUserNotFound() {
        when(repo.findByEmail("wrong@mail.com"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.loadUserByUsername("wrong@mail.com"));
    }
}