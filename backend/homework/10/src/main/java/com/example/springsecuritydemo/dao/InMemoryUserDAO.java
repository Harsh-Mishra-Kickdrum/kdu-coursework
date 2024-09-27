package com.example.springsecuritydemo.dao;

import com.example.springsecuritydemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An in-memory DAO implementation for managing users.
 */
@Repository
public class InMemoryUserDAO implements UserDAO {

    // List to simulate a database of users.
    private final List<User> userList = new ArrayList<>();

    /**
     * Finds all users in the in-memory store.
     *
     * @return a list of all users.
     */
    @Override
    public List<User> findAll() {
        return userList;
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for.
     * @return an Optional containing the found user, or an empty Optional if no user is found.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userList.stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(username))
                .findFirst();
    }

    /**
     * Saves a user to the in-memory store.
     *
     * @param user the user to save.
     * @return the saved user.
     */
    @Override
    public User save(User user) {
        userList.add(user); // Adds the user to the list, simulating a save operation.
        return user;
    }
}
