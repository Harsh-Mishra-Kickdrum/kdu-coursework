package org.example;

import java.util.List;
import java.util.Map;

/**
 * Utility class to view a trader's portfolio.
 */
public class PortfolioViewer {

    /**
     * Display the portfolio of a trader.
     *
     * @param traders        A list of Trader objects.
     * @param traderNameInput The name of the trader whose portfolio to display.
     * @return TraderPortfolio object representing the trader's portfolio.
     */
    public static TraderPortfolio showTraderPortfolio(List<Trader> traders, String traderNameInput) {
        // Create a new TraderPortfolio object to store the trader's portfolio
        TraderPortfolio traderPortfolio = new TraderPortfolio();

        // Find the specified trader
        Trader trader = getTraderByName(traders, traderNameInput);

        if (trader != null) {
            // Get the trader's portfolio
            Map<Coin, Integer> portfolio = trader.getPortfolio();

            // Populate the TraderPortfolio object
            for (Map.Entry<Coin, Integer> entry : portfolio.entrySet()) {
                Coin coin = entry.getKey();
                int quantity = entry.getValue();
                CoinDetails coinDetails = new CoinDetails(coin.getName(), coin.getCode(), coin.getPrice());
                traderPortfolio.addCoin(coinDetails, quantity);
            }
        } else {
            System.out.println("Trader not found.");
        }

        return traderPortfolio;
    }

    /**
     * Find a trader by name in the list of traders.
     *
     * @param traders      A list of Trader objects.
     * @param traderName   The name of the trader to find.
     * @return The Trader object if found, otherwise null.
     */
    private static Trader getTraderByName(List<Trader> traders, String traderName) {
        for (Trader trader : traders) {
            if (trader.getName().equalsIgnoreCase(traderName)) {
                return trader;
            }
        }
        return null; // Return null if the trader is not found
    }
}
