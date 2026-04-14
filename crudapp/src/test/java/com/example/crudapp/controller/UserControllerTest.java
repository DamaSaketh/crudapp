// // // package com.example.crudapp.controller;

// // // import com.example.crudapp.controller.UserController;
// // // import com.example.crudapp.model.User;
// // // import com.example.crudapp.service.UserService;
// // // import org.junit.jupiter.api.Test;
// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.boot.test.autoconfigure.web.servlet.*;

// // // import org.springframework.boot.test.mock.mockito.MockBean;
// // // import org.springframework.http.MediaType;
// // // import org.springframework.test.web.servlet.MockMvc;

// // // import java.util.List;

// // // import static org.mockito.Mockito.*;
// // // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// // // import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // // @WebMvcTest(UserController.class)
// // // @AutoConfigureMockMvc(addFilters = false)
// // // class UserControllerTest {

// // //     @Autowired
// // //     private MockMvc mockMvc;

// // //     @MockBean
// // //     private UserService userService;

// // //     @Test
// // //     void testGetAllUsers() throws Exception {
// // //         User user = new User();
// // //         user.setName("John");

// // //         when(userService.getAllUsers()).thenReturn(List.of(user));

// // //         mockMvc.perform(get("/users"))
// // //                 .andExpect(status().isOk())
// // //                 .andExpect(jsonPath("$[0].name").value("John"));
// // //     }

// // //     @Test
// // //     void testCreateUser() throws Exception {
// // //         User user = new User();
// // //         user.setName("John");

// // //         when(userService.createUser(any(User.class))).thenReturn(user);

// // //         mockMvc.perform(post("/users")
// // //                 .contentType(MediaType.APPLICATION_JSON)
// // //                 .content("""
// // //                         {"name":"John","email":"john@mail.com"}
// // //                         """))
// // //                 .andExpect(status().isOk())
// // //                 .andExpect(jsonPath("$.name").value("John"));
// // //     }

// // //     @Test
// // //     void testGetUserById() throws Exception {
// // //         User user = new User();
// // //         user.setName("John");

// // //         when(userService.getUserById(1L)).thenReturn(user);

// // //         mockMvc.perform(get("/users/1"))
// // //                 .andExpect(status().isOk())
// // //                 .andExpect(jsonPath("$.name").value("John"));
// // //     }

// // //     @Test
// // //     void testDeleteUser() throws Exception {
// // //         doNothing().when(userService).deleteUser(1L);

// // //         mockMvc.perform(delete("/users/1"))
// // //                 .andExpect(status().isOk());
// // //     }
// // // }
// //  package com.example.crudapp.controller;

// // import com.example.crudapp.config.TestSecurityConfig;
// // import com.example.crudapp.model.User;
// // import com.example.crudapp.security.JwtUtil;
// // import com.example.crudapp.service.UserService;
// // import org.junit.jupiter.api.Test;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// // import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// // import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
// // import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// // import org.springframework.boot.test.mock.mockito.MockBean;
// // import org.springframework.context.annotation.Import;
// // import org.springframework.http.MediaType;
// // import org.springframework.test.web.servlet.MockMvc;

// // import java.util.List;

// // import static org.mockito.ArgumentMatchers.any;
// // import static org.mockito.Mockito.*;
// // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// // import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // @WebMvcTest(
// //     controllers = UserController.class,
// //     excludeAutoConfiguration = {
// //         SecurityAutoConfiguration.class,
// //         SecurityFilterAutoConfiguration.class
// //     }
// // )
// // @AutoConfigureMockMvc(addFilters = false)
// // @Import(TestSecurityConfig.class)
// // class UserControllerTest {

// //     @Autowired
// //     private MockMvc mockMvc;

// //     @MockBean
// //     private UserService userService;

// // //     @MockBean
// // //     private com.example.crudapp.security.JwtUtil jwtUtil; // ✅ ADD THIS
// // //     // ❌ DO NOT DELETE THIS
// // // @MockBean
// // // private JwtUtil jwtUtil1;

// //     @Test
// //     void testGetAllUsers() throws Exception {

// //         User user = new User();
// //         user.setName("John");

// //         when(userService.getAllUsers()).thenReturn(List.of(user));

// //         mockMvc.perform(get("/users"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].name").value("John"));
// //     }

// //     @Test
// //     void testCreateUser() throws Exception {

// //         User user = new User();
// //         user.setName("John");

// //         when(userService.createUser(any(User.class))).thenReturn(user);

// //         mockMvc.perform(post("/users")
// //                         .contentType(MediaType.APPLICATION_JSON)
// //                         .content("""
// //                         {"name":"John","email":"john@mail.com"}
// //                         """))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.name").value("John"));
// //     }

// //     @Test
// //     void testGetUserById() throws Exception {

// //         User user = new User();
// //         user.setName("John");

// //         when(userService.getUserById(1L)).thenReturn(user);

// //         mockMvc.perform(get("/users/1"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.name").value("John"));
// //     }

// //     @Test
// //     void testDeleteUser() throws Exception {

// //         doNothing().when(userService).deleteUser(1L);

// //         mockMvc.perform(delete("/users/1"))
// //                 .andExpect(status().isOk());
// //     }
// // }

// package com.example.crudapp.controller;

// import com.example.crudapp.model.User;
// import com.example.crudapp.service.UserService;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.List;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // @WebMvcTest(UserController.class)
// // @AutoConfigureMockMvc(addFilters = false)
// // class UserControllerTest {

// //     @Autowired
// //     private MockMvc mockMvc;

// //     @MockBean
// //     private UserService userService;

// //     @Test
// //     void testGetAllUsers() throws Exception {

// //         User user = new User();
// //         user.setName("John");

// //         when(userService.getAllUsers()).thenReturn(List.of(user));

// //         mockMvc.perform(get("/users"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$[0].name").value("John"));
// //     }

// //     @Test
// //     void testCreateUser() throws Exception {

// //         User user = new User();
// //         user.setName("John");

// //         when(userService.createUser(any(User.class))).thenReturn(user);

// //         mockMvc.perform(post("/users")
// //                         .contentType(MediaType.APPLICATION_JSON)
// //                         .content("""
// //                         {"name":"John","email":"john@mail.com"}
// //                         """))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.name").value("John"));
// //     }

// //     @Test
// //     void testGetUserById() throws Exception {

// //         User user = new User();
// //         user.setName("John");

// //         when(userService.getUserById(1L)).thenReturn(user);

// //         mockMvc.perform(get("/users/1"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.name").value("John"));
// //     }

// //     @Test
// //     void testDeleteUser() throws Exception {

// //         doNothing().when(userService).deleteUser(1L);

// //         mockMvc.perform(delete("/users/1"))
// //                 .andExpect(status().isOk());
// //     }
// // }
// @WebMvcTest(UserController.class)
// @AutoConfigureMockMvc(addFilters = false)
// class UserControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UserService userService;

//     @Test
//     void testGetAllUsers() throws Exception {
//         User user = new User();
//         user.setName("John");

//         when(userService.getAllUsers()).thenReturn(List.of(user));

//         mockMvc.perform(get("/users"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].name").value("John"));
//     }

//     @Test
//     void testCreateUser() throws Exception {
//         User user = new User();
//         user.setName("John");

//         when(userService.createUser(any(User.class))).thenReturn(user);

//         mockMvc.perform(post("/users")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("""
//                         {"name":"John","email":"john@mail.com"}
//                         """))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.name").value("John"));
//     }

//     @Test
//     void testGetUserById() throws Exception {
//         User user = new User();
//         user.setName("John");

//         when(userService.getUserById(1L)).thenReturn(user);

//         mockMvc.perform(get("/users/1"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.name").value("John"));
//     }

//     @Test
//     void testDeleteUser() throws Exception {
//         doNothing().when(userService).deleteUser(1L);

//         mockMvc.perform(delete("/users/1"))
//                 .andExpect(status().isOk());
//     }
// }

package com.example.crudapp.controller;

import com.example.crudapp.model.User;
import com.example.crudapp.security.JwtUtil;
import com.example.crudapp.service.CustomUserDetailsService;
import com.example.crudapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(com.example.crudapp.exception.GlobalExceptionHandler.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // 🔥 CRITICAL FIX
@MockBean
private JwtUtil jwtUtil;

@MockBean
private CustomUserDetailsService customUserDetailsService;
    @Test
    void testGetAllUsers() throws Exception {

        User user = new User();
        user.setName("John");

        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    void testCreateUser() throws Exception {

        User user = new User();
        user.setName("John");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {"name":"John","email":"john@mail.com"}
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testGetUserById() throws Exception {

        User user = new User();
        user.setName("John");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testDeleteUser() throws Exception {

        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
void testGetUserException() throws Exception {

    when(userService.getUserById(1L))
            .thenThrow(new RuntimeException("User not found"));

    mockMvc.perform(get("/users/1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Error: User not found"));
}
}


