package org.example;

public class BusinessService {

    static final int BEST_NUMBER = 12;
    /**
     * Example method with @Log and @HandleError annotations.
     *
     * @Log(level = LogLevel.INFO, message = "Starting methodA")
     * @HandleError({CustomException.class, AnotherException.class})
     */
    public void methodA() throws CustomException, AnotherException {
        Logging.getmsg().info("Inside methodA - INFO log");

        // Simulating some business logic
        int result = performComplexCalculation();

        // Simulating  exceptions for demonstration
        if (result < 0) {
            throw new CustomException("Custom Exception in methodA");
        } else if (result > 100) {
            throw new AnotherException("Another Exception in methodA");
        }

        Logging.getmsg().info("Exiting methodA - INFO log");
    }

    /**
     * Example method with @Log annotation.
     *
     * @Log(level = LogLevel.DEBUG, message = "Starting methodB")
     */
    public void methodB() {
        Logging.getmsg().debug("Inside methodB - DEBUG log");

        // Simulating some business logic
        int result = performSimpleCalculation();

        Logging.getmsg().debug("Result of methodB calculation: " + result);

        Logging.getmsg().info("Exiting methodB - INFO log");
    }

    /**
     * Method with no annotations.
     */
    public void methodC() {
        Logging.getmsg().info("Inside methodC - INFO log");

        // Simulating some business logic
        int result = performComplexCalculation();
        Logging.getmsg().debug("Result of methodC calculation: " + result);

        Logging.getmsg().info("Exiting methodC - INFO log");
    }

    private int performComplexCalculation() {
        // Simulating a complex calculation

        return BEST_NUMBER * 12;
    }

    private int performSimpleCalculation() {
        // Simulating a simple calculation
        return BEST_NUMBER + 12;
    }
}
