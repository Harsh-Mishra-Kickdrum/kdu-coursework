package question_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The main class that initializes instances of MessageQueue, creates ExecutorServices for
 * MessageSender and MessageReceiver thread pools, and submits tasks to them.
 */
public class MainApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        // Creating an instance of MessageQueue
        MessageQueue messageQueue = new MessageQueue();

        // Creating ExecutorServices for MessageSender and MessageReceiver thread pools
        ExecutorService senderThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService receiverThreadPool = Executors.newFixedThreadPool(3);

        // Creating three instances of MessageSender
        for (int i = 1; i <= 3; i++) {
            MessageSender sender = new MessageSender("Sender" + i, messageQueue);
            senderThreadPool.submit(sender);
        }

        // Creating three instances of MessageReceiver
        for (int i = 1; i <= 3; i++) {
            MessageReceiver receiver = new MessageReceiver("Receiver" + i, messageQueue);
            receiverThreadPool.submit(receiver);
        }

        // Shutting down the thread pools
        senderThreadPool.shutdown();
        receiverThreadPool.shutdown();

        LOGGER.info("MainApp: Sender and receiver thread pools submitted tasks.");
    }
}

