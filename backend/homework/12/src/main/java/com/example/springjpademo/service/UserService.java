package com.example.springjpademo.service;

import com.example.springjpademo.dto.UserDto;
import com.example.springjpademo.entity.User;
import com.example.springjpademo.repository.UserRepository;
import com.example.springjpademo.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user to the database.
     *
     * @param userDto The user data transfer object.
     * @return The saved User entity.
     */
    public User saveUser(User userDto) {
        User user = new User();
        try {
            User savedUser = userRepository.save(user);
            log.info("User saved successfully: {}", savedUser.getUsername());
            return savedUser;
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Updates user details based on the provided UserDto.
     *
     * @param userId  The ID of the user to update.
     * @param userDto The updated user information.
     * @return The updated User entity.
     * @throws UserNotFoundException If no user is found with the given ID.
     */
    public User updateUserDetails(UUID userId, UserDto userDto) {
        return userRepository.findById(userId).map(user -> {
            if (userDto.getUsername() != null) {
                user.setUsername(userDto.getUsername());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }
}
