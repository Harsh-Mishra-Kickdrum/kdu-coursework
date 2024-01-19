package question_3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question_2.MessageQueue;

import java.util.Scanner;

public class FactorialAndFactors {
    private static final Logger logger = LoggerFactory.getLogger(FactorialAndFactors.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input a number
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // Creating the threads for factorial and factors calculations
        Thread factorialThread = new Thread(() -> {
            long factorial = calculateFactorial(number);
            System.out.println("Factorial of " + number + ": " + factorial);
        });

        Thread factorsThread = new Thread(() -> {
            System.out.print("Factors of " + number + ": ");
            calculateFactors(number);
            System.out.println();
        });

        // Starting both threads
        factorialThread.start();
        factorsThread.start();

        /**
         *The join() method is used to ensure that the main thread finishes last. In Java,
         * the join() method is provided by the java.lang.Thread class. When a thread calls
         * join() on another thread, it means that the calling
         * thread (in this case, the main thread) will wait
         * for the specified thread (in this case, factorialThread and
         * factorsThread) to complete its execution before continuing.
         */
        try {
            // Ensure the main thread finishes last
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            //e.printStackTrace();
            logger.warn("Interrupted Exception Occurred! ");
        }

        System.out.println("Main thread finished last.");
    }

    private static long calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }

    private static void calculateFactors(int n) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
    }
}

