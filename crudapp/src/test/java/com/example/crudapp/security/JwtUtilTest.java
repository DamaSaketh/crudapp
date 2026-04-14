// package com.example.crudapp.security;

// import org.junit.jupiter.api.Test;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;

// import java.util.Collections;
// import java.util.Date;

// import static org.junit.jupiter.api.Assertions.*;

// class JwtUtilTest {

//     private final JwtUtil jwtUtil = new JwtUtil();

//     @Test
//     void testGenerateAndExtractUsername() {
//         String email = "test@example.com";

//         String token = jwtUtil.generateToken(email);
//         String extractedUsername = jwtUtil.extractUsername(token);

//         assertEquals(email, extractedUsername);
//     }

//     @Test
//     void testExtractExpiration() {
//         String token = jwtUtil.generateToken("test@example.com");

//         Date expiration = jwtUtil.extractExpiration(token);

//         assertNotNull(expiration);
//         assertTrue(expiration.after(new Date()));
//     }

//     @Test
//     void testValidateTokenSuccess() {
//         String email = "user@test.com";
//         String token = jwtUtil.generateToken(email);

//         UserDetails userDetails = new User(email, "password", Collections.emptyList());

//         boolean isValid = jwtUtil.validateToken(token, userDetails);

//         assertTrue(isValid);
//     }

//     @Test
//     void testValidateTokenFailureWrongUser() {
//         String token = jwtUtil.generateToken("correct@test.com");

//         UserDetails userDetails = new User("wrong@test.com", "password", Collections.emptyList());

//         boolean isValid = jwtUtil.validateToken(token, userDetails);

//         assertFalse(isValid);
//     }

//     @Test
//     void testTokenNotExpired() {
//         String token = jwtUtil.generateToken("test@example.com");

//         UserDetails userDetails = new User("test@example.com", "password", Collections.emptyList());

//         assertTrue(jwtUtil.validateToken(token, userDetails));
//     }
// }

package com.example.crudapp.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void testGenerateAndExtractUsername() {
        String email = "test@example.com";

        String token = jwtUtil.generateToken(email);

        assertEquals(email, jwtUtil.extractUsername(token));
    }

    @Test
    void testExtractExpiration() {
        String token = jwtUtil.generateToken("test@example.com");

        Date expiration = jwtUtil.extractExpiration(token);

        assertNotNull(expiration);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    void testValidateTokenSuccess() {
        String email = "user@test.com";

        String token = jwtUtil.generateToken(email);

        UserDetails userDetails =
                new User(email, "password", Collections.emptyList());

        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    void testValidateTokenFailureWrongUser() {
        String token = jwtUtil.generateToken("correct@test.com");

        UserDetails userDetails =
                new User("wrong@test.com", "password", Collections.emptyList());

        assertFalse(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    void testValidateTokenFailureNullUser() {
        String token = jwtUtil.generateToken("test@example.com");

        assertFalse(jwtUtil.validateToken(token,
                new User("different@test.com", "pass", Collections.emptyList())));
    }
}