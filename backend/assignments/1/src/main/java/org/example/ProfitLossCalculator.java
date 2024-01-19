package org.example;

import java.util.List;
import java.util.Map;

/**
 * Utility class to calculate profit or loss for a trader.
 */
public class ProfitLossCalculator {

    /**
     * Calculate the profit or loss for a trader.
     *
     * @param traders        A list of Trader objects.
     * @param traderNameInput The name of the trader for whom to calculate profit or loss.
     * @return The calculated profit or loss.
     */
    public static double calculateTraderProfitLoss(List<Trader> traders, String traderNameInput) {
        double profitLoss = 0;

        // Find the specified trader
        Trader trader = getTraderByName(traders, traderNameInput);

        if (trader != null) {
            // Get the trader's portfolio
            Map<Coin, Integer> portfolio = trader.getPortfolio();

            // Calculate the total value of the trader's portfolio
            for (Map.Entry<Coin, Integer> entry : portfolio.entrySet()) {
                Coin coin = entry.getKey();
                int quantity = entry.getValue();
                double currentCoinValue = coin.getPrice() * quantity;
                profitLoss += currentCoinValue;
            }

            // Subtract the initial balance to get the profit or loss
            profitLoss -= trader.getBalance();
        } else {
            System.out.println("Trader not found.");
        }

        return profitLoss;
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
