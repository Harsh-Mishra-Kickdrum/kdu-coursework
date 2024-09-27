import React, { useEffect, useState } from "react";
import "./App.css";
import Filter from "./Components/Filter/Filter";
import Header from "./Components/Header/Header";
import QuoteList from "./Components/QuoteList/QuoteList";
import { fetchQuotes, fetchRandomQuote } from "./api/quotesApi";
import { APIResponse } from "./api/quotes.type";


/**
 * The App component renders the main application interface, including the header, filter, and quote list.
 *
 * @return {JSX.Element} The main application interface
 */

const App = () => {
  const [quotes, setQuotes] = useState<APIResponse[]>([]);
  const [filters, setFilters] = useState<string[]>([]);
  const [newQuote, setNewQuote] = useState<APIResponse | null>(null);

  useEffect(() => {
    /**
     * Asynchronously fetches data and sets quotes, or logs an error if there is one.
     *
     * @param {} -
     * @return {}
     */
    const fetchData = async () => {
      try {
        const data = await fetchQuotes();
        setQuotes(data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

  /**
   * Handles the click event for a tag.
   *
   * @param {string} tag - the tag that was clicked
   * @return {void}
   */
  const handleTagClick = (tag: string) => {
    if (filters.includes(tag)) {
      setFilters(filters.filter((filter) => filter !== tag));
    } else {
      setFilters([...filters, tag]);
    }
  };

  /**
   * Removes the specified filter from the filters list.
   *
   * @param {string} filter - the filter to be removed
   * @return {void}
   */
  const removeFilter = (filter: string) => {
    setFilters(filters.filter((item) => item !== filter));
  };

  /**
   * Handle fetching a new quote and updating the state accordingly.
   *
   * @param {APIResponse} newQuote - The new quote to be fetched
   * @return {void}
   */
  const handleFetchNewQuote = async (newQuote: APIResponse) => {
    try {
      const newQuote = await fetchRandomQuote();
      setQuotes( [newQuote, ...quotes]);
      console.log(quotes);

      setNewQuote(newQuote); 
    } catch (error) {
      console.error(error);
    }
  };

  const filteredQuotes = quotes
    .filter((quote) =>
      filters.length > 0
        ? quote.tags.some((tag) => filters.includes(tag))
        : true
    )
    .concat(newQuote || []);

  return (
    <div className="App">
      <Header onNewQuote={handleFetchNewQuote} />
      <div className="filter-text">Filters</div>
      <Filter filters={filters} removeFilter={removeFilter} />
      <hr />
      <QuoteList quotes={filteredQuotes} handleTagClick={handleTagClick} />
    </div>
  );
};

export default App;