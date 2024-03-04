/* eslint-disable @typescript-eslint/no-unused-vars */
// src/features/Summarizer/Summarizer.tsx
import React, { useEffect, useState } from 'react';
import {  Card, CardContent, Typography } from '@mui/material';
import * as S from './Summarizer.styles';
import { Loader } from '../../components/Loader/Loader.styles';

interface Stock {
  company: string;
  symbol: string;
  data: { date: string; prices: number[] }[];
}

interface StockSummary {
  company: string;
  symbol: string;
  buyDate: string;
  sellDate: string;
  buyPrice: number;
  sellPrice: number;
  maxProfit: number;
}


/**
 * Fetches stock data and calculates stock summaries for display.
 *
 * @return {JSX.Element} The JSX for the stock summaries display.
 */



const Summarizer: React.FC = () => {
  const [loading, setLoading] = useState(true);
  const [stockSummaries, setStockSummaries] = useState<StockSummary[]>([]);

  useEffect(() => {
    /**
     * Asynchronous function to fetch and process stock data.
     *
     * @return {void} No return value
     */
     
    const fetchData = async () => {
      try {
        console.log("Fetching data...");
        const response = await fetch(
          "https://dev-9x9ul9opv3e623n.api.raw-labs.com/mock/4"
        );
        const data: Stock[] = await response.json();
        console.log(data);

        for (let i = 0; i < 10; i++) {
          const stock = data[i];

          if (stock.data && stock.data.length > 0) {
            const worker = new Worker("/service-worker.js");

            worker.addEventListener("message", (event) => {
              if (event.data.type === "maxProfitCalculated") {
                const { buyIndex, sellIndex, buyPrice, sellPrice, maxProfit } =
                  event.data.payload;
                const buyDate = stock.data[0]?.date || ""; // Using the date from the first day
                const sellDate = stock.data[sellIndex]?.date || ""; // Using the date from the sell day

                const summary: StockSummary = {
                  company: stock.company,
                  symbol: stock.symbol,
                  buyDate,
                  sellDate,
                  buyPrice,
                  sellPrice,
                  maxProfit,
                };

                setStockSummaries((prevSummaries) => [
                  ...prevSummaries,
                  summary,
                ]);

                if (stockSummaries.length === 10) {
                  setLoading(false);
                }
              }
            });

            worker.postMessage({
              type: "calculateMaxProfit",
              payload: { prices: stock.data[0]?.prices || [] },
            });
          } else {
            console.error("Invalid data format for stock:", stock);
          }
        }
      } catch (error) {
        console.error("Error fetching data:", error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      {loading && <Loader />}
      {stockSummaries.map((summary, index) => (
        <Card
          key={index}
          style={{
            color: "white",
            margin: "16px",
            backgroundColor: summary.maxProfit > 244 ? "#2e7dc7" : "#e24444",
          }}
        >
          <CardContent>
            <S.CardContainer>
              <S.CardContainer2>
                <Typography variant="h5">
                  {summary.company} ({summary.symbol})
                </Typography>
                {summary.maxProfit > 244 ? (
                  <Typography>Profit Margin: ₹{summary.maxProfit}</Typography>
                ) : (
                  <Typography></Typography>
                )}
              </S.CardContainer2>
              <S.CardContainer1>
                {summary.maxProfit > 244 && (
                  <>
                    <Typography>
                      Sell : ₹{summary.sellPrice} on Date {summary.sellDate}
                    </Typography>
                    <Typography>
                      Buy : ₹{summary.buyPrice} on Date {summary.buyDate}
                    </Typography>
                  </>
                )}
              </S.CardContainer1>
            </S.CardContainer>
          </CardContent>
        </Card>
      ))}
    </div>
  );
};

export default Summarizer;
