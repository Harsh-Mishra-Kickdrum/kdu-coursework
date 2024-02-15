"use strict";
//path of this file - > " /src/app.ts "
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const axios_1 = __importDefault(require("axios"));
const BASE_URL = "https://dummyjson.com/recipes";
// Function to fetch recipes from the API
async function fetchRecipesFromAPI() {
    const response = await axios_1.default.get(BASE_URL);
    const data = response.data;
    return data.recipes;
}
// Function to search recipes by keyword in the API
/* sync function searchRecipes(query: string): Promise<Recipe[]> {
  const response = await axios.get(`${BASE_URL}/search?q=${query}`);
  const data: RecipesApiResponse = await response.json() as RecipesApiResponse;
  console.log(data.recipes);
  return data.recipes;
} */
// Function to print single recipe details
function printRecipe(recipe) {
    console.log(`Name: ${recipe.name}, Cuisine: ${recipe.cuisine}, Rating: ${recipe.rating}, Calories: ${recipe.caloriesPerServing}`);
}
// Function to print all recipes
async function printAllRecipes() {
    const recipes = await fetchRecipesFromAPI();
    recipes.forEach(printRecipe);
}
printAllRecipes();
