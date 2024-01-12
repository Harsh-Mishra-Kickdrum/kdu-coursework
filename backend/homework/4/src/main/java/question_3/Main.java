package question_3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ArraySwapper exchangePositions = new ArraySwapper();

        // Integer Array
        Integer[] intArray = {1, 2, 3, 4, 5};
        logger.info("Before swapping: " + Arrays.toString(intArray));
        exchangePositions.swapElements(intArray, 1, 3);
        logger.info("After swapping: " + Arrays.toString(intArray));

        // String Array
        String[] strArray = {"apple", "banana", "orange", "grape"};
        logger.info("Before swapping: " + Arrays.toString(strArray));
        exchangePositions.swapElements(strArray, 0, 2);
        logger.info("After swapping: " + Arrays.toString(strArray));
    }
}

