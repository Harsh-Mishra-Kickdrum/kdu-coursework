package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to load CSV data.
 */
public class CsvLoader {

    /**
     * Load a list of coins from a CSV file.
     *
     * @param filePath The path to the CSV file containing coin data.
     * @return A list of Coin objects.
     */
    public static List<Coin> loadCoins(String filePath) {
        List<Coin> coins = new ArrayList<>();
        System.out.println(CsvLoader.class.getClassLoader().getResourceAsStream(filePath));
        try (InputStream inputStream = CsvLoader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream != null) {
                CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                List<String[]> data = reader.readAll();

                for (int i = 1; i < data.size(); i++) {
                    String[] coinData = data.get(i);

                    if (coinData.length >= 4) {
                        try {
                            String name = coinData[0];
                            String code = coinData[1];
                            double price = Double.parseDouble(coinData[2]);
                            int volume = Integer.parseInt(coinData[3]);

                            Coin coin = new Coin(name, code, price, volume);
                            coins.add(coin);
                        } catch (NumberFormatException e) {
                            Logging.getMsg().error("Error parsing data for coin at index {}: {}", i, e.getMessage());
                        }
                    }
                }
            } else {
                Logging.getMsg().error("Input stream is null for file: {}", filePath);
            }
        } catch (IOException | CsvException e) {
            Logging.getMsg().error("Error loading coins from CSV file: {}", filePath, e);
        }

        return coins;
    }

    /**
     * Load a list of traders from a CSV file.
     *
     * @param filePath The path to the CSV file containing trader data.
     * @return A list of Trader objects.
     */
    public static List<Trader> loadTraders(String filePath) {
        List<Trader> traders = new ArrayList<>();

        try (InputStream inputStream = CsvLoader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream != null) {
                CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

                List<String[]> data = reader.readAll();

                for (int i = 1; i < data.size(); i++) {
                    String[] traderData = data.get(i);

                    if (traderData.length >= 4) {
                        String firstName = traderData[0];
                        String lastName = traderData[1];
                        String phone = traderData[2];
                        String walletAddress = traderData[3];

                        Trader trader = new Trader(firstName, lastName, phone, walletAddress);
                        traders.add(trader);
                    }
                }
            } else {
                Logging.getMsg().error("Input stream is null for file: {}", filePath);
            }
        } catch (IOException | CsvException | NumberFormatException e) {
            Logging.getMsg().error("Error loading traders from CSV file: {}", filePath, e);
        }

        return traders;
    }
}
