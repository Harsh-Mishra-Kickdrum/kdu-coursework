// WatchList.tsx
import React, { useState } from "react";
import * as S from "./watchlist.styles";
import { Stock } from "../../../type/stocks";
import { useNavigate } from "react-router-dom"; 

interface WatchListProps {
  watchlist: Stock[];
  onRemoveFromWatchlist: (stockSymbol: string) => void;
  onAddToWatchlist: (stock: Stock) => void;
}

const ITEMS_PER_PAGE = 7;

/**
 * React functional component for displaying a watchlist of stocks.
 *
 * @param {WatchListProps} watchlist - the list of stocks to display
 * @param {Function} onRemoveFromWatchlist - function to remove a stock from the watchlist
 * @param {Function} onAddToWatchlist - function to add a stock to the watchlist
 * @return {JSX.Element} the JSX element representing the watchlist
 */


const WatchList: React.FC<WatchListProps> = ({
  watchlist,
  onRemoveFromWatchlist,
  onAddToWatchlist,
}) => {
  const [currentPage, setCurrentPage] = useState(1);
  const navigate = useNavigate(); // Initialize useNavigate

  const indexOfLastItem = currentPage * ITEMS_PER_PAGE;
  const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
  const currentItems = watchlist.slice(indexOfFirstItem, indexOfLastItem);

  const totalPages = Math.ceil(watchlist.length / ITEMS_PER_PAGE);

  /**
   * A function that handles the click event.
   *
   * @param {number} page - the page number to set
   * @return {void}
   */

  const handleClick = (page: number) => {
    setCurrentPage(page);
  };

  /**
   * A function to handle navigation to stock detail page.
   *
   * @param {string} stockSymbol - the stock symbol to navigate to
   * @return {void}
   */

  const handleNavigateToStockDetail = (stockSymbol: string) => {
    navigate(`/stock/${stockSymbol}`); // Navigate to StockDetail page
  };



    /**
   * Handles removing a stock from the watchlist and calling the appropriate callback.
   *
   * @param {string} stockSymbol - The symbol of the stock to be removed from the watchlist
   * @return {void} 
   */
  
  const handleRemoveFromWatchlist = (stockSymbol: string) => {
    onRemoveFromWatchlist(stockSymbol);
    const removedStock = watchlist.find(
      (stock) => stock.stock_symbol === stockSymbol
    );
    if (removedStock) {
      // Call the callback to handle addition to the watchlist in StockList
      onAddToWatchlist(removedStock);
    }
  };

  return (
    <S.WatchListContainer>
      <S.Table>
        <thead>
          <tr>
            <th>Stock Name</th>
            <th>Base Price</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {currentItems.map((stock) => (
            <tr
              key={stock.stock_symbol}
              onClick={() => handleNavigateToStockDetail(stock.stock_symbol)}
              style={{ cursor: "pointer" }}
            >
              <td>{stock.stock_name}</td>
              <td>₹{stock.base_price}</td>
              <td>
                <S.RemoveFromWatchlistButton
                  onClick={(e) => {
                    e.stopPropagation();
                    handleRemoveFromWatchlist(stock.stock_symbol);
                  }}
                >
                  <i
                    className="pi pi-times"
                    style={{
                      fontSize: "1rem",
                      color: "darkred",
                      fontWeight: 800,
                      backgroundColor: "transparent",
                    }}
                  ></i>
                </S.RemoveFromWatchlistButton>
              </td>
            </tr>
          ))}
        </tbody>
        <S.Pagination>
          <S.Arrow
            onClick={() => handleClick(currentPage - 1)}
            disabled={currentPage === 1}
          >
            {"<"}
          </S.Arrow>
          <ul>
            {[...Array(totalPages)].map((_, index) => (
              <li
                key={index + 1}
                onClick={() => handleClick(index + 1)}
                className={currentPage === index + 1 ? "active" : ""}
              >
                {index + 1}
              </li>
            ))}
          </ul>
          <S.Arrow
            onClick={() => handleClick(currentPage + 1)}
            disabled={currentPage === totalPages}
          >
            {">"}
          </S.Arrow>
        </S.Pagination>
      </S.Table>
    </S.WatchListContainer>
  );
};

export default WatchList;
