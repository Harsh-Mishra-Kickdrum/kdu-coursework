package org.example.Question_1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simulates a message receiver that retrieves and processes messages from the MessageQueue.
 */
public class MessageReceiver implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private String receiverName;
    private MessageQueue messageQueue;

    public MessageReceiver(String receiverName, MessageQueue messageQueue) {
        this.receiverName = receiverName;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            String message = messageQueue.receiveMessage();
            logger.info(receiverName + ": Message received - "+ i +":" +  message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                logger.warn("Interrupted Exception Occurred! ");
            }
        }
    }
}
