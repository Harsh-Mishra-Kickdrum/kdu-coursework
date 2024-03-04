import { Stock } from "../type/stocks";

// client/src/utils/LocalStoragePersistenceFunction.ts
export const getWatchlistFromStorage = (): Array<Stock> => {
    const storedWatchlist = localStorage.getItem('watchlist');
    return storedWatchlist ? JSON.parse(storedWatchlist) : [];
  };
  

    /**
   * Updates the watchlist in the storage.
   *
   * @param {Array<Stock>} watchlist - the array of stocks to be updated in the storage
   * @return {void} 
   */
  
  export const updateWatchlistInStorage = (watchlist: Array<Stock>): void => {
    localStorage.setItem('watchlist', JSON.stringify(watchlist));
  };
  