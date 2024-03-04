import React, { createContext, useContext, useState, ReactNode } from "react";

/**
 * Defines the structure of a transaction within the stock market context.
 */
interface Transaction {
  type: "BUY" | "SELL";
  stockSymbol: string;
  stockPrice: number;
  date: string;
}

/**
 * Defines the structure of a notification message.
 */
interface Notification {
  message: string;
}

/**
 * Defines the types of context provided for stock operations.
 */
interface StockContextType {
  walletBalance: number;
  transactions: Transaction[];
  notifications: Notification[];
  buyStock: (stockSymbol: string, stockPrice: number) => void;
  sellStock: (stockSymbol: string, stockPrice: number) => void;
}

const StockContext = createContext<StockContextType | undefined>(undefined);

interface StockProviderProps {
  children: ReactNode;
}

/**
 * Custom hook to use the stock context.
 * @throws {Error} If called outside of the StockProvider component.
 * @returns {StockContextType} The stock context.
 */
export const useStocks = () => {
  const context = useContext(StockContext);
  if (context === undefined) {
    throw new Error("useStocks must be used within a StockProvider");
  }
  return context;
};

/**
 * Provides a context for managing stock transactions and wallet balance.
 * @param {StockProviderProps} props The children elements to be rendered within this provider.
 * @returns {React.FC} A react functional component.
 */
export const StockProvider: React.FC<StockProviderProps> = ({ children }) => {
  const [walletBalance, setWalletBalance] = useState<number>(1000); // Initial wallet balance
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [notifications, setNotifications] = useState<Notification[]>([]);

  /**
   * Handles buying a stock and updates the wallet balance, transactions, and notifications.
   * @param {string} stockSymbol The symbol of the stock to buy.
   * @param {number} stockPrice The price of the stock.
   */
  const buyStock = (stockSymbol: string, stockPrice: number) => {
    if (walletBalance >= stockPrice) {
      setWalletBalance(walletBalance - stockPrice);
      const newTransaction: Transaction = {
        type: "BUY",
        stockSymbol,
        stockPrice,
        date: new Date().toISOString(),
      };
      setTransactions([...transactions, newTransaction]);
      setNotifications([
        ...notifications,
        { message: `Bought ${stockSymbol} at ${stockPrice}` },
      ]);
    } else {
      alert("You don't have enough balance");
    }
  };

  /**
   * Handles selling a stock and updates the wallet balance, transactions, and notifications.
   * @param {string} stockSymbol The symbol of the stock to sell.
   * @param {number} stockPrice The price at which the stock is sold.
   */
  const sellStock = (stockSymbol: string, stockPrice: number) => {
    setWalletBalance(walletBalance + stockPrice);
    const newTransaction: Transaction = {
      type: "SELL",
      stockSymbol,
      stockPrice,
      date: new Date().toISOString(),
    };
    setTransactions([...transactions, newTransaction]);
    setNotifications([
      ...notifications,
      { message: `Sold ${stockSymbol} at ${stockPrice}` },
    ]);
  };

  return (
    <StockContext.Provider
      value={{
        buyStock,
        sellStock,
        transactions,
        notifications,
        walletBalance,
      }}
    >
      {children}
    </StockContext.Provider>
  );
};
