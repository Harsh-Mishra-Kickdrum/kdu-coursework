// Define shoe objects array
const shoes = [
  { type: "sneakers", color: "white", size: 9, price: 100 },
  { type: "running shoes", color: "blue", size: 10, price: 120 },
];

// Define shirt objects array
const shirts = [
  { type: "polo", color: "red", size: "M", price: 50 },
  { type: "t-shirt", color: "green", size: "L", price: 25 },
  { type: "dress shirt", color: "blue", size: "M", price: 75 },
];

// Combine shoes and shirts into a single warehouse array
const warehouse = [...shoes, ...shirts];

// Calculate the total worth of products in the warehouse
const totalWorth = warehouse.reduce((acc, item) => acc + item.price, 0);
console.log(`Total worth of products: $${totalWorth}`);

// Sort the warehouse items in descending order by price
warehouse.sort((a, b) => b.price - a.price);

// Filter and display blue products
const blueProducts = warehouse.filter((item) => item.color === "blue");
console.log("Blue products in the warehouse:", blueProducts);
