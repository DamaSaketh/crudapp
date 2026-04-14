// package com.example.crudapp.service;

// import com.example.crudapp.model.User;
// import com.example.crudapp.repository.UserRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserService {

//     private static final Logger logger = LoggerFactory.getLogger(UserService.class);

//     private final UserRepository userRepository;

//     public UserService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     public List<User> getAllUsers() {
//         logger.info("Fetching all users from database");
//         return userRepository.findAll();
//     }

//     public User createUser(User user) {
//         logger.info("Saving user with name: {}", user.getName());

//         try {
//             User savedUser = userRepository.save(user);
//             logger.debug("User saved successfully with id: {}", savedUser.getId());
//             return savedUser;
//         } catch (Exception e) {
//             logger.error("Error while saving user", e);
//             throw e;
//         }
//     }

//     public void deleteUser(Long id) {
//         logger.warn("Deleting user with id: {}", id);

//         try {
//             userRepository.deleteById(id);
//             logger.debug("User deleted successfully with id: {}", id);
//         } catch (Exception e) {
//             logger.error("Error while deleting user with id: {}", id, e);
//             throw e;
//         }
//     }

//     public User getUserById(Long id) {
//         logger.info("Fetching user with id: {}", id);

//         return userRepository.findById(id)
//                 .orElseThrow(() -> {
//                     logger.error("User not found with id: {}", id);
//                     return new RuntimeException("User not found");
//                 });
//     }
// }

package com.example.crudapp.service;

import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users from database");
        return userRepository.findAll();
    }

    public User createUser(User user) {
        logger.info("Saving user with name: {}", user.getName());
        try {
            User savedUser = userRepository.save(user);
            logger.debug("User saved successfully with id: {}", savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error while saving user", e);
            throw e;
        }
    }

    public void deleteUser(Long id) {
        logger.warn("Deleting user with id: {}", id);
        try {
            userRepository.deleteById(id);
            logger.debug("User deleted successfully with id: {}", id);
        } catch (Exception e) {
            logger.error("Error while deleting user with id: {}", id, e);
            throw e;
        }
    }

    public User getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new UserNotFoundException(id);
                });
    }
    @Autowired
private BCryptPasswordEncoder passwordEncoder;

public void saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    
}
}