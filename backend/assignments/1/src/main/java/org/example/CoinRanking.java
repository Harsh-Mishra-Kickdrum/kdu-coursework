package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to rank and display the top N coins.
 */
public class CoinRanking {

    /**
     * Display the top N coins.
     *
     * @param coins   A list of Coin objects.
     * @param topCount The number of top coins to display.
     * @return A list of top N Coin objects.
     */
    public static List<Coin> displayTopCoins(List<Coin> coins, int topCount) {
        // Assuming higher price means a higher rank
        List<Coin> topCoins = coins.stream()
                .sorted(Comparator.comparingDouble(Coin::getPrice).reversed())
                .limit(topCount)
                .collect(Collectors.toList());

        // Display the top N coins
        System.out.println("Top " + topCount + " Coins:");
        for (int i = 0; i < topCoins.size(); i++) {
            Coin coin = topCoins.get(i);
            System.out.println((i + 1) + ". " + coin.getName() + " - " + coin.getPrice());
        }

        return topCoins;
    }
}
