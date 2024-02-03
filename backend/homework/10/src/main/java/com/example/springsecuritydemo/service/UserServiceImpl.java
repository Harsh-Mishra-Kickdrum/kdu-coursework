package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dao.UserDAO;
import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Implementation of the UserService interface.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    /**
     * Constructs a new UserServiceImpl with the necessary dependencies.
     *
     * @param userDAO         the DAO for user operations.
     * @param modelMapper     the ModelMapper for mapping between entities and DTOs.
     * @param passwordEncoder the PasswordEncoder for encrypting and checking passwords.
     */
    @Autowired
    public UserServiceImpl(UserDAO userDAO, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a List of UserDto objects representing all users.
     */
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDAO.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }


    /**
     * Finds a user by their username.
     *
     * @param userName the username of the user to find.
     * @return an Optional of UserDto if found, otherwise an empty Optional.
     */
    @Override
    public Optional<UserDto> getUserByUserName(String userName) {
        Optional<User> user = userDAO.findByUsername(userName);
        return user.map(value -> modelMapper.map(value, UserDto.class));
    }


    /**
     * Adds a new user to the database.
     *
     * @param userDto the user data transfer object.
     * @return the added UserDto with additional data set.
     */
    @Override
    public UserDto addUser(UserDto userDto) {
        // Encrypt password before saving
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userDAO.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    /**
     * Validates user credentials against the stored values in the database.
     *
     * @param userName the username to validate.
     * @param password the password to validate.
     * @return true if credentials are valid, false otherwise.
     */
    @Override
    public boolean validateUserCredentials(String userName, String password) {
        Optional<User> userOptional = userDAO.findByUsername(userName);
        return userOptional
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .isPresent();
    }

    /**
     * Retrieves the role of a user by their username.
     *
     * @param username the username of the user whose role is to be retrieved.
     * @return a string representing the user's role, or null if the user is not found.
     */
    @Override
    public String getUserRoleByUsername(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        return user.isPresent() ? user.get().getRole() : null;
    }
}
