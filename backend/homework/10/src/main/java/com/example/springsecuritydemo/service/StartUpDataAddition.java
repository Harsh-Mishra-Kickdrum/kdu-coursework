package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service to add startup data into the application.
 * Implements CommandLineRunner to execute code after the application has started.
 * Preloads the in-memory user database with default users.
 */
@Service
public class StartUpDataAddition implements CommandLineRunner {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs the service with required dependencies.
     *
     * @param userDAO          DAO for user operations.
     * @param passwordEncoder  Encoder for password hashing.
     */
    @Autowired
    public StartUpDataAddition(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Runs the data initialization logic. This method is invoked automatically after the application starts.
     * Adds default users to the application's user storage.
     *
     * @param args Application arguments.
     */
    @Override
    public void run(String[] args) {
        // Preloading the in-memory database with some users
        userDAO.save(new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
        userDAO.save(new User("user", passwordEncoder.encode("user"), "ROLE_USER"));
    }
}
