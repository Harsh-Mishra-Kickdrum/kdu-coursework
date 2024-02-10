package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.LoginRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.dto.response.LoginResponse;
import com.kdu.smarthome.dto.response.UserRegisterResponse;
import com.kdu.smarthome.model.UserEntity;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.utility.JwtUtility;
import com.kdu.smarthome.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtility jwtUtility, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user with the provided registration details.
     * @param registerRequest The registration request containing the user's details.
     * @return A response indicating the user has been registered successfully, including a JWT token for authentication.
     */
    public UserRegisterResponse register(UserRegisterRequest registerRequest) {
        log.info("Registering new user: {}", registerRequest.getUsername());

        // saving user in Database
        UserEntity user = Mapper.getUserFromRegisterRequest(registerRequest);
        userRepository.save(user);

        // generating token
        String token = jwtUtility.createToken(user);

        log.info("User registered successfully with username: {}", user.getUsername());

        return new UserRegisterResponse("User registered successfully!!!", token);
    }

    /**
     * Authenticates a user based on the provided login credentials.
     * @param loginRequest The request containing the username and password.
     * @return A response indicating the user has been logged in successfully, including a JWT token for subsequent requests.
     */
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        log.debug("Auth-service [Login]: Attempting login for username: {}", username);

        // authenticating user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserEntity currentUser = userRepository.findByUsername(username);
        if (currentUser == null) {
            log.error("Auth-service [Login]: User not found for username: {}", username);
            throw new IllegalArgumentException("User not found");
        }

        String token = jwtUtility.createToken(currentUser);

        log.info("User logged in successfully with username: {}", username);

        return new LoginResponse("User logged in successfully!!!", token);
    }
}
