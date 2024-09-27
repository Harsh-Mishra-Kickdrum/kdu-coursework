package com.example.springsecuritydemo.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Filter for generating JWT tokens for authenticated requests.
 * This filter extends {@link OncePerRequestFilter} to ensure it is executed once per request.
 */
@Component
public class TokenGeneratorFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(TokenGeneratorFilter.class);

    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4"; // Secret key for signing JWT
    public static final String JWT_HEADER = "Authorization"; // Header name where JWT will be placed


    /**
     * Processes an HTTP request to generate a JWT token for authenticated users and adds it to the response headers.
     *
     * @param request     The request
     * @param response    The response
     * @param filterChain The filter chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Inside TokenGeneratorFilter");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .setIssuer("kdu") // Define the issuer of the JWT
                    .setSubject("JWT Token") // Define the subject of the JWT
                    .claim("username", authentication.getName()) // Include the username in the token
                    .claim("roles", populateAuthorities(authentication.getAuthorities())) // Include user roles in the token
                    .setIssuedAt(new Date()) // Set the issuance date
                    .setExpiration(new Date((new Date()).getTime() + 30000000)) // Set the expiration date
                    .signWith(key) // Sign the JWT with the secret key
                    .compact(); // Build the JWT
            response.setHeader(JWT_HEADER, "Bearer " + jwt); // Add JWT to response headers
            log.info("JWT token generated and added to the response header '{}'", JWT_HEADER);
        }

        filterChain.doFilter(request, response); // Continue filter chain
    }


    /**
     * Determines if a request should not be filtered.
     * This implementation exempts the login endpoint from being filtered.
     *
     * @param request The request
     * @return true if the request should not be filtered, false otherwise
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }

    /**
     * Converts a collection of granted authorities into a comma-separated string of authorities.
     *
     * @param collection The collection of granted authorities
     * @return A comma-separated string of authorities
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
