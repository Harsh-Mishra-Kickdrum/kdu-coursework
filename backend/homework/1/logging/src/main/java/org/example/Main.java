package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main{
    public static final Logger logger
            = LoggerFactory.getLogger(Main.class);

    //for checking the working of log ,we wrote below code
//    public static void main(String[] args) {
//        logger.info("Example log from {}", Main.class.getSimpleName());
//    }

    public static Logger getmsg(){
        return logger;
    }
}
