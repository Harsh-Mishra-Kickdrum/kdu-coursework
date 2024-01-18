# Crypto Trading Application

## Overview
The Crypto Trading Application is a Java-based program designed to simulate cryptocurrency trading. It incorporates features such as loading data from CSV files, processing transactions, and providing a menu-driven frontend for user interaction.

## Project Structure
The project is organized into several classes, each serving a specific purpose. Here's an overview of the main classes:

1. **Main Class (Main.java)**
    - Entry point of the application.
    - Loads data from CSV files and JSON transactions.
    - Executes transactions using a thread pool.
    - Displays a menu-driven frontend for user interaction.

2. **CsvLoader Class (CsvLoader.java)**
    - `loadCoins(String filePath): List<Coin>` - Load a list of coins from a CSV file.
    - `loadTraders(String filePath): List<Trader>` - Load a list of traders from a CSV file.

3. **ExecuteTransaction Class (ExecuteTransaction.java)**
    - `run(): void` - Executes the transaction based on its type.
    - `executeBuyTransaction(String transactionHash): void` - Handles the execution of a BUY transaction.
    - `executeSellTransaction(String transactionHash): void` - Handles the execution of a SELL transaction.
    - `executeUpdatePriceTransaction(String transactionHash): void` - Handles the execution of an UPDATE_PRICE transaction.
    - `executeAddVolumeTransaction(String transactionHash): void` - Handles the execution of an ADD_VOLUME transaction.
    - `getBlockHash(): String` - Generates a unique block hash required for transactions.

4. **CoinRanking Class (CoinRanking.java)**
    - `displayTopCoins(List<Coin> coins, int topCount): List<Coin>` - Displays the top N coins.

5. **Logging Class (Logging.java)**
    - `getMsg(): Logger` - Retrieves the logger instance.

6. **PortfolioViewer Class (PortfolioViewer.java)**
    - `showTraderPortfolio(List<Trader> traders, String traderNameInput): TraderPortfolio` - Displays the portfolio of a trader.

7. **ProfitLossCalculator Class (ProfitLossCalculator.java)**
    - `calculateTraderProfitLoss(List<Trader> traders, String traderNameInput): double` - Calculates the profit or loss for a trader.

8. **Trader Class (Trader.java)**
    - Various methods for managing the trader's portfolio, balance, and handling transactions.

9. **TraderRanking Class (TraderRanking.java)**
    - `displayTopBottomTraders(List<Trader> traders, int topBottomCount): void` - Displays the top and bottom traders.

## How to Run
1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the Main class to start the Crypto Trading Application.
4. Follow the menu prompts to interact with the application.

## Dependencies
The project uses the following external libraries:

```xml
<!-- Logback -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.3.5</version>
</dependency>

<!-- Jackson Databind -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.8.5</version>
</dependency>

<!-- OpenCSV -->
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.5</version> <!-- Use the latest version available -->
</dependency>
```

## Sample Flow
1. The Main class loads data from CSV files into memory.
2. It then loads JSON transaction data and executes transactions using a thread pool.
3. The menu-driven frontend is displayed, allowing users to interact with the application.
4. Users can retrieve coin details, display top coins, view a trader's portfolio, and perform other operations.
5. The application uses SLF4J for logging messages.

## Structure of the Directory
```css
Structure of the Directory
CryptoTradingApplication/
│
├── src/
│   ├── org/
│   │   └── example/
│   │       ├── Coin.java
│   │       ├── CoinRanking.java
│   │       ├── CsvLoader.java
│   │       ├── ExecuteTransaction.java
│   │       ├── Logging.java
│   │       ├── Main.java
│   │       ├── PortfolioViewer.java
│   │       ├── ProfitLossCalculator.java
│   │       ├── Trader.java
│   │       ├── TraderRanking.java
│   │       └── ...
│   └── ...
│
├── resources/
│   ├── coins.csv
│   ├── small_transaction.json
│   ├── medium_transaction.json
│   └── traders.csv
│
├── target/
├── .gitignore
├── pom.xml
├── README.md
```

##  Author
 Harsh Mishra 