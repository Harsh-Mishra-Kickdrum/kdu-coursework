package org.example.Question_1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class represents the main entry point of the messaging application.
 * It initializes instances of MessageQueue, MessageSender, and MessageReceiver,
 * and starts threads for sending and receiving messages concurrently.
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    /**
     * The main method where the execution of the messaging application starts.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Creating an instance of MessageQueue
        MessageQueue messageQueue = new MessageQueue();

        // Creating three instances of MessageSender
        MessageSender sender1 = new MessageSender("Sender1", messageQueue);
        MessageSender sender2 = new MessageSender("Sender2", messageQueue);
        MessageSender sender3 = new MessageSender("Sender3", messageQueue);

        // Creating three instances of MessageReceiver
        MessageReceiver receiver1 = new MessageReceiver("Receiver1", messageQueue);
        MessageReceiver receiver2 = new MessageReceiver("Receiver2", messageQueue);
        MessageReceiver receiver3 = new MessageReceiver("Receiver3", messageQueue);

        // Starting sender and receiver threads
        new Thread(sender1).start();
        new Thread(sender2).start();
        new Thread(sender3).start();
        new Thread(receiver1).start();
        new Thread(receiver2).start();
        new Thread(receiver3).start();

        LOGGER.info("Main: Sender and receiver threads started.");
    }


}
