// Dashboard.tsx
import React, { useState, useEffect } from 'react';
import StockList from './Stocklist/StockList';
import WatchList from './Watchlist/WatchList';
import * as S from './Dashboard.styles';
import { getWatchlistFromStorage, updateWatchlistInStorage } from '../../utils/LocalStoragePersistenceFunctions';
import { Stock } from '../../type/stocks';
import LoaderComponent from '../../components/Loader/Loader';


/**
 * Fetches data from a specified API endpoint with exponential backoff retry logic.
 *
 * @return {void}
 */

const Dashboard: React.FC = () => {
  const [stocks, setStocks] = useState<Stock[]>([]);
  const [watchlist, setWatchlist] = useState<Stock[]>(getWatchlistFromStorage());
  const [loading, setLoading] = useState<boolean>(true);
  const [activeTab, setActiveTab] = useState<'Explore' | 'My WatchList'>('Explore');

  useEffect(() => {
    /**
     * Fetches data from a specified API endpoint with exponential backoff retry logic.
     *
     * @return {void}
     */

    const fetchData = async () => {
      let retryCount = 0;
      let success = false;

      while (retryCount < 3 && !success) {
        try {
          const response = await fetch(
            "https://dev-9x9ul9opv3e623n.api.raw-labs.com/mock/2"
          );

          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }

          const data: Stock[] = await response.json();
          setStocks(data);
          setLoading(false);
          success = true;
        } catch (error) {
          console.error("Error fetching data:", error);
          retryCount++;
         
          await new Promise((resolve) =>
            setTimeout(resolve, Math.pow(2, retryCount) * 1000)
          );
        }
      }
    };

    fetchData();
  }, []);

  const addToWatchlist = (stock: Stock) => {
    if (!watchlist.find((item) => item.stock_symbol === stock.stock_symbol)) {
      const newWatchlist = [...watchlist, stock];
      setWatchlist(newWatchlist);
      updateWatchlistInStorage(newWatchlist);
      // Update stock list to reflect watchlist status
      const updatedStocks = stocks.map((s) =>
        s.stock_symbol === stock.stock_symbol ? { ...s, addedToWatchlist: true } : s
      );
      setStocks(updatedStocks);
    }
  };

  const removeFromWatchlist = (stockSymbol: string) => {
    const newWatchlist = watchlist.filter((item) => item.stock_symbol !== stockSymbol);
    setWatchlist(newWatchlist);
    updateWatchlistInStorage(newWatchlist);
    // Update stock list to reflect watchlist status
    const updatedStocks = stocks.map((s) =>
      s.stock_symbol === stockSymbol ? { ...s, addedToWatchlist: false } : s
    );
    setStocks(updatedStocks);
  };

  return (
    <>
      <LoaderComponent loading={loading} />
      <S.DashboardContainer>
        <S.Tabs>
          <S.Tab active={activeTab === 'Explore'} onClick={() => setActiveTab('Explore')}>
            Explore
          </S.Tab>
          <S.Tab active={activeTab === 'My WatchList'} onClick={() => setActiveTab('My WatchList')}>
            My WatchList
          </S.Tab>
        </S.Tabs>
        {loading ? (
          <S.Spinner></S.Spinner>
        ) : (
          <>
            <S.TabContent active={activeTab === 'Explore'}>
              <StockList stocks={stocks} onAddToWatchlist={addToWatchlist} onRemoveFromWatchlist={removeFromWatchlist} />
            </S.TabContent>

            <S.TabContent active={activeTab === 'My WatchList'}>
             
              <WatchList watchlist={watchlist} onRemoveFromWatchlist={removeFromWatchlist} onAddToWatchlist={addToWatchlist} />
            </S.TabContent>
          </>
        )}
      </S.DashboardContainer>
    </>
  );
};

export default Dashboard;
