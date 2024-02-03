package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.filter.TokenGeneratorFilter;
import com.example.springsecuritydemo.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security configuration class for Spring Security.
 * Defines beans related to the security filter chain and password encoding.
 **/

@Configuration
public class CustomSecurityConfig {

    /**
     * Configures the security filter chain to specify custom authentication and authorization rules.
     *
     * @param http The HttpSecurity configuration object used to customize security settings.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs configuring the security.
     */
    @Bean
    public SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http
                // Add custom filters for token generation and validation
                .addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Publicly accessible login endpoint
                        .requestMatchers("/login").permitAll()  // Use your actual login endpoint
                        // Restrict access to admin endpoints to users with ADMIN role
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Restrict access to user details to users with USER role
                        .requestMatchers("/user/**").hasRole("USER")
                        // Require authentication for all other requests
                        .anyRequest().authenticated())
                // Disable CSRF for the application (not recommended for production)
                .csrf(csrf -> csrf.disable())
                // Enable CORS with default settings
                .cors(withDefaults());

        // Enable form login and HTTP basic authentication with default configuration
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        return http.build();
    }

    /**
     * Defines the password encoder bean to be used for encoding and verifying passwords.
     *
     * @return The password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}