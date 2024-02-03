package com.example.springsecuritydemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication requests.
 */
@RestController
public class AuthController {

    /**
     * Handles the login request.

     * This endpoint simply returns a success message indicating the login
     * endpoint was reached. In a real application, this would trigger the
     * authentication process.
     *
     * @return A response entity with a message and the HTTP status code.
     */
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        // In an actual implementation, this method would involve authentication logic
        // such as verifying user credentials, generating tokens, etc.
        return new ResponseEntity<>("Login endpoint reached", HttpStatus.CREATED);
    }
}
