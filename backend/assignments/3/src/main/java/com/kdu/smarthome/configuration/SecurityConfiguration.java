package com.kdu.smarthome.configuration;

import com.kdu.smarthome.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


        private final CustomUserDetailService userDetailsService;
        private final JwtAuthorizationFilter jwtAuthorizationFilter;

        public SecurityConfiguration(CustomUserDetailService customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
            this.userDetailsService = customUserDetailsService;
            this.jwtAuthorizationFilter = jwtAuthorizationFilter;

        }
        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
                throws Exception {

            AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

            return authenticationManagerBuilder.build();
        }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(requests -> requests
                        .antMatchers("/api/v1/auth/**").permitAll() // Changed from requestMatchers to antMatchers
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        return http.build();
    }


    @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }
