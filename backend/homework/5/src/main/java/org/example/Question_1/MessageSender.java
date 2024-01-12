package org.example.Question_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simulates a message sender that produces and sends messages to the MessageQueue.
 */
public class MessageSender implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    private String senderName;
    private MessageQueue messageQueue;

    public MessageSender(String senderName, MessageQueue messageQueue) {
        this.senderName = senderName;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String message = "Message from " + senderName + ": " + i;
            messageQueue.sendMessage(message);
            logger.info(senderName + ": Message sent - " + message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                logger.warn("Interrupted Exception Occurred! ");
            }
        }
    }
}
