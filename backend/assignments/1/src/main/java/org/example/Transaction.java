package org.example;
/**
 * Represents a transaction with attributes for type, associated coin, quantity, and wallet address.
 */
public class Transaction {
    public enum TransactionType {
        BUY, SELL, UPDATE_PRICE, ADD_VOLUME
    }

    private TransactionType type;
    private Coin coin;
    private int quantity;
    private String walletAddress;

    /**
     * Constructor to initialize a Transaction object.
     *
     * @param type          The type of the transaction (BUY, SELL, UPDATE_PRICE, ADD_VOLUME).
     * @param coin          The associated coin for the transaction.
     * @param quantity      The quantity of the transaction.
     * @param walletAddress The wallet address associated with the transaction.
     */
    public Transaction(TransactionType type, Coin coin, int quantity, String walletAddress) {
        this.type = type;
        this.coin = coin;
        this.quantity = quantity;
        this.walletAddress = walletAddress;
    }

    // Getters

    /**
     * Gets the type of the transaction.
     *
     * @return The type of the transaction.
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Gets the associated coin for the transaction.
     *
     * @return The associated coin for the transaction.
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * Gets the quantity of the transaction.
     *
     * @return The quantity of the transaction.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the wallet address associated with the transaction.
     *
     * @return The wallet address associated with the transaction.
     */
    public String getWalletAddress() {
        return walletAddress;
    }
}
