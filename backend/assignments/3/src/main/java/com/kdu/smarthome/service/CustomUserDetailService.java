package com.kdu.smarthome.service;

import com.kdu.smarthome.exception.custom.ResourceNotFoundException;
import com.kdu.smarthome.model.UserEntity;
import com.kdu.smarthome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user from the database using the username.
     *
     * @param username the username identifying the user whose data is required.
     * @return UserDetails containing the user's information.
     * @throws UsernameNotFoundException if the user cannot be found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * This method overrides the loadUserByUsername method from the UserDetailsService interface
         * and is responsible for fetching a user's details from the database
         * and converting them into a UserDetails object that Spring Security can use for
         * authentication and authorization.
         */
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found with username: {}", username);
            throw new ResourceNotFoundException("User not found with username: " + username); // Use your custom exception here
        }

        log.debug("User found in [loadUserByUsername]: {}", user);

        // Creating default admin role
        List<String> role = new ArrayList<>();
        role.add("ADMIN");


        // Building User for JWT
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role.toArray(new String[0]))
                .build();
    }
}
