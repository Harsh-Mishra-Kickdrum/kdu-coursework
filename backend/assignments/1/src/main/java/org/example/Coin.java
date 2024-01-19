package org.example;

/**
 * Represents a cryptocurrency with attributes such as name, code, price, and volume.
 */
public class Coin {
    private String name;
    private String code;
    private double price;
    private int volume;

    /**
     * Constructor to initialize a Coin object.
     *
     * @param name   The name of the coin.
     * @param code   The code or symbol of the coin.
     * @param price  The current price of the coin.
     * @param volume The available volume of the coin.
     */
    public Coin(String name, String code, double price, int volume) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.volume = volume;
    }

    // Getters and setters

    /**
     * Gets the name of the coin.
     *
     * @return The name of the coin.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the code or symbol of the coin.
     *
     * @return The code or symbol of the coin.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the current price of the coin.
     *
     * @return The current price of the coin.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the available volume of the coin.
     *
     * @return The available volume of the coin.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Gets the current price of the coin.
     *
     * @return The current price of the coin.
     */
    public double getCurrentPrice() {
        return price;
    }

    /**
     * Sets the current price of the coin.
     *
     * @param currentPrice The new current price of the coin.
     */
    public void setCurrentPrice(double currentPrice) {
        this.price = currentPrice;
    }

    /**
     * Updates the volume of the coin by a specified amount.
     *
     * @param change The change in volume (positive or negative).
     */
    public void updateVolume(int change) {
        this.volume += change;
    }

    /**
     * Adds a specified quantity to the available volume of the coin.
     *
     * @param additionalVolume The quantity to be added to the volume.
     */
    public void addVolume(int additionalVolume) {
        this.volume += additionalVolume;
    }

    /**
     * Subtracts a specified quantity from the available volume of the coin.
     *
     * @param quantity The quantity to be subtracted from the volume.
     */
    public void subtractFromVolume(int quantity) {
        if (this.volume >= quantity) {
            this.volume -= quantity;
        } else {
            System.out.println("Error: Attempted to subtract more volume than available.");
        }
    }
}
