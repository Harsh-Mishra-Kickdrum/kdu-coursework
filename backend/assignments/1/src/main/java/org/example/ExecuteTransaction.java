package org.example;

import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;

/**
 * Handles the execution of different types of transactions (BUY, SELL, UPDATE_PRICE, ADD_VOLUME).
 * Implements the Runnable interface for concurrent execution by threads.
 */
public class ExecuteTransaction implements Runnable {

    private final Trader trader;
    private final Transaction transaction;  // Updated to use Transaction class
    private final CountDownLatch latch;

    /**
     * Initializes the ExecuteTransaction with required data.
     *
     * @param trader      The trader involved in the transaction.
     * @param transaction The transaction object.
     * @param latch       The CountDownLatch for synchronization.
     */
    public ExecuteTransaction(Trader trader, Transaction transaction, CountDownLatch latch) {
        this.trader = trader;
        this.transaction = transaction;
        this.latch = latch;
    }

    /**
     * Executes the transaction based on its type.
     */
    @Override
    public void run() {
        try {
            // Your transaction processing logic goes here
            String transactionHash = getBlockHash(); // Generate a random transaction hash

            switch (transaction.getType()) {
                case BUY:
                    executeBuyTransaction(transaction, transactionHash);
                    break;
                case SELL:
                    executeSellTransaction(transaction, transactionHash);
                    break;
                case UPDATE_PRICE:
                    executeUpdatePriceTransaction(transaction, transactionHash);
                    break;
                case ADD_VOLUME:
                    executeAddVolumeTransaction(transaction, transactionHash);
                    break;
                default:
                    Logging.getMsg().error("Unknown transaction type: {}", transaction.getType());
            }
        } finally {
            // Count down the latch to signal completion
            latch.countDown();
        }
    }

    private void executeBuyTransaction(Transaction transaction, String transactionHash) {
        if (trader.getBalance() >= transaction.getCoin().getCurrentPrice() * transaction.getQuantity()) {
            trader.addToPortfolio(transaction.getCoin(), transaction.getQuantity());
            trader.deductFromBalance(transaction.getCoin().getCurrentPrice() * transaction.getQuantity());
            Logging.getMsg().info("BUY: {} units of {} by {} for ${} Transaction Hash: {}",
                    transaction.getQuantity(), transaction.getCoin().getName(),
                    trader.getName(), transaction.getCoin().getCurrentPrice() * transaction.getQuantity(), transactionHash);
        } else {
            Logging.getMsg().warn("Insufficient funds for the BUY transaction. Transaction Hash: {}", transactionHash);
        }
    }

    private void executeSellTransaction(Transaction transaction, String transactionHash) {
        if (trader.getPortfolio().containsKey(transaction.getCoin())
                && trader.getPortfolio().get(transaction.getCoin()) >= transaction.getQuantity()) {
            trader.removeFromPortfolio(transaction.getCoin(), transaction.getQuantity());
            trader.addToBalance(transaction.getCoin().getCurrentPrice() * transaction.getQuantity());
            Logging.getMsg().info("SELL: {} units of {} by {} for ${} Transaction Hash: {}",
                    transaction.getQuantity(), transaction.getCoin().getName(),
                    trader.getName(), transaction.getCoin().getCurrentPrice() * transaction.getQuantity(), transactionHash);
        } else {
            Logging.getMsg().warn("Insufficient quantity of {} for the SELL transaction. Transaction Hash: {}",
                    transaction.getCoin().getName(), transactionHash);
        }
    }

    private void executeUpdatePriceTransaction(Transaction transaction, String transactionHash) {
        double newPrice = transaction.getCoin().getCurrentPrice() * 1.05; // Increase price by 5%
        transaction.getCoin().setCurrentPrice(newPrice);
        Logging.getMsg().info("UPDATE_PRICE: Set new price for {} to ${} Transaction Hash: {}",
                transaction.getCoin().getName(), newPrice, transactionHash);
    }

    private void executeAddVolumeTransaction(Transaction transaction, String transactionHash) {
        int additionalVolume = 100;
        transaction.getCoin().addVolume(additionalVolume);
        Logging.getMsg().info("ADD_VOLUME: Added {} units of volume to {} Transaction Hash: {}",
                additionalVolume, transaction.getCoin().getName(), transactionHash);
    }

    /**
     * Method generates the unique block hash required for transactions made using
     * the cryptocurrencies.
     *
     * @return - string representing the transaction hashcode
     */
    private String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (double i = 0; i < 199999999; i++) {
            i = i; // Introducing delay mimicking complex calculation being performed.
        }
        while (transactionHash.length() < 128) {
            int index = (int) (secureRandom.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
