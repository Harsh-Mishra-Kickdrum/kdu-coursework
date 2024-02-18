/**
 * Utility for paginating data.
 * @module utils/pagination
 */

const limit = 5; // Number of items per page

/**
 * Paginates the given data based on the page number.
 *
 * @param {number} pageNumber - The current page number.
 * @param {Array} data - The array of data items to be paginated.
 * @returns {Array} - The subset of data items for the current page.
 */
const paginate = (pageNumber, data) => {
  const startIndex = (pageNumber - 1) * limit;
  const endIndex = pageNumber * limit;
  return data.slice(startIndex, endIndex);
};

module.exports = paginate;
