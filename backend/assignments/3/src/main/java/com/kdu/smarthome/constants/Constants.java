package com.kdu.smarthome.constants;

/**
 * Holds application-wide constant values.
 */
public final class Constants {

    private Constants() {
        // Private constructor to prevent instantiation
    }

    // JWT Token constants
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET_KEY = "harshMishraSecretKey";
    public static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds


    //log print message
    public static final String MESSAGE = "Request data: {}";

}
