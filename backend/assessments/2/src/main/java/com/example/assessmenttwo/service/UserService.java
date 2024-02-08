package com.example.assessmenttwo.service;

import com.example.assessmenttwo.entity.User;
import com.example.assessmenttwo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.assessmenttwo.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserService {
     UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user to the database.
     *
     * @param user The user data transfer object.
     * @return The saved User entity.
     */
    public User saveUser(User user) {
         user = new User();
        try {
            User savedUser = userRepository.save(user);
            log.info("User saved successfully: {}", savedUser.getName());
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
     * @param user The updated user information.
     * @return The updated User entity.
     * @throws UserNotFoundException If no user is found with the given ID.
     */
    public User updateUserDetails(Long userId, User user) {
        return userRepository.findById(userId).map(user1 -> {
            if (user.getName()!= null) {
                user.setName(user.getName());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }


    @Transactional
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
