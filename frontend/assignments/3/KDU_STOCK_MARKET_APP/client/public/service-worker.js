// public/service-worker.js
self.addEventListener('message', async (event) => {
  console.log('Service Worker received calculateMaxProfit message');
  const { type, payload } = event.data;

  if (type === 'calculateMaxProfit') {
    const { prices } = payload;
    const maxProfitInfo = calculateMaxProfit(prices);

    // Send the result back to the main thread
    self.postMessage({ type: 'maxProfitCalculated', payload: maxProfitInfo });
  }
});

/**
 * Calculate the maximum profit that can be obtained from a given array of stock prices.
 *
 * @param {Array<number>} prices - An array of stock prices
 * @return {Object} An object containing buyIndex, sellIndex, buyPrice, sellPrice, and maxProfit
 */

const calculateMaxProfit = (prices) => {
  let buyIndex = 0;
  let sellIndex = 0;
  let buyPrice = 0;
  let sellPrice = 0;
  let maxProfit = 0;

  let minBuyPrice = prices[0]; // Initialize with the first price
  let maxSellPrice = prices[0];

  for (let i = 1; i < prices.length; i++) {
    if (prices[i] < minBuyPrice) {
      // Update the buy price
      minBuyPrice = prices[i];
      buyIndex = i;
      buyPrice = minBuyPrice;
    } else if (prices[i] > maxSellPrice) {
      // Update the sell price
      maxSellPrice = prices[i];
      sellIndex = i;
      sellPrice = maxSellPrice;
    }
  }

  maxProfit = maxSellPrice - minBuyPrice;

  return { buyIndex, sellIndex, buyPrice, sellPrice, maxProfit };
};
