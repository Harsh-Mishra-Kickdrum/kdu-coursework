package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class to rank and display the top and bottom traders.
 */
public class TraderRanking {

    /**
     * Display the top and bottom traders.
     *
     * @param traders        A list of Trader objects.
     * @param topBottomCount The number of top/bottom traders to display.
     */
    public static void displayTopBottomTraders(List<Trader> traders, int topBottomCount) {
        if (traders.isEmpty()) {
            System.out.println("No traders available for ranking.");
            return;
        }

        // Sort the traders based on their balance in descending order
        Collections.sort(traders, Comparator.comparingDouble(Trader::getBalance).reversed());

        System.out.println("\n=== Top Traders ===");
        displayTraders(traders.subList(0, Math.min(topBottomCount, traders.size())));

        System.out.println("\n=== Bottom Traders ===");
        Collections.reverse(traders); // Reversing the list to get bottom traders
        displayTraders(traders.subList(0, Math.min(topBottomCount, traders.size())));
    }

    /**
     * Display information about a list of traders.
     *
     * @param traders A list of Trader objects.
     */
    private static void displayTraders(List<Trader> traders) {
        for (Trader trader : traders) {
            System.out.println("Name: " + trader.getName() + " | Balance: " + trader.getBalance());
        }
    }
}
