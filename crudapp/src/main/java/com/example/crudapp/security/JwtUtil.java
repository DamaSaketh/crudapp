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
// }
package com.example.crudapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // 1️⃣ Secret key for signing the token (keep it safe!)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 2️⃣ Token validity: 10 hours
    private final long TOKEN_VALIDITY = 1000 * 60 * 60 * 10;

    // Generate token for a user
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    // Extract email/username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Check if token is expired
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method to extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Create token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // email/username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(secretKey)
                .compact();
    }

    // Parse claims from token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}