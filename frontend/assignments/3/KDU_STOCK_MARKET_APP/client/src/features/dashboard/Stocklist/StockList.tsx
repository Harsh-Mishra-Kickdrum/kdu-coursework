import "primeicons/primeicons.css";
import React, { useState, useEffect } from "react";
import * as S from "./stocklist.styles";
import { Stock } from "../../../type/stocks";
import {
  getWatchlistFromStorage,
  updateWatchlistInStorage,
} from "../../../utils/LocalStoragePersistenceFunctions";
import { useNavigate } from "react-router-dom";

interface StockListProps {
  stocks: Stock[];
  onAddToWatchlist: (stock: Stock) => void;
  onRemoveFromWatchlist: (stockSymbol: string) => void;
}

const ITEMS_PER_PAGE = 7;

/**
 * A React functional component for displaying a list of stocks with pagination and watchlist functionality.
 *
 * @param {StockListProps} stocks - the list of stocks to display
 * @param {Function} onAddToWatchlist - the function to add a stock to the watchlist
 * @param {Function} onRemoveFromWatchlist - the function to remove a stock from the watchlist
 * @return {JSX.Element} The JSX element representing the stock list component
 */

const StockList: React.FC<StockListProps> = ({
  stocks,
  onAddToWatchlist,
  onRemoveFromWatchlist,
}) => {
  const [currentPage, setCurrentPage] = useState(1);
  const [watchlist, setWatchlist] = useState<string[]>(
    getWatchlistFromStorage()
  ); // Initialize from local storage
  const [hoveredItem, setHoveredItem] = useState<string | null>(null);
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    // Update local storage when watchlist changes
    updateWatchlistInStorage(watchlist);
  }, [watchlist]);

  useEffect(() => {
    // Update watchlist when stocks change
    setWatchlist(getWatchlistFromStorage());
  }, [stocks]);

  const indexOfLastItem = currentPage * ITEMS_PER_PAGE;
  const indexOfFirstItem = indexOfLastItem - ITEMS_PER_PAGE;
  const currentItems = stocks.slice(indexOfFirstItem, indexOfLastItem);

  const totalPages = Math.ceil(stocks.length / ITEMS_PER_PAGE);

  /**
   * A function to handle navigation to the stock detail page.
   *
   * @param {string} stockSymbol - the stock symbol for navigation
   * @return {void}
   */

  const handleClickPage = (page: number) => {
    setCurrentPage(page);
    setHoveredItem(null);
  };

  /**
   * A function to handle navigation to the stock detail page.
   *
   * @param {string} stockSymbol - the stock symbol for navigation
   * @return {void}
   */

  const handleNavigateToStockDetail = (stockSymbol: string) => {
    navigate(`/stock/${stockSymbol}`); // Navigate to StockDetail page
  };

  /**
   * A function to handle the toggling of stock items in the watchlist.
   *
   * @param {React.MouseEvent<HTMLElement>} e - The click event triggering the function
   * @param {Stock} stock - The stock item to be toggled in the watchlist
   * @return {void}
   */

  const handleToggleWatchlist = (
    e: React.MouseEvent<HTMLElement>,
    stock: Stock
  ) => {
    e.stopPropagation(); // Prevent navigation when clicking the button
    const isAdded = watchlist.includes(stock.stock_symbol);

    if (isAdded) {
      const updatedWatchlist = watchlist.filter(
        (item) => item !== stock.stock_symbol
      );
      setWatchlist(updatedWatchlist);
      onRemoveFromWatchlist(stock.stock_symbol);
    } else {
      setWatchlist([...watchlist, stock.stock_symbol]);
      onAddToWatchlist(stock);
    }
  };

  /**
   * A function to handle hovering over an item.
   *
   * @param {string} stockSymbol - the stock symbol to be hovered over
   * @return {void}
   */

  const handleHoverItem = (stockSymbol: string) => {
    setHoveredItem(stockSymbol);
  };

  /**
   * Handles the removal of an item from the watchlist.
   *
   * @param {React.MouseEvent<HTMLElement>} e - the mouse event triggering the function
   * @return {void}
   */

  const handleRemoveFromWatchlist = (e: React.MouseEvent<HTMLElement>) => {
    e.stopPropagation(); // Prevent navigation when clicking the icon
    if (hoveredItem) {
      const updatedWatchlist = watchlist.filter((item) => item !== hoveredItem);
      setWatchlist(updatedWatchlist);
      onRemoveFromWatchlist(hoveredItem);
    }
  };

  return (
    <S.StockListContainer>
      <S.Table>
        <thead>
          <tr>
            <th>Company</th>
            <th>Base Price</th>
            <th>Watchlist</th>
          </tr>
        </thead>
        <tbody>
          {currentItems.map((stock) => (
            <tr
              key={stock.stock_symbol}
              onMouseEnter={() => handleHoverItem(stock.stock_symbol)}
              onMouseLeave={() => setHoveredItem(null)}
              onClick={() => handleNavigateToStockDetail(stock.stock_symbol)} // Navigate on row click
              style={{ cursor: "pointer" }}
            >
              <td>{stock.stock_name}</td>
              <td>₹{stock.base_price}</td>
              <td>
                <S.AddToWatchlistButton
                  onClick={(e) => handleToggleWatchlist(e, stock)}
                  isAdded={watchlist.includes(stock.stock_symbol)}
                  isHovered={hoveredItem === stock.stock_symbol}
                >
                  {watchlist.includes(stock.stock_symbol) ? (
                    <i
                      className={`pi ${
                        hoveredItem === stock.stock_symbol
                          ? "pi-times"
                          : "pi-check-circle"
                      }`}
                      style={{
                        fontSize: "1rem",
                        background: "#1871c1",
                        color: "white",
                        fontWeight: 800,
                      }}
                      onClick={handleRemoveFromWatchlist}
                    ></i>
                  ) : (
                    <i
                      className="pi pi-plus-circle"
                      style={{
                        fontSize: "1rem",
                        color: "#1871c1",
                        fontWeight: 800,
                      }}
                    ></i>
                  )}
                </S.AddToWatchlistButton>
              </td>
            </tr>
          ))}
        </tbody>
        <S.Pagination>
          <S.Arrow
            onClick={() => handleClickPage(currentPage - 1)}
            disabled={currentPage === 1}
          >
            {"<"}
          </S.Arrow>
          <ul>
            {[...Array(totalPages)].map((_, index) => (
              <li
                key={index + 1}
                onClick={() => handleClickPage(index + 1)}
                className={currentPage === index + 1 ? "active" : ""}
              >
                {index + 1}
              </li>
            ))}
          </ul>
          <S.Arrow
            onClick={() => handleClickPage(currentPage + 1)}
            disabled={currentPage === totalPages}
          >
            {">"}
          </S.Arrow>
        </S.Pagination>
      </S.Table>
    </S.StockListContainer>
  );
};

export default StockList;
