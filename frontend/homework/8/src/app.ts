import axios from "axios";
import * as readline from 'readline';
import { Recipe, RecipesApiResponse } from "./models/Recipe";

const BASE_URL = "https://dummyjson.com/recipes";

// Create readline interface
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Function to fetch recipes from the API
async function fetchRecipesFromAPI(): Promise<Recipe[]> {
  const response = await axios.get(BASE_URL);
  const data = response.data;
  return data.recipes;
}

// Function to search recipes by keyword in the API
async function searchRecipes(query: string): Promise<Recipe[]> {
  const response = await axios.get(`${BASE_URL}/search?q=${query}`);
  const data = response.data;
  return data.recipes;
}

// Function to print single recipe details
function printRecipe(recipe: Recipe): void {
  console.log(
    `Name: ${recipe.name}, Cuisine: ${recipe.cuisine}, Rating: ${recipe.rating}, Calories: ${recipe.caloriesPerServing}`
  );
}

// Function to print all recipes
async function printAllRecipes(): Promise<void> {
  const recipes = await fetchRecipesFromAPI();
  recipes.forEach(printRecipe);
}

// // Function to handle user input and execute the chosen action
// async function handleUserInput(choice: string): Promise<void> {
//   switch (choice) {
//     case "1":
//       await printAllRecipes();
//       break;
//     case "2":
//       rl.question("Enter a keyword to search for recipes: ", async (query) => {
//         const recipes = await searchRecipes(query);
//         recipes.forEach(printRecipe);
//         rl.close();
//       });
//       break;
//     default:
//       console.log("Invalid choice, please enter 1 or 2.");
//       rl.close();
//       break;
//   }
// }

// Main function to run the application
async function main() {
  console.log("Recipe Application");
  console.log("1. Fetch and print all recipes");
  console.log("2. Search for recipes by keyword");

  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  // Wrap readline.question in a promise to use with async/await
  const question = (query: string): Promise<string> => {
    return new Promise((resolve) => {
      rl.question(query, (answer) => {
        resolve(answer);
      });
    });
  };

  const choice = await question("Enter your choice (1 or 2): ");

  if (choice === '1') {
    await printAllRecipes();
  } else if (choice === '2') {
    const keyword = await question("Enter a keyword to search for recipes: ");
    await searchAndPrintRecipes(keyword);
  }

  rl.close();
}

// Function to search recipes and print results
async function searchAndPrintRecipes(query: string): Promise<void> {
  const recipes = await searchRecipes(query);
  if (recipes.length > 0) {
    recipes.forEach(printRecipe);
  } else {
    console.log("No recipes found for the keyword:", query);
  }
}

main()
  .then(() => console.log("Application finished."))
  .catch((error) => console.error(error));
