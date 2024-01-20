package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for logging messages using SLF4J.
 */
public class Logging {
    // Initializing the logger for the class
    public static final Logger logger = LoggerFactory.getLogger(Logging.class);

    /**
     * Retrieves the logger instance.
     *
     * @return The logger instance for the class.
     */
    public static Logger getMsg() {
        return logger;
    }
}
