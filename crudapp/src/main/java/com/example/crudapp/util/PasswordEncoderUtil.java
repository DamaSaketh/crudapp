package com.example.crudapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // List of raw passwords you want to use
       
        String[] passwords = {"1234"};
        for (String rawPassword : passwords) {
            String encoded = encoder.encode(rawPassword);
            System.out.println("Raw: " + rawPassword + " -> Encoded: " + encoded);
        }
    }
}