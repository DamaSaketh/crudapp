// package com.example.crudapp.controller;


// import com.example.crudapp.service.AuthService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.*;

// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(AuthController.class)
// @AutoConfigureMockMvc(addFilters = false)
// class AuthControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private AuthService authService;

//     @Test
//     void testLoginSuccess() throws Exception {

//         when(authService.login("test@mail.com", "1234"))
//                 .thenReturn("JWT_TOKEN");

//         mockMvc.perform(post("/auth/login")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("""
//                         {"email":"test@mail.com","password":"1234"}
//                         """))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").value("JWT_TOKEN"));
//     }

//     @Test
//     void testLoginFailure() throws Exception {

//         when(authService.login("test@mail.com", "wrong"))
//                 .thenThrow(new RuntimeException("Invalid credentials"));

//         mockMvc.perform(post("/auth/login")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("""
//                         {"email":"test@mail.com","password":"wrong"}
//                         """))
//                 .andExpect(status().isUnauthorized())
//                 .andExpect(jsonPath("$.token").value("Error: Invalid credentials"));
//     }
// }

// package com.example.crudapp.controller;

// import com.example.crudapp.config.TestSecurityConfig;
// import com.example.crudapp.security.JwtUtil;
// import com.example.crudapp.service.AuthService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// @WebMvcTest(
//     controllers = AuthController.class,
//     excludeAutoConfiguration = {
//         SecurityAutoConfiguration.class,
//         SecurityFilterAutoConfiguration.class
//     }
// )

// @AutoConfigureMockMvc(addFilters = false)
// @Import(TestSecurityConfig.class)
// class AuthControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private AuthService authService;

// //     @MockBean
// //     private com.example.crudapp.security.JwtUtil jwtUtil; // ✅ ADD THIS
// //     // ❌ DO NOT DELETE THIS
// // @MockBean
// // private JwtUtil jwtUtil1;

//     public AuthControllerTest() {
//     }
    

//     @Test
//     void testLoginSuccess() throws Exception {

//         when(authService.login("test@mail.com", "1234"))
//                 .thenReturn("JWT_TOKEN");

//         mockMvc.perform(post("/auth/login")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("""
//                         {"email":"test@mail.com","password":"1234"}
//                         """))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.token").value("JWT_TOKEN"));
//     }

//     @Test
//     void testLoginFailure() throws Exception {

//         when(authService.login("test@mail.com", "wrong"))
//                 .thenThrow(new RuntimeException("Invalid credentials"));

//         mockMvc.perform(post("/auth/login")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("""
//                         {"email":"test@mail.com","password":"wrong"}
//                         """))
//                 .andExpect(status().isBadRequest()); // FIXED
//     }
// }
package com.example.crudapp.controller;

import com.example.crudapp.security.JwtUtil;
import com.example.crudapp.service.AuthService;
import com.example.crudapp.service.CustomUserDetailsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
private JwtUtil jwtUtil;

@MockBean
private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private AuthService authService;

    // 🔥 CRITICAL: mock security filter dependencies
    
    @Test
    void testLoginSuccess() throws Exception {

        when(authService.login("test@mail.com", "1234"))
                .thenReturn("JWT_TOKEN");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"email":"test@mail.com","password":"1234"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("JWT_TOKEN"));
    }

   @Test
void testLoginFailure() throws Exception {

    when(authService.login("test@mail.com", "wrong"))
            .thenThrow(new RuntimeException("Invalid credentials"));

    mockMvc.perform(post("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                    {"email":"test@mail.com","password":"wrong"}
                    """))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.token").value("Error: Invalid credentials"));
}
}