package org.example;

import org.example.Coin;
import org.example.Logging;
import org.example.Trader;

import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;

/**
 * Handles the execution of different types of transactions (BUY, SELL, UPDATE_PRICE, ADD_VOLUME).
 * Implements the Runnable interface for concurrent execution by threads.
 */
public class ExecuteTransaction implements Runnable {

    private final Trader trader;
    private final Coin coin;
    private final CountDownLatch latch;
    private final int quantity;
    private final String transactionType;

    /**
     * Initializes the ExecuteTransaction with required data.
     *
     * @param trader          The trader involved in the transaction.
     * @param coin            The coin related to the transaction.
     * @param latch           The CountDownLatch for synchronization.
     * @param quantity        The quantity of the coin involved.
     * @param transactionType The type of transaction (BUY, SELL, UPDATE_PRICE, ADD_VOLUME).
     */
    public ExecuteTransaction(Trader trader, Coin coin, CountDownLatch latch, int quantity, String transactionType) {
        this.trader = trader;
        this.coin = coin;
        this.latch = latch;
        this.quantity = quantity;
        this.transactionType = transactionType;
    }

    /**
     * Executes the transaction based on its type.
     */
    @Override
    public void run() {
        try {
            // Your transaction processing logic goes here
            String transactionHash = getBlockHash(); // Generate a random transaction hash

            switch (transactionType) {
                case "BUY":
                    executeBuyTransaction(transactionHash);
                    break;
                case "SELL":
                    executeSellTransaction(transactionHash);
                    break;
                case "UPDATE_PRICE":
                    executeUpdatePriceTransaction(transactionHash);
                    break;
                case "ADD_VOLUME":
                    executeAddVolumeTransaction(transactionHash);
                    break;
                default:
                    Logging.getMsg().error("Unknown transaction type: {}", transactionType);
            }
        } finally {
            // Count down the latch to signal completion
            latch.countDown();
        }
    }

    private void executeBuyTransaction(String transactionHash) {
        if (trader.getBalance() >= coin.getCurrentPrice() * quantity) {
            trader.addToPortfolio(coin, quantity);
            trader.deductFromBalance(coin.getCurrentPrice() * quantity);
            Logging.getMsg().info("BUY: {} units of {} by {} for ${} Transaction Hash: {}",
                    quantity, coin.getName(), trader.getName(), coin.getCurrentPrice() * quantity, transactionHash);
        } else {
            Logging.getMsg().warn("Insufficient funds for the BUY transaction. Transaction Hash: {}", transactionHash);
        }
    }

    private void executeSellTransaction(String transactionHash) {
        if (trader.getPortfolio().containsKey(coin) && trader.getPortfolio().get(coin) >= quantity) {
            trader.removeFromPortfolio(coin, quantity);
            trader.addToBalance(coin.getCurrentPrice() * quantity);
            Logging.getMsg().info("SELL: {} units of {} by {} for ${} Transaction Hash: {}",
                    quantity, coin.getName(), trader.getName(), coin.getCurrentPrice() * quantity, transactionHash);
        } else {
            Logging.getMsg().warn("Insufficient quantity of {} for the SELL transaction. Transaction Hash: {}",
                    coin.getName(), transactionHash);
        }
    }

    private void executeUpdatePriceTransaction(String transactionHash) {
        double newPrice = coin.getCurrentPrice() * 1.05; // Increase price by 5%
        coin.setCurrentPrice(newPrice);
        Logging.getMsg().info("UPDATE_PRICE: Set new price for {} to ${} Transaction Hash: {}",
                coin.getName(), newPrice, transactionHash);
    }

    private void executeAddVolumeTransaction(String transactionHash) {
        int additionalVolume = 100;
        coin.addVolume(additionalVolume);
        Logging.getMsg().info("ADD_VOLUME: Added {} units of volume to {} Transaction Hash: {}",
                additionalVolume, coin.getName(), transactionHash);
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
