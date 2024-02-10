package com.kdu.smarthome.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.utility.JwtUtility;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Authorization filter to validate JWT token for each HTTP request.
 */
@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtil;
    private final ObjectMapper mapper;

    public JwtAuthorizationFilter(JwtUtility jwtUtil, ObjectMapper mapper) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain) throws javax.servlet.ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            String accessToken = jwtUtil.resolveToken((HttpServletRequest) request);

            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            log.debug("Token received: {}", accessToken);

            Claims claims = jwtUtil.resolveClaims((HttpServletRequest) request);

            if (claims != null && jwtUtil.validateClaims(claims)) {
                String username = claims.getSubject();
                log.debug("Username found from token: {}", username);

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Authentication error: {}", e.getMessage(), e);
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);
            return;
        }

        filterChain.doFilter(request, response);
    }

}
