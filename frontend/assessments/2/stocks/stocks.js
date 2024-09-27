const casual = require("casual");

//creates the stock object which sends zomato as stock name and random prices for each stock
module.exports = () => {
  casual.define("stock", function () {
    return {
      stockName: casual.name || "ZOMATO",
      stockRandomCurrentPrice : casual.integer(0, 500),
      stockRandomBasePrice : casual.integer(0, 500),
      //for showing change in stock price
      stockChange : casual.integer(-100, 100),
      stockChangePercentage : casual.integer(0, 100),
      stockVolume : casual.integer(1000, 200000),
      stockDate : new Date(),
      id: casual.uuid,
    };
  });
  const data = {
    stocks: [],
  };
  // Create 100 stocks
  for (let i = 0; i < 100; i++) {
    data.stocks.push(casual.stocks);
  }
  return data;
};