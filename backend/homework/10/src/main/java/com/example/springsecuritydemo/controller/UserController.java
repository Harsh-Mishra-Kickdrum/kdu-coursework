package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for handling user-specific requests.
 */
@RestController
@RequestMapping("/user") // Base path for all endpoints in this controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Constructs the UserController with a UserService.
     * @param userService The service to handle user operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves the details of the currently authenticated user.
     *
     * @return A ResponseEntity containing the user details or an error message.
     */
    @GetMapping("/details")
    public ResponseEntity<Object> singleUserDetails() {
        try {
            // Get the authentication object from the security context
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName(); // Extract username from the authentication object

            // Attempt to fetch user details using the username
            Optional<UserDto> userDtoOptional = userService.getUserByUserName(username);

            if (userDtoOptional.isPresent()) {
                UserDto userDto = userDtoOptional.get(); // Get the UserDto if present
                logger.info("User details retrieved successfully for user: {}", username);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                logger.warn("No user found for username: {}", username);
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching user details: {}", e.getMessage(), e);
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
