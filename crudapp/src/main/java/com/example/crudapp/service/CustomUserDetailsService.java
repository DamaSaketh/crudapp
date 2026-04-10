// // package com.example.crudapp.security;

// // import com.example.crudapp.model.User;
// // import com.example.crudapp.repository.UserRepository;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // import org.springframework.stereotype.Service;

// // import java.util.ArrayList;

// // @Service
// // public class CustomUserDetailsService implements UserDetailsService {

// //     private final UserRepository userRepository;

// //     public CustomUserDetailsService(UserRepository userRepository) {
// //         this.userRepository = userRepository;
// //     }

// //     @Override
// //     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
// //         User user = userRepository.findByEmail(email)
// //                 .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
                

// //         // Return Spring Security User with username=email, password, empty authorities
// //         return new org.springframework.security.core.userdetails.User(
// //                 user.getEmail(),
// //                 user.getPassword(),
// //                 new ArrayList<>() // no roles for now
// //         );
// //     }
// // }

// // package com.example.crudapp.security;

// // import com.example.crudapp.entity.User;
// // import com.example.crudapp.repository.UserRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.core.userdetails.*;
// // import org.springframework.stereotype.Service;

// // @Service
// // public class CustomUserDetailsService implements UserDetailsService {

// //     @Autowired
// //     private UserRepository userRepository;

// //     @Override
// //     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
// //         User user = userRepository.findByEmail(email)
// //                 .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

// //         return org.springframework.security.core.userdetails.User.builder()
// //                 .username(user.getEmail())
// //                 .password(user.getPassword()) // Password must be encoded (BCrypt)
// //                 .roles("USER") // Or fetch roles from DB
// //                 .build();
// //     }
// // }
// package com.example.crudapp.service;

// import com.example.crudapp.model.User;
// import com.example.crudapp.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // Look up user by email
//         User user = userRepository.findByEmail(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         // Return a Spring Security User object
//         return org.springframework.security.core.userdetails.User
//                 .withUsername(user.getEmail())
//                 .password(user.getPassword())
//                 .authorities("USER") // or any roles you want
//                 .build();
//     }
// }

package com.example.crudapp.service;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // must be BCrypt encoded
                .roles("USER")
                .build();
    }
}