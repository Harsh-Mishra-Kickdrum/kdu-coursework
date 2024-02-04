package com.example.springjdbcdemo.exception;
/**
 * Custom exception for handling errors during the mapping of data from a ResultSet.
 * This exception is used to wrap SQLExceptions encountered in RowMapper implementations.
 */
public class DataMappingException extends RuntimeException {

    /**
     * Constructor for DataMappingException.
     *
     * @param message Detailed message about the exception.
     * @param cause   The original SQLException that was encountered.
     */
    public DataMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
