// package com.example.crudapp.repository;

// import com.example.crudapp.model.User;
// import com.example.crudapp.repository.UserRepository;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest
// class UserRepositoryTest {

//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     void testFindByEmail() {
//         User user = new User();
//         user.setEmail("test@mail.com");
//         user.setName("John");

//         userRepository.save(user);

//         Optional<User> result = userRepository.findByEmail("test@mail.com");

//         assertTrue(result.isPresent());
//         assertEquals("John", result.get().getName());
//     }
// } 
package com.example.crudapp.repository;

import com.example.crudapp.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {

        User user = new User();
        user.setEmail("test@mail.com");
        user.setName("John");

        userRepository.save(user);

        Optional<User> result = userRepository.findByEmail("test@mail.com");

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }
}