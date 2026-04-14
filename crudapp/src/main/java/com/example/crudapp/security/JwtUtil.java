// package com.example.crudapp.security;

// import io.jsonwebtoken.*;
// import org.springframework.stereotype.Component;

// import java.util.Date;

// @Component
// public class JwtUtil {

//     private final String SECRET_KEY = "mySecretKey123"; // Use environment variable in production
//     private final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

//     // Generate JWT token
//     public String generateToken(String username) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                 .compact();
//     }

//     // Extract username from token safely
//     @SuppressWarnings("UseSpecificCatch")
//     public String extractUsername(String token) {
//         try {
//             return Jwts.parser()
//                     .setSigningKey(SECRET_KEY)
//                     .parseClaimsJws(token)
//                     .getBody()
//                     .getSubject();
//         } catch (Exception e) {
//             System.err.println("JWT extraction failed: " + e.getMessage());
//         }
//         return null;
//     }

//     // ✅ Validate token against username (two-argument version)
//     public boolean validateToken(String token, String username) {
//         String tokenUsername = extractUsername(token);
//         return tokenUsername != null && tokenUsername.equals(username) && !isTokenExpired(token);
//     }

//     @SuppressWarnings("UseSpecificCatch")
//     private boolean isTokenExpired(String token) {
//         try {
//             return Jwts.parser()
//                     .setSigningKey(SECRET_KEY)
//                     .parseClaimsJws(token)
//                     .getBody()
//                     .getExpiration()
//                     .before(new Date());
//         } catch (Exception e) {
//             return true;
//         }
//     }
// // }
// package com.example.crudapp.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// @Component
// public class JwtUtil {

//     private final String SECRET_KEY = "mysecretkey12345678901234567890";

//     public String generateToken(String email) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                 .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return extractAllClaims(token).getSubject();
//     }

//     public Date extractExpiration(String token) {
//         return extractAllClaims(token).getExpiration();
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(SECRET_KEY)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public boolean validateToken(String token, UserDetails userDetails) {
//         final String username = extractUsername(token);
//         return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//     }

//     private boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }
// }

package com.example.crudapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ MUST be 32+ characters
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "my-super-secret-key-which-is-very-long-1234567890"
            .getBytes()
    );

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}