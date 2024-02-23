// src/components/Header/Header.tsx
import React, { useState } from "react";
import { fetchRandomQuote } from "../../api/quotesApi"; // Adjust the path as necessary
import "./Header.scss";
import { APIResponse } from "../../api/quotes.type";
import Spinner from "../Spinner/Spinner";

interface HeaderProps {
  onNewQuote: (newQuote: APIResponse) => void;
}

export const Header: React.FC<HeaderProps> = ({ onNewQuote }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleNewQuoteClick = async () => {
    setIsLoading(true);
    setError(null);
    try {
      const newQuote = await fetchRandomQuote();
      onNewQuote(newQuote); 
    } catch (error) {
      setError("Failed to fetch a new quote");
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="headerContainer">
      <button className="header-addQuote-btn" onClick={handleNewQuoteClick} disabled={isLoading}>
        {isLoading ? <Spinner /> : "New Quote"}
      </button>
      {error && <p className="error">{error}</p>}
    </div>
  );
};

export default Header;
