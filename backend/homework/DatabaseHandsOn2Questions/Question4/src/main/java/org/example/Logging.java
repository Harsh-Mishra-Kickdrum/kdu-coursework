package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {

    public static final Logger logger = LoggerFactory.getLogger(Logging.class);

    /**
     * Logger created for logging.
     */
    private Logging() {
        Logging.getmsg().info("Logger Created! Call the method for logging.");
    }

    public static Logger getmsg() {
        return logger;
    }
}
