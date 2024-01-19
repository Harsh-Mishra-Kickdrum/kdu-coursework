package question_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Simulates a message sender that produces and sends messages to the MessageQueue.
 */
public class MessageSender implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

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
            LOGGER.info(senderName + ": Message sent - " + message);

            try {
                // Using TimeUnit for better readability and expressiveness
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
