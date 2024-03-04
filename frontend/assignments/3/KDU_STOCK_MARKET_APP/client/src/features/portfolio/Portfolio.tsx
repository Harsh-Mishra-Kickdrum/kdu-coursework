/* eslint-disable @typescript-eslint/no-explicit-any */
// Portfolio.tsx
import React, { useState, useEffect } from "react";
import * as S from "./Portfolio.styles";

import "primeicons/primeicons.css";
import {
  TextField,
  Checkbox,
  FormControlLabel,
  CircularProgress,
  Table,
  TableContainer,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  Typography,
} from "@mui/material";
import { Loader } from "../../components/Loader/Loader.styles";

interface Transaction {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: string;
}

type FilterType = "failed" | "passed" | "stockName" | "date";
type DateFilterType = "startDate" | "endDate";

interface PortfolioProps {
  transactionsData?: Transaction[]; // Make transactionsData optional
}

/**
 * A React functional component for displaying a portfolio with filter options.
 *
 * @param {PortfolioProps} transactionsData - an array of transactions data
 * @return {JSX.Element} the portfolio component
 */

const Portfolio: React.FC<PortfolioProps> = ({ transactionsData = [] }) => {
  const [filteredTransactions, setFilteredTransactions] =
    useState<Transaction[]>(transactionsData);
  const [loading, setLoading] = useState(false);
  const [filters, setFilters] = useState<{ [key in FilterType]?: any }>({});
  const [dateFilters, setDateFilters] = useState<{
    [key in DateFilterType]?: string;
  }>({});
  const [stockNames, setStockNames] = useState<string[]>([]);

  useEffect(() => {
    applyFilters();
    // Extract unique stock names for stock filter checkboxes
    const uniqueStockNames = Array.from(
      new Set(transactionsData.map((transaction) => transaction.stock_name))
    );
    setStockNames(uniqueStockNames);
  }, [filters, dateFilters, transactionsData]); // Include transactionsData in the dependency array

  const applyFilters = () => {
    setLoading(true);

    let filteredData = [...transactionsData];

    // Apply checkbox filters
    if (filters.failed || filters.passed) {
      filteredData = filteredData.filter((transaction) =>
        filters.failed && filters.passed
          ? true
          : filters.failed
          ? transaction.status === "Failed"
          : transaction.status === "Passed"
      );
    }

    // Apply stock name filter
    if (filters.stockName) {
      filteredData = filteredData.filter(
        (transaction) =>
          transaction.stock_name
            .toLowerCase()
            .includes(filters.stockName.toLowerCase()) ||
          transaction.stock_symbol
            .toLowerCase()
            .includes(filters.stockName.toLowerCase())
      );
    }

    // Apply date filter
    if (dateFilters.startDate || dateFilters.endDate) {
      filteredData = filteredData.filter((transaction) => {
        const timestamp = new Date(transaction.timestamp).getTime();

        if (dateFilters.startDate && dateFilters.endDate) {
          const startDate = new Date(dateFilters.startDate).getTime();
          const endDate = new Date(dateFilters.endDate).getTime();

          return timestamp >= startDate && timestamp <= endDate;
        } else if (dateFilters.startDate) {
          const startDate = new Date(dateFilters.startDate).getTime();
          return timestamp >= startDate;
        } else if (dateFilters.endDate) {
          const endDate = new Date(dateFilters.endDate).getTime();
          return timestamp <= endDate;
        }

        return true;
      });
    }

    setFilteredTransactions(filteredData);
    setLoading(false);
  };


    /**
   * A function to handle filter change.
   *
   * @param {FilterType} type - the type of filter
   * @param {any} value - the new value for the filter
   * @return {void} 
   */

  const handleFilterChange = (type: FilterType, value: any) => {
    setFilters((prevFilters) => ({ ...prevFilters, [type]: value }));
  };


    /**
   * A function that handles the change of date filter.
   *
   * @param {DateFilterType} type - the type of date filter
   * @param {string} value - the new value for the date filter
   * @return {void} 
   */

  const handleDateFilterChange = (type: DateFilterType, value: string) => {
    setDateFilters((prevDateFilters) => ({
      ...prevDateFilters,
      [type]: value,
    }));
  };


    /**
   * A function that handles the change of stock name.
   *
   * @param {string} stockName - the new stock name
   * @param {boolean} checked - indicates if the stock name is checked
   * @return {void} 
   */

  const handleStockNameChange = (stockName: string, checked: boolean) => {
    setFilters((prevFilters) => ({
      ...prevFilters,
      stockName: checked ? stockName : "",
    }));
  };

  /**
   * Clears all the filters and date filters.
   */

  const clearAllFilters = () => {
    setFilters({});
    setDateFilters({});
  };

  return (
    <div style={{ display: "flex" }}>
      {/* Left part - Filter Section */}
      <div
        style={{
          flex: 1,
          padding: "16px",
          borderRight: "1px solid #ccc",
          background: "#e9ecef",
          margin: "20px",
          border: "3px solid grey",
          borderRadius: "3em",
          height: "85em",
        }}
      >
        <div style={{ display: "flex", justifyContent: "space-around" }}>
          <div>Filters</div>
          <div>
            {/* Clear All Filters Button */}
            <S.btn onClick={clearAllFilters}>Clear All</S.btn>
          </div>
        </div>
        <hr />
        {/* Search by Stock Name/Symbol */}
        <TextField
          label="Search by Stock Name/Symbol"
          fullWidth
          onChange={(e) => handleFilterChange("stockName", e.target.value)}
        />
        <hr />
        {/* Start Date and End Date Side by Side */}
        <div style={{ display: "flex", marginTop: "16px" }}>
          {/* Start Date */}
          <TextField
            label="Start Date"
            type="date"
            fullWidth
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(e) =>
              handleDateFilterChange("startDate", e.target.value)
            }
            style={{ marginRight: "8px" }}
          />

          {/* End Date */}
          <TextField
            label="End Date"
            type="date"
            fullWidth
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(e) => handleDateFilterChange("endDate", e.target.value)}
          />
        </div>
        <hr />
        {/* Passed Checkbox */}
        <div style={{ marginTop: "16px" }}>
          <FormControlLabel
            control={
              <Checkbox
                checked={filters.passed}
                onChange={(e) => handleFilterChange("passed", e.target.checked)}
                color="primary"
              />
            }
            label="Passed"
          />
        </div>

        {/* Failed Checkbox */}
        <div style={{ marginTop: "8px" }}>
          <FormControlLabel
            control={
              <Checkbox
                checked={filters.failed}
                onChange={(e) => handleFilterChange("failed", e.target.checked)}
                color="primary"
              />
            }
            label="Failed"
          />
        </div>
        <hr />
        {/* Stock Name Checkboxes */}
        <div style={{ marginTop: "16px" }}>
          {stockNames.map((stockName) => (
            <div key={stockName}>
              <FormControlLabel
                control={
                  <Checkbox
                    checked={filters.stockName === stockName}
                    onChange={(e) =>
                      handleStockNameChange(stockName, e.target.checked)
                    }
                    color="primary"
                  />
                }
                label={stockName}
                style={{ marginBottom: "8px" }}
              />
            </div>
          ))}
        </div>
      </div>

      {/* Right part - Transaction Data */}
      <div style={{ flex: 3, padding: "16px" }}>
        {loading && <CircularProgress style={{ marginBottom: "16px" }} />}
        {filteredTransactions.length === 0 ? (
          <Typography>
            <Loader />
          </Typography>
        ) : (
          <TableContainer component={Paper}>
            <Table>
              <TableHead></TableHead>
              <TableBody>
                {filteredTransactions.map((transaction, index) => (
                  <TableRow key={index}>
                    <TableCell>{transaction.stock_name}</TableCell>
                    <TableCell>{transaction.stock_symbol}</TableCell>
                    <TableCell>₹{transaction.transaction_price}</TableCell>
                    <TableCell>
                      {new Date(transaction.timestamp).toLocaleTimeString(
                        "en-US",
                        { hour: "numeric", minute: "numeric", hour12: true }
                      )}
                    </TableCell>
                    <TableCell>
                      {/* Display icon based on status */}
                      {transaction.status === "Failed" ? (
                        <i
                          className="pi pi-circle-fill"
                          style={{ color: "#e76c6d" }}
                        ></i>
                      ) : (
                        <i
                          className="pi pi-circle-fill"
                          style={{ color: "#6bb97b" }}
                        ></i>
                      )}
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </div>
    </div>
  );
};

export default Portfolio;
