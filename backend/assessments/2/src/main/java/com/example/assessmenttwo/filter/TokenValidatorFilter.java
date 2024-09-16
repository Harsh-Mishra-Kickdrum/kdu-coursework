package com.example.assessmenttwo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.example.assessmenttwo.utils.Mapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Filter to validate JWT tokens in incoming requests.
 * This filter extends {@link OncePerRequestFilter} to ensure it is executed once per request.
 */
@Slf4j
public class TokenValidatorFilter extends OncePerRequestFilter {

    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";

    /**
     * Validates the JWT token from the request's Authorization header.
     * If the token is valid, it sets the authentication in the security context.
     *
     * @param request     The request
     * @param response    The response
     * @param filterChain The filter chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("token validator filter started..... ");
        String jwt = request.getHeader(JWT_HEADER);
        log.info("jwt token from request : " + jwt);
        if (null != jwt) {
            try {
                jwt = jwt.substring(7);

                SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("roles");
                Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                log.info("Validated Auth: " + auth);
                Mapper.currName = username;


                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received!");
            }

        }
        filterChain.doFilter(request, response);
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
        return (
                request.getServletPath().equals("/api/v1/user/login") ||
                        request.getServletPath().equals("api/v1/user/register")
        );
    }
}