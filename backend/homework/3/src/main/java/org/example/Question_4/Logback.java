package org.example.Question_4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Logback {
    private static final Logger LOGGER = LoggerFactory.getLogger(Logback.class);
    public static Logger getMsg(){
        return LOGGER;
    }
}