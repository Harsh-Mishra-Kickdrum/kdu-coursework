package org.example;

import java.util.List;
import java.util.Optional;

/**
 * Utility class to retrieve details of a cryptocurrency coin.
 */
public class CoinDetailsRetriever {

    /**
     * Retrieve details of a coin based on the provided input.
     *
     * @param coins      A list of Coin objects.
     * @param coinInput  The input (name or code) to identify the coin.
     * @return CoinDetails object containing details of the specified coin.
     */
    public static CoinDetails retrieveCoinDetails(List<Coin> coins, String coinInput) {
        Optional<Coin> matchingCoin = findCoin(coins, coinInput);

        if (matchingCoin.isPresent()) {
            Coin coin = matchingCoin.get();
            return new CoinDetails(coin.getName(), coin.getCode(), coin.getPrice());
        } else {
            System.out.println("Coin not found.");
            return null;
        }
    }

    /**
     * Find a coin by name or code in the list of coins.
     *
     * @param coins     A list of Coin objects.
     * @param coinInput The name or code of the coin to find.
     * @return Optional containing the Coin object if found, otherwise empty.
     */
    private static Optional<Coin> findCoin(List<Coin> coins, String coinInput) {
        return coins.stream()
                .filter(coin -> coin.getName().equalsIgnoreCase(coinInput) || coin.getCode().equalsIgnoreCase(coinInput))
                .findFirst();
    }
}
