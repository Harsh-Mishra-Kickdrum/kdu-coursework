package question_4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Main class to test different scenarios using TreeSets with comparators.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main method to demonstrate various test cases using TreeSets and comparators.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        logger.info("\nTest case - i:");
        Set<Book> result1 = SetDemo.treeSetDemo(new PubDateAscComparator());
        printBooks(result1);
    }

    /**
     * Prints the books in the set.
     *
     * @param books The set of books to print.
     */
    private static void printBooks(Set<Book> books) {
        logger.info("\n");
        for (Book book : books) {
            logger.info("{}", book);
        }
    }
}

