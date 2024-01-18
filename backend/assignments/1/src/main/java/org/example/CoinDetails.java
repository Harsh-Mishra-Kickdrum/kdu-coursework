package org.example;

/**
 * Represents details of a cryptocurrency coin.
 */
public class CoinDetails {

    private String name;
    private String code;
    private double price;

    /**
     * Constructor to initialize a CoinDetails object.
     *
     * @param name  The name of the coin.
     * @param code  The code or symbol of the coin.
     * @param price The current price of the coin.
     */
    public CoinDetails(String name, String code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

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
     * Returns a string representation of the CoinDetails object.
     *
     * @return String representation of the CoinDetails object.
     */
    @Override
    public String toString() {
        return "CoinDetails{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                '}';
    }

}
