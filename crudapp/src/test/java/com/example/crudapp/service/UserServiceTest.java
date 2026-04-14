
// package com.example.crudapp.service;

// import com.example.crudapp.model.User;
// import com.example.crudapp.repository.UserRepository;
// import com.example.crudapp.service.UserService;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.*;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import java.util.*;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
// class UserServiceTest {

//     @Mock
//     private UserRepository userRepository;

//     @Mock
//     private BCryptPasswordEncoder passwordEncoder;

//     @InjectMocks
//     private UserService userService;

//     @Test
//     void testGetAllUsers() {
//         when(userRepository.findAll()).thenReturn(List.of(new User()));
//         assertEquals(1, userService.getAllUsers().size());
//     }

//     @Test
//     void testCreateUser() {
//         User user = new User();
//         user.setName("John");

//         when(userRepository.save(user)).thenReturn(user);

//         User saved = userService.createUser(user);

//         assertEquals("John", saved.getName());
//     }

//     @Test
//     void testGetUserById() {
//         User user = new User();
//         user.setName("John");

//         when(userRepository.findById(1L)).thenReturn(Optional.of(user));

//         User result = userService.getUserById(1L);

//         assertEquals("John", result.getName());
//     }

//     @Test
//     void testDeleteUser() {
//         doNothing().when(userRepository).deleteById(1L);
//         userService.deleteUser(1L);
//         verify(userRepository).deleteById(1L);
//     }
// } 
//  package com.example.crudapp.service;

// import com.example.crudapp.model.User;
// import com.example.crudapp.repository.UserRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.*;

// import java.util.*;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
// class UserServiceTest {

//     @Mock private UserRepository userRepository;
//     @InjectMocks private UserService userService;

//     @Test
//     void testGetAllUsers() {
//         when(userRepository.findAll()).thenReturn(List.of(new User()));
//         assertEquals(1, userService.getAllUsers().size());
//     }

//     @Test
//     void testCreateUser() {
//         User user = new User();
//         user.setName("John");

//         when(userRepository.save(user)).thenReturn(user);

//         User saved = userService.createUser(user);

//         assertEquals("John", saved.getName());
//     }

//     @Test
//     void testGetUserById() {
//         User user = new User();
//         user.setName("John");

//         when(userRepository.findById(1L)).thenReturn(Optional.of(user));

//         User result = userService.getUserById(1L);

//         assertEquals("John", result.getName());
//     }

//     @Test
//     void testDeleteUser() {
//         doNothing().when(userRepository).deleteById(1L);

//         userService.deleteUser(1L);

//         verify(userRepository).deleteById(1L);
//     }
// }

package com.example.crudapp.service;

import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // -------------------------
    // GET ALL USERS
    // -------------------------
    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
    }

    // -------------------------
    // CREATE USER SUCCESS
    // -------------------------
    @Test
    void testCreateUser() {
        User user = new User();
        user.setName("John");

        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.createUser(user);

        assertEquals("John", saved.getName());
    }

    // -------------------------
    // CREATE USER EXCEPTION BRANCH
    // -------------------------
    @Test
    void testCreateUserException() {
        User user = new User();
        user.setName("John");

        when(userRepository.save(user))
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class,
                () -> userService.createUser(user));
    }

    // -------------------------
    // GET USER BY ID SUCCESS
    // -------------------------
    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("John");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("John", result.getName());
    }

    // -------------------------
    // GET USER BY ID NOT FOUND
    // -------------------------
    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(1L));
    }

    // -------------------------
    // DELETE USER
    // -------------------------
    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }
     @Test
    void testDeleteUserException() {
        doThrow(new RuntimeException("Delete failed"))
                .when(userRepository).deleteById(1L);

        assertThrows(RuntimeException.class,
                () -> userService.deleteUser(1L));
    }

    // -------------------------
    // SAVE USER (PASSWORD ENCODE COVERAGE)
    // -------------------------
    @Test
    void testSaveUser() {
        User user = new User();
        user.setPassword("1234");

        BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

        // Inject mock encoder using reflection (no production change)
        try {
            java.lang.reflect.Field field =
                    UserService.class.getDeclaredField("passwordEncoder");
            field.setAccessible(true);
            field.set(userService, encoder);
        } catch (Exception e) {
            fail("Failed to inject mock password encoder");
        }

        when(encoder.encode("1234")).thenReturn("ENCODED");

        userService.saveUser(user);

        assertEquals("ENCODED", user.getPassword());
        verify(userRepository).save(user);
    }
    @Test
void testSetTestColumn() {
    User user = new User();

    user.setTestColumn("ABC123");

    assertEquals("ABC123", user.getTestColumn());
}
}