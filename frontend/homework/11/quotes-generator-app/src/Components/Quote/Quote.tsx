// src/components/Quote/Quote.tsx
import React from "react";
import { APIResponse } from "../../api/quotes.type";
import "./Quote.scss";

interface QuoteProps {
  quote: APIResponse;
  handleTagClick: (tag: string) => void;
}

/**
 * Renders a quote with content, author, date, and tags.
 *
 * @param {QuoteProps} quote - The quote object containing content, author, date, and tags
 * @param {Function} handleTagClick - The function to handle tag click events
 * @return {JSX.Element} The rendered quote component
 */

const Quote: React.FC<QuoteProps> = ({ quote, handleTagClick }) => {
  return (
    <div className="quoteContainer">
      <p className="quote">{quote.content}</p>
      <div>
        <span className="author">~{quote.author}</span>

      
        {
          <span className="date">
            {quote.dateAdded
              ? new Date(quote.dateAdded)
                  .toLocaleDateString("en-US", {
                    year: "numeric",
                    month: "2-digit",
                    day: "2-digit",
                  })
                  .replace(/\//g, "-")
                  .split("-")
                  .reverse()
                  .join("-")
              : null}
          </span>
        }
      </div>
      <div className="tags">
        {Array.isArray(quote.tags) &&
          quote.tags.map((tag, index) => (
            <button
              key={index}
              onClick={() => handleTagClick(tag)}
              className="tagButton"
            >
              {tag}
            </button>
          ))}
      </div>
    </div>
  );
};

export default Quote;


