// src/components/QuoteList/QuoteList.tsx
import React from "react";
import Quote from "../Quote/Quote";
import { APIResponse } from "../../api/quotes.type";
import "./QuoteList.scss";
import Spinner from "../Spinner/Spinner";

interface QuoteListProps {
  quotes: APIResponse[];
  handleTagClick: (tag: string) => void;
}


/**
 * Renders a list of quotes.
 *
 * @param {Quote[]} quotes - The list of quotes to render
 * @param {Function} handleTagClick - The function to handle tag click
 * @return {ReactNode} The rendered list of quotes
 */
const QuoteList: React.FC<QuoteListProps> = ({ quotes, handleTagClick }) => {
  return (
    <div className="quoteList">
      {quotes.length === 0 && (
        <div className="unknownDateContainer">
          <p className="starting-message">
            Loading Wait .........
          </p>
        </div>
      )}
      {quotes.map((quote, index) => (
        <Quote key={index} quote={quote} handleTagClick={handleTagClick} />
      ))}
    </div>
  );
};

export default QuoteList;
