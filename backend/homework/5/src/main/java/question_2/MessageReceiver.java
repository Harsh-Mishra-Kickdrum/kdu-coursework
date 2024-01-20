package question_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Simulates a message receiver that retrieves and processes messages from the MessageQueue.
 */
public class MessageReceiver implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);

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
            LOGGER.info(receiverName + ": Message received - " + message);

            try {

                //TimeUnit.SECONDS gives more precise time than Threads.sleep()
                // Using TimeUnit for better readability and expressiveness
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                //e.printStackTrace();
                LOGGER.warn("Interrupted Exception Occurred! ");
            }
        }
    }
}
