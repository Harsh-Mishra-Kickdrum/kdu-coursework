package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom authentication manager that implements Spring Security's AuthenticationProvider.
 * This class provides a mechanism to authenticate users based on username and password
 * by leveraging a custom user service.
 */
public class CustomAuthenticationManager implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationManager.class);

    @Autowired
    public CustomAuthenticationManager(UserService userService){
        this.userService = userService;
    }
    private UserService userService;

    /**
     * Authenticates the user with the provided credentials.
     *
     * @param authentication Contains the username and password for authentication.
     * @return An Authentication object containing the user's details and roles if authentication is successful.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Validate user credentials with the UserService
        if (!userService.validateUserCredentials(username, password)) {
            logger.error("Invalid credentials for username: {}", username);
            throw new BadCredentialsException("Invalid username or password!");
        }

        // Fetch the user's role, assuming the UserService provides this information
        String role = userService.getUserRoleByUsername(username);
        if (role == null || role.isEmpty()) {
            logger.error("No role assigned to user: {}", username);
            throw new BadCredentialsException("No role assigned to user!");
        }

        List<GrantedAuthority> authorities = getGrantedAuthorities(role);

        logger.info("User {} authenticated successfully with role {}", username, role);
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    /**
     * Checks if this AuthenticationProvider supports a given Authentication object.
     *
     * @param authentication The class of the authentication object.
     * @return true if the authentication object is supported, false otherwise.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * Helper method to convert a user role into a GrantedAuthority object.
     *
     * @param role The role of the user.
     * @return A list containing the GrantedAuthority for the user's role.
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        return grantedAuthorities;
    }
}
