// package com.example.crudapp.security;

// import com.example.crudapp.service.CustomUserDetailsService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mockito;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import java.util.ArrayList;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class JwtFilterTest {

//     private JwtFilter jwtFilter;
//     private JwtUtil jwtUtil;
//     private CustomUserDetailsService customUserDetailsService;
// @InjectMocks
// private JwtFilter jwtFilter;
//     @BeforeEach
//     void setUp() {
//         jwtUtil = mock(JwtUtil.class);
//         customUserDetailsService = mock(CustomUserDetailsService.class);
//         jwtFilter = new JwtFilter(jwtUtil, customUserDetailsService);
//     }

//     @Test
//     void testDoFilterInternalWithValidToken() throws Exception {
//         // Setup
//         String token = "validToken";
//         String username = "testUser";
//         String authHeader = "Bearer " + token;

//         HttpServletRequest request = mock(HttpServletRequest.class);
//         HttpServletResponse response = mock(HttpServletResponse.class);
//         FilterChain filterChain = mock(FilterChain.class);

//         // Mock the request header
//         when(request.getHeader("Authorization")).thenReturn(authHeader);

//         UserDetails userDetails = new User(username, "password", new ArrayList<>());
//         when(jwtUtil.extractUsername(token)).thenReturn(username);
//         when(jwtUtil.validateToken(token, userDetails)).thenReturn(true);
//         when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

//         // Execute
//         jwtFilter.doFilterInternal(request, response, filterChain);

//         // Verify that the filter chain was executed and the context was set
//         verify(filterChain).doFilter(request, response);
//         verify(customUserDetailsService).loadUserByUsername(username);
//         verify(jwtUtil).validateToken(token, userDetails);
//         verify(SecurityContextHolder.getContext().getAuthentication()).isAuthenticated();
//     }

//     @Test
//     void testDoFilterInternalWithInvalidToken() throws Exception {
//         // Setup
//         String token = "invalidToken";
//         String username = "testUser";
//         String authHeader = "Bearer " + token;

//         HttpServletRequest request = mock(HttpServletRequest.class);
//         HttpServletResponse response = mock(HttpServletResponse.class);
//         FilterChain filterChain = mock(FilterChain.class);

//         // Mock the request header
//         when(request.getHeader("Authorization")).thenReturn(authHeader);

//         UserDetails userDetails = new User(username, "password", new ArrayList<>());
//         when(jwtUtil.extractUsername(token)).thenReturn(username);
//         when(jwtUtil.validateToken(token, userDetails)).thenReturn(false);
//         when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

//         // Execute
//         jwtFilter.doFilterInternal(request, response, filterChain);

//         // Verify that the filter chain was executed
//         verify(filterChain).doFilter(request, response);
//         verify(customUserDetailsService).loadUserByUsername(username);
//         verify(jwtUtil).validateToken(token, userDetails);
//         verify(SecurityContextHolder.getContext().getAuthentication(), never()).setAuthenticated(true);
//     }

//     @Test
//     void testDoFilterInternalWithNoAuthHeader() throws Exception {
//         // Setup
//         HttpServletRequest request = mock(HttpServletRequest.class);
//         HttpServletResponse response = mock(HttpServletResponse.class);
//         FilterChain filterChain = mock(FilterChain.class);

//         // Mock the request header
//         when(request.getHeader("Authorization")).thenReturn(null);

//         // Execute
//         jwtFilter.doFilterInternal(request, response, filterChain);

//         // Verify that the filter chain was executed without any security context changes
//         verify(filterChain).doFilter(request, response);
//         verify(SecurityContextHolder.getContext().getAuthentication(), never()).setAuthenticated(true);
//     }
// }


package com.example.crudapp.security;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.crudapp.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class JwtFilterTest {

    @InjectMocks
    private JwtFilter jwtFilter;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Clear SecurityContext before each test
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternalWithValidToken() throws Exception {
        // Arrange
        String token = "validToken";
        String username = "testUser";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        // Mock extraction and validation
        when(jwtUtil.extractUsername(token)).thenReturn(username);
        UserDetails userDetails = new User(username, "password", new ArrayList<>());
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(token, userDetails)).thenReturn(true);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);

        // After filter runs, authentication must be set
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals(username, SecurityContextHolder.getContext().getAuthentication().getName());
        
    }

    @Test
    void testDoFilterInternalWithInvalidToken() throws Exception {
        // Arrange
        String token = "invalidToken";
        String username = "testUser";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        when(jwtUtil.extractUsername(token)).thenReturn(username);
        UserDetails userDetails = new User(username, "password", new ArrayList<>());
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(token, userDetails)).thenReturn(false);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);

        // For invalid token, authentication should NOT be set
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void testDoFilterInternalWithNoAuthHeader() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);

        // No header -> no authentication
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}