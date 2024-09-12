package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.LoginRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.dto.response.LoginResponse;
import com.kdu.smarthome.dto.response.UserRegisterResponse;
import com.kdu.smarthome.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for user registration.
     *
     * @param registerRequest The registration request containing user information.
     * @return A response entity containing the registration response.
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest registerRequest) {
        log.info("POST: /api/v1/auth/register");
        log.debug("Request data: {}", registerRequest);

        UserRegisterResponse registerResponse = authService.register(registerRequest);

        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    /**
     * Endpoint for user login.
     *
     * @param loginRequest The login request containing login credentials.
     * @return A response entity containing the login response.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("POST: /api/v1/auth/login");
        log.debug("Request data: {}", loginRequest);

        LoginResponse loginResponse = authService.login(loginRequest);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
