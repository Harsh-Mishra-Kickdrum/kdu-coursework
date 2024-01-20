package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the portfolio of a trader.
 */
public class TraderPortfolio {

    private Map<CoinDetails, Integer> portfolio;

    /**
     * Constructor to initialize a TraderPortfolio object.
     */
    public TraderPortfolio() {
        this.portfolio = new HashMap<>();
    }

    /**
     * Add a coin to the portfolio with the specified quantity.
     *
     * @param coinDetails The details of the coin.
     * @param quantity    The quantity of the coin.
     */
    public void addCoin(CoinDetails coinDetails, int quantity) {
        portfolio.put(coinDetails, quantity);
    }

    /**
     * Get the portfolio as a map where the key is CoinDetails and the value is the quantity.
     *
     * @return The portfolio map.
     */
    public Map<CoinDetails, Integer> getPortfolio() {
        return portfolio;
    }

    /**
     * Override toString method to provide a meaningful string representation of the portfolio.
     *
     * @return String representation of the portfolio.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("TraderPortfolio{");
        for (Map.Entry<CoinDetails, Integer> entry : portfolio.entrySet()) {
            result.append(entry.getKey()).append(" - Quantity: ").append(entry.getValue()).append(", ");
        }
        // Remove the trailing comma and space
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }
        result.append("}");
        return result.toString();
    }
}
