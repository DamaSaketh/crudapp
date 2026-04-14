// // package com.example.crudapp.service;


// // import com.example.crudapp.model.User;
// // import com.example.crudapp.repository.UserRepository;
// // import com.example.crudapp.security.JwtUtil;
// // import com.example.crudapp.service.AuthService;

// // import org.junit.jupiter.api.Test;
// // import org.junit.jupiter.api.extension.ExtendWith;
// // import org.mockito.*;
// // import org.springframework.security.crypto.password.PasswordEncoder;

// // import java.util.Optional;

// // import static org.junit.jupiter.api.Assertions.*;
// // import static org.mockito.Mockito.*;

// // @ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
// // class AuthServiceTest {

// //     @Mock
// //     private UserRepository userRepository;

// //     @Mock
// //     private PasswordEncoder passwordEncoder;

// //     @Mock
// //     private JwtUtil jwtUtil;

// //     @InjectMocks
// //     private AuthService authService;

// //     @Test
// //     void testLoginSuccess() {
// //         User user = new User();
// //         user.setEmail("test@mail.com");
// //         user.setPassword("encoded");

// //         when(userRepository.findByEmail("test@mail.com"))
// //                 .thenReturn(Optional.of(user));

// //         when(passwordEncoder.matches("1234", "encoded"))
// //                 .thenReturn(true);

// //         when(jwtUtil.generateToken("test@mail.com"))
// //                 .thenReturn("TOKEN");

// //         String token = authService.login("test@mail.com", "1234");

// //         assertEquals("TOKEN", token);
// //     }

// //     @Test
// //     void testUserNotFound() {
// //         when(userRepository.findByEmail("x@mail.com"))
// //                 .thenReturn(Optional.empty());

// //         assertThrows(RuntimeException.class,
// //                 () -> authService.login("x@mail.com", "1234"));
// //     }

// //     @Test
// //     void testInvalidPassword() {
// //         User user = new User();
// //         user.setEmail("test@mail.com");
// //         user.setPassword("encoded");

// //         when(userRepository.findByEmail("test@mail.com"))
// //                 .thenReturn(Optional.of(user));

// //         when(passwordEncoder.matches("wrong", "encoded"))
// //                 .thenReturn(false);

// //         assertThrows(RuntimeException.class,
// //                 () -> authService.login("test@mail.com", "wrong"));
// //     }
// // }

// package com.example.crudapp.service;

// import com.example.crudapp.model.User;
// import com.example.crudapp.repository.UserRepository;
// import com.example.crudapp.security.JwtUtil;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.*;

// import org.springframework.security.crypto.password.PasswordEncoder;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)

// class AuthServiceTest {

//     @Mock private UserRepository userRepository;
//     @Mock private PasswordEncoder passwordEncoder;
//     @Mock private JwtUtil jwtUtil;

//     @InjectMocks private AuthService authService;

//     @Test
//     void testLoginSuccess() {

//         User user = new User();
//         user.setEmail("test@mail.com");
//         user.setPassword("encoded");

//         when(userRepository.findByEmail("test@mail.com"))
//                 .thenReturn(Optional.of(user));

//         when(passwordEncoder.matches("1234", "encoded"))
//                 .thenReturn(true);

//         when(jwtUtil.generateToken("test@mail.com"))
//                 .thenReturn("TOKEN");

//         String token = authService.login("test@mail.com", "1234");

//         assertEquals("TOKEN", token);
//     }

//     @Test
//     void testUserNotFound() {

//         when(userRepository.findByEmail("x@mail.com"))
//                 .thenReturn(Optional.empty());

//         assertThrows(RuntimeException.class,
//                 () -> authService.login("x@mail.com", "1234"));
//     }

//     @Test
//     void testInvalidPassword() {

//         User user = new User();
//         user.setEmail("test@mail.com");
//         user.setPassword("encoded");

//         when(userRepository.findByEmail("test@mail.com"))
//                 .thenReturn(Optional.of(user));

//         when(passwordEncoder.matches("wrong", "encoded"))
//                 .thenReturn(false);

//         assertThrows(RuntimeException.class,
//                 () -> authService.login("test@mail.com", "wrong"));
//     }
// }

package com.example.crudapp.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import com.example.crudapp.security.JwtUtil;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

//     @Test
//     void testLoginSuccess() {

//         User user = new User();
//         user.setEmail("test@mail.com");
//         user.setPassword("encoded");

//         when(userRepository.findByEmail("test@mail.com"))
//                 .thenReturn(Optional.of(user));

//         when(passwordEncoder.matches("1234", "encoded"))
//                 .thenReturn(true);

//         when(jwtUtil.generateToken("test@mail.com"))
//                 .thenReturn("TOKEN");

//         String token = authService.login("test@mail.com", "1234");

//         assertEquals("TOKEN", token);

//         verify(userRepository).findByEmail("test@mail.com");
//         verify(passwordEncoder).matches("1234", "encoded");
//         verify(jwtUtil).generateToken("test@mail.com");
//     }
@Test
void testLoginSuccess() {
    User user = new User();
    user.setEmail("test@mail.com");
    user.setPassword("encoded");

    when(userRepository.findByEmail("test@mail.com"))
            .thenReturn(Optional.of(user));

    when(passwordEncoder.matches("1234", "encoded"))
            .thenReturn(true);

    when(jwtUtil.generateToken("test@mail.com"))
            .thenReturn("TOKEN");

    String token = authService.login("test@mail.com", "1234");

    assertEquals("TOKEN", token);
}
@Test
void testUserNotFound() {

    when(userRepository.findByEmail("x@mail.com"))
            .thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class,
            () -> authService.login("x@mail.com", "1234"));

    assertEquals("User not found", ex.getMessage());
}

    @Test
    void testInvalidPassword() {

        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("encoded");

        when(userRepository.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("wrong", "encoded"))
                .thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> authService.login("test@mail.com", "wrong"));

    assertEquals("Invalid credentials", ex.getMessage()); // ✅ FIXED
}}