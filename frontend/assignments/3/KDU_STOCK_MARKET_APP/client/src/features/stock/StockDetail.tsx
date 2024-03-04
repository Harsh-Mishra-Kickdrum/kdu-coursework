import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useStocks } from "../../Context/StockContext";
import { Loader } from "../../components/Loader/Loader.styles";
import { Chart, LinearScale } from "chart.js";
Chart.register(LinearScale);

import {
  Container,
  StockInfoContainer,
  HistoryContainer,
  NotificationContainer,
  DetailsContainer,
  DetailBox,
  GraphContainer,
  SideContainer,
  PriceIndicator,
  ActionButton,
  QuantityInput,
} from "./StockStyles";

interface StockDetailType {
  stock_name: string;
  stock_symbol: string;
  base_price: number;
}

/**
 * React functional component for displaying stock details and enabling stock transactions.
 *
 * @return {JSX.Element} The JSX element containing stock details and transaction functionality.
 */

const StockDetail: React.FC = () => {
  const { stockSymbol } = useParams<{ stockSymbol: string }>();
  const [stockDetail, setStockDetail] = useState<StockDetailType | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [userName, setUserName] = useState<string | null>(null);
  const [allStocks, setAllStocks] = useState<StockDetailType[]>([]);

  const { buyStock, sellStock, walletBalance } = useStocks();

  useEffect(() => {
    /**
     * Fetches stock details and sets the state with the fetched data,
     * handling errors and loading state as well.
     *
     * @param {}
     * @return {}
     */
    const fetchStockDetails = async () => {
      if (!userName) return; // Ensure userName is set before fetching

      try {
        const response = await fetch(
          "https://dev-9x9ul9opv3e623n.api.raw-labs.com/mock/dashboard"
        );
        if (!response.ok) throw new Error("Stocks fetch failed");
        const data: StockDetailType[] = await response.json();

        setAllStocks(data); // Assuming the endpoint returns an array of stocks
        const detail = data.find((d) => d.stock_symbol === stockSymbol);
        setStockDetail(detail ?? null);
      } catch (error) {
        setError(
          error instanceof Error ? error.message : "An unknown error occurred"
        );
      } finally {
        setLoading(false);
      }
    };

    if (!userName) {
      const promptedName = window.prompt(
        "Please enter your name for transaction history:"
      );
      if (promptedName !== null) {
        setUserName(promptedName);
        fetchStockDetails();
      } else {
        setLoading(false); // Stop loading if user cancels prompt
      }
    } else {
      fetchStockDetails();
    }
  }, [stockSymbol, userName]);

  /**
   * Function to handle the buying process.
   *
   * @param {} -
   * @return {}
   */

  const handleBuy = () => {
    if (!userName) {
      alert("Please enter your name to proceed with the transaction.");
      return;
    }

    if (stockSymbol && stockDetail && walletBalance >= stockDetail.base_price) {
      buyStock(stockSymbol, stockDetail.base_price);
      alert(
        `${userName}, you have bought ${stockSymbol} at ₹${stockDetail.base_price}.`
      );
    } else {
      alert(
        `Sorry ${userName}, you do not have enough balance to buy ${stockSymbol}.`
      );
    }
  };

  /**
   * Function to handle the selling of stocks.
   */

  const handleSell = () => {
    if (!userName) {
      alert("Please enter your name to proceed with the transaction.");
      return;
    }

    if (stockSymbol && stockDetail) {
      sellStock(stockSymbol, stockDetail.base_price);
      alert(
        `${userName}, you have sold ${stockSymbol} at ₹${stockDetail.base_price}.`
      );
    } else {
      console.error("Stock information is not available.");
    }
  };

  if (loading) return <Loader />;
  if (error) return <div>Error: {error}</div>;
  if (!stockDetail) return <div>Stock not found</div>;

  return (
    <Container>
      <StockInfoContainer>
        <DetailsContainer>
          <DetailBox>
            <div>
              <label htmlFor="stockSelect"></label>
              <select id="stockSelect" aria-label="Select Stock">
                {allStocks.map((stock, index) => (
                  <option key={index} value={stock.stock_symbol}>
                    {stock.stock_symbol}
                    {"        "} --- {stock.stock_name}
                    {"    "}
                    {/* Modified to add a dash for visual separation */}
                  </option>
                ))}
              </select>
            </div>
          </DetailBox>
          <DetailBox>
            <PriceIndicator>
              Price ₹{stockDetail?.base_price}
              <svg width="20" height="20" viewBox="0 0 24 24">
                <path d="M5 15l7-7 7 7H5z" />
              </svg>
            </PriceIndicator>
          </DetailBox>

          <label></label>

          <QuantityInput type="number" placeholder="Enter quantity" />
          <ActionButton buy onClick={handleBuy}>
            Buy
          </ActionButton>
          <ActionButton onClick={handleSell}>Sell</ActionButton>
        </DetailsContainer>
        <GraphContainer>
          Graph Container <Loader />
          {/* <Bar data={data} options={options} /> */}
        </GraphContainer>
      </StockInfoContainer>
      <SideContainer>
        <HistoryContainer> History</HistoryContainer>
        <NotificationContainer>Live Notifications </NotificationContainer>
      </SideContainer>
    </Container>
  );
};
export default StockDetail;
