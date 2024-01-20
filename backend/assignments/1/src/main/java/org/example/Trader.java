package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/**
 * Represents a trader with attributes like name, wallet address, phone, address, and a portfolio of coins.
 */
public class Trader {
    private String name;
    private String walletAddress;
    private String phone;
    private String address;
    private List<Coin> portfolio;
    private double balance;

    /**
     * Constructor to initialize a Trader object.
     *
     * @param name          The name of the trader.
     * @param walletAddress The wallet address of the trader.
     * @param phone         The phone number of the trader.
     * @param address       The address of the trader.
     */
    public Trader(String name, String walletAddress, String phone, String address) {
        this.name = name;
        this.walletAddress = walletAddress;
        this.phone = phone;
        this.address = address;
        this.portfolio = new ArrayList<>();
        this.balance = 0;
    }

    // Getters and setters

    /**
     * Gets the name of the trader.
     *
     * @return The name of the trader.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a specified amount to the trader's balance.
     *
     * @param amount The amount to be added to the balance.
     */
    public void addToBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Gets the wallet address of the trader.
     *
     * @return The wallet address of the trader.
     */
    public String getWalletAddress() {
        return walletAddress;
    }

    /**
     * Gets the phone number of the trader.
     *
     * @return The phone number of the trader.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the address of the trader.
     *
     * @return The address of the trader.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the portfolio of the trader.
     *
     * @return The portfolio of the trader as a Map where the key is the Coin and the value is the quantity.
     */
    public Map<Coin, Integer> getPortfolio() {
        Map<Coin, Integer> portfolioMap = new HashMap<>();

        for (Coin coin : portfolio) {
            int quantity = coin.getVolume();
            portfolioMap.put(coin, quantity);
        }

        return portfolioMap;
    }

    /**
     * Deducts a specified amount from the trader's balance.
     *
     * @param amount The amount to be deducted.
     */
    public void deductFromBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Error: Insufficient balance.");
        }
    }

    /**
     * Gets the balance of the trader.
     *
     * @return The balance of the trader.
     */
    public double getBalance() {
        return balance;
    }

    // Methods

    /**
     * Adds a coin to the trader's portfolio.
     *
     * @param coin     The coin to be added.
     * @param quantity The quantity of the coin to be added.
     */
    public void addToPortfolio(Coin coin, int quantity) {
        for (int i = 0; i < quantity; i++) {
            portfolio.add(coin);
        }
    }

    /**
     * Removes a specified quantity of a coin from the trader's portfolio.
     *
     * @param coin     The coin to be removed.
     * @param quantity The quantity of the coin to be removed.
     */
    public void removeFromPortfolio(Coin coin, int quantity) {
        Iterator<Coin> iterator = portfolio.iterator();
        int count = 0;

        while (iterator.hasNext()) {
            Coin currentCoin = iterator.next();

            if (currentCoin.equals(coin)) {
                iterator.remove();
                count++;

                if (count == quantity) {
                    break;
                }
            }
        }
    }

    /**
     * Buys a specified quantity of a coin.
     *
     * @param coin     The coin to be bought.
     * @param quantity The quantity of the coin to be bought.
     */
    public void buyCoin(Coin coin, int quantity) {
        double cost = coin.getPrice() * quantity;

        if (balance >= cost) {
            deductFromBalance(cost);

            addToPortfolio(coin, quantity);
        } else {
            System.out.println("Error: Insufficient balance to buy " + quantity + " " + coin.getName() + ".");
        }
    }

    /**
     * Sells a specified quantity of a coin.
     *
     * @param coin     The coin to be sold.
     * @param quantity The quantity of the coin to be sold.
     */
    public void sellCoin(Coin coin, int quantity) {
        if (portfolio.contains(coin) && getPortfolio().get(coin) >= quantity) {
            removeFromPortfolio(coin, quantity);

            double proceeds = coin.getPrice() * quantity;
            addToBalance(proceeds);
        } else {
            System.out.println("Error: Insufficient quantity of " + coin.getName() + " to sell.");
        }
    }
}
