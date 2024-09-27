package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.dto.UserDto;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Custom implementation of UserDetailsService to integrate with the application's user service.
 */
@Component
class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    /**
     * Constructor-based dependency injection of UserService.
     *
     * @param userService The UserService for retrieving user data.
     */
    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loads the user by username. This method is called during the authentication process.
     *
     * @param username the username identifying the user whose data is required.
     * @return UserDetails containing the user's information.
     * @throws UsernameNotFoundException if the user cannot be found or the user has no roles.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.getUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Fetch the user role
        String role = userService.getUserRoleByUsername(username);
        if (role == null || role.isEmpty()) {
            throw new UsernameNotFoundException("User has no assigned roles: " + username);
        }

        // Placeholder password handling. In real scenarios, you would fetch and use the encoded password.
        String placeholderPassword = "";

        // Convert to Spring Security UserDetails with roles prefixed by "ROLE_"
        return new org.springframework.security.core.userdetails.User(
                userDto.getUserName(),
                placeholderPassword,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}