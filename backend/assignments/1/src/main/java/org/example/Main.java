package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class for the Crypto Trading Application.
 */
public class Main {

    private static final Logger logger = Logging.logger;

    private static List<Coin> coins;
    private static List<Trader> traders;

    /**
     * Main method to initiate the Crypto Trading Application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // Step 1: Loading data from CSV files into memory
        loadCsvData();

        // Step 2: Loading JSON transaction data into memory
        JsonNode jsonTransactions = loadJsonTransactions("src/main/resources/test_transaction.json");

        // Step 3: Execute transactions using a thread pool
        assert jsonTransactions != null;
        executeTransactions(jsonTransactions);

        // Step 4: Menu-Driven Frontend
        displayMenu();
    }

    /**
     * Loads coin and trader data from CSV files into memory.
     */
    /**
     * Loads coin and trader data from CSV files into memory.
     */
    private static void loadCsvData() {
        String coinsFilePath = "src/main/resources/coins.csv";
        String tradersFilePath = "src/main/resources/traders.csv";

        System.out.println("Coins file path: " + new File(coinsFilePath).getAbsolutePath());
        System.out.println("Traders file path: " + new File(tradersFilePath).getAbsolutePath());

        try {
            coins = CsvLoader.loadCoins(coinsFilePath);
            traders = CsvLoader.loadTraders(tradersFilePath);

            if (coins == null || traders == null) {
                Logging.getMsg().error("Error loading CSV files. Make sure the file paths are correct.");
            }
        } catch (NullPointerException e) {
            Logging.getMsg().error("Error loading CSV files. Make sure the file paths are correct.", e);
        }
    }



    /**
     * Loads JSON transaction data from a file and returns the JsonNode.
     *
     * @return The JsonNode representing the loaded transactions.
     */
    private static JsonNode loadJsonTransactions(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readTree(file);
            } else {
                Logging.getMsg().error("JSON transactions file not found: {}", filePath);
                return null;
            }
        } catch (IOException e) {
            Logging.getMsg().error("Error loading JSON transactions from file: {}", filePath, e);
            return null;
        }
    }


    /**
     * Executes transactions using a thread pool.
     *
     * @param jsonTransactions The JsonNode containing transaction data.
     */
    private static void executeTransactions(JsonNode jsonTransactions) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(jsonTransactions.size());  // Add this line

        for (JsonNode transactionNode : jsonTransactions) {
            String transactionType = transactionNode.get("type").asText();
            Trader trader = getTrader(transactionNode);
            Coin coin = getCoin(transactionNode);

            ExecuteTransaction executeTransaction = new ExecuteTransaction(trader, coin, latch, 0, transactionType);  // Update this line
            executorService.submit(executeTransaction);
        }

        try {
            latch.await();  // Add this line to wait for all transactions to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logging.getMsg().error("Error waiting for transactions to finish", e);
        }

        executorService.shutdown();
    }


    /**
     * Displays a menu for the Crypto Trading Application and processes user input.
     */
    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Crypto Trading Application Menu ===");
            System.out.println("1. Retrieve Coin Details");
            System.out.println("2. Display Top N Coins");
            System.out.println("3. Show Trader's Portfolio");
            System.out.println("4. Display Trader's Profit/Loss");
            System.out.println("5. Show Top/Bottom Traders");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    retrieveCoinDetails(getUserInput("Enter coin name or code: "));
                    break;
                case 2:
                    displayTopCoins(getUserInput("Enter the number of top coins to display: "));
                    break;
                case 3:
                    showTraderPortfolio(getUserInput("Enter trader's name: "));
                    break;
                case 4:
                    displayTraderProfitLoss(getUserInput("Enter trader's name: "));
                    break;
                case 5:
                    displayTopBottomTraders(getUserInput("Enter the number of top/bottom traders to display: "));
                    break;
                case 0:
                    System.out.println("Exiting Crypto Trading Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    /**
     * Retrieves user input with the specified prompt, ensuring it is not empty.
     *
     * @param askForInput The prompt for user input.
     * @return The user-provided input.
     */
    private static String getUserInput(String askForInput) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.print(askForInput);
            userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                System.out.println("Error: Input cannot be empty. Please try again.");
            } else {
                break;
            }

        } while (true);

        return userInput;
    }

    /**
     * Retrieves and displays details for a specific coin.
     *
     * @param coinInput The user-provided coin name or code.
     */
    private static void retrieveCoinDetails(String coinInput) {
        CoinDetails coinDetails = CoinDetailsRetriever.retrieveCoinDetails(coins, coinInput);
        System.out.println("Coin Details Are : " + coinDetails);
    }

    /**
     * Displays the top N coins based on a user-provided count.
     *
     * @param topCountInput The user-provided count of top coins to display.
     */
    private static void displayTopCoins(String topCountInput) {
        int topCount = Integer.parseInt(topCountInput);
        List<Coin> topCoins = CoinRanking.displayTopCoins(coins, topCount);
        System.out.println("Top Coins: " + topCoins);
    }

    /**
     * Displays the portfolio of a specific trader.
     *
     * @param traderNameInput The user-provided trader name.
     */
    private static void showTraderPortfolio(String traderNameInput) {
        TraderPortfolio traderPortfolio = PortfolioViewer.showTraderPortfolio(traders, traderNameInput);
        System.out.println("Trader's Portfolio: " + traderPortfolio);
    }

    /**
     * Displays the profit/loss of a specific trader.
     *
     * @param traderNameInput The user-provided trader name.
     */
    private static void displayTraderProfitLoss(String traderNameInput) {
        double profitLoss = ProfitLossCalculator.calculateTraderProfitLoss(traders, traderNameInput);
        System.out.println("Trader's Profit/Loss: " + profitLoss);
    }

    /**
     * Displays the top and bottom traders based on a user-provided count.
     *
     * @param topBottomInput The user-provided count of top/bottom traders to display.
     */
    private static void displayTopBottomTraders(String topBottomInput) {
        int topBottomCount = Integer.parseInt(topBottomInput);
        TraderRanking.displayTopBottomTraders(traders, topBottomCount);
    }

    /**
     * Retrieves the coin associated with a transaction.
     *
     * @param transactionNode The JSON node representing the transaction.
     * @return The associated coin.
     */
    private static Coin getCoin(JsonNode transactionNode) {
        String coinName = transactionNode.get("coin").asText();
        return coins.stream()
                .filter(coin -> coin.getName().equals(coinName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves the trader associated with a transaction.
     *
     * @param transactionNode The JSON node representing the transaction.
     * @return The associated trader.
     */
    private static Trader getTrader(JsonNode transactionNode) {
        String traderName = transactionNode.get("trader").asText();
        return traders.stream()
                .filter(trader -> trader.getName().equals(traderName))
                .findFirst()
                .orElse(null);
    }
}
