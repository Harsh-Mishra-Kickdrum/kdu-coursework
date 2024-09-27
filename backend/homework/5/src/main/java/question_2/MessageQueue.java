package question_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represents a message queue where senders put messages, and receivers retrieve messages.
 * It ensures thread-safe message handling.
 */
public class MessageQueue {

    private static final Logger logger = LoggerFactory.getLogger(MessageQueue.class);

    /**
     * A blocking queue is a data structure used in concurrent programming where one
     * thread produces data and another consumes it.
     * Unlike a regular queue, a blocking queue is designed
     * to handle situations where the queue is empty or full.
     */
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    /**
     * Adds a message to the queue.
     *
     * @param message The message to be added.
     */
    public void sendMessage(String message) {
        try {
            queue.put(message);
            logger.info("MessageQueue: Message sent to the queue - " + message);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            logger.warn("Interrupted Exception Occurred! ");
        }
    }

    /**
     * Retrieves a message from the queue.
     *
     * @return The received message.
     */
    public String receiveMessage() {
        try {
            String message = queue.take();
            logger.info("MessageQueue: Message received from the queue - " + message);
            return message;
        } catch (InterruptedException e) {
            //e.printStackTrace();
            logger.warn("Interrupted Exception Occurred! ");
            return null;
        }
    }
}
