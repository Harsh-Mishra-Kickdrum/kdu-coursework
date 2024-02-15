"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
Object.defineProperty(exports, "__esModule", { value: true });
var axios_1 = require("axios");
var readline = require("readline");
var BASE_URL = "https://dummyjson.com/recipes";
// Create readline interface
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});
// Function to fetch recipes from the API
function fetchRecipesFromAPI() {
    return __awaiter(this, void 0, void 0, function () {
        var response, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, axios_1.default.get(BASE_URL)];
                case 1:
                    response = _a.sent();
                    data = response.data;
                    return [2 /*return*/, data.recipes];
            }
        });
    });
}
// Function to search recipes by keyword in the API
function searchRecipes(query) {
    return __awaiter(this, void 0, void 0, function () {
        var response, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, axios_1.default.get("".concat(BASE_URL, "/search?q=").concat(query))];
                case 1:
                    response = _a.sent();
                    data = response.data;
                    return [2 /*return*/, data.recipes];
            }
        });
    });
}
// Function to print single recipe details
function printRecipe(recipe) {
    console.log("Name: ".concat(recipe.name, ", Cuisine: ").concat(recipe.cuisine, ", Rating: ").concat(recipe.rating, ", Calories: ").concat(recipe.caloriesPerServing));
}
// Function to print all recipes
function printAllRecipes() {
    return __awaiter(this, void 0, void 0, function () {
        var recipes;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, fetchRecipesFromAPI()];
                case 1:
                    recipes = _a.sent();
                    recipes.forEach(printRecipe);
                    return [2 /*return*/];
            }
        });
    });
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
function main() {
    return __awaiter(this, void 0, void 0, function () {
        var rl, question, choice, keyword;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    console.log("Recipe Application");
                    console.log("1. Fetch and print all recipes");
                    console.log("2. Search for recipes by keyword");
                    rl = readline.createInterface({
                        input: process.stdin,
                        output: process.stdout,
                    });
                    question = function (query) {
                        return new Promise(function (resolve) {
                            rl.question(query, function (answer) {
                                resolve(answer);
                            });
                        });
                    };
                    return [4 /*yield*/, question("Enter your choice (1 or 2): ")];
                case 1:
                    choice = _a.sent();
                    if (!(choice === '1')) return [3 /*break*/, 3];
                    return [4 /*yield*/, printAllRecipes()];
                case 2:
                    _a.sent();
                    return [3 /*break*/, 6];
                case 3:
                    if (!(choice === '2')) return [3 /*break*/, 6];
                    return [4 /*yield*/, question("Enter a keyword to search for recipes: ")];
                case 4:
                    keyword = _a.sent();
                    return [4 /*yield*/, searchAndPrintRecipes(keyword)];
                case 5:
                    _a.sent();
                    _a.label = 6;
                case 6:
                    rl.close();
                    return [2 /*return*/];
            }
        });
    });
}
// Function to search recipes and print results
function searchAndPrintRecipes(query) {
    return __awaiter(this, void 0, void 0, function () {
        var recipes;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, searchRecipes(query)];
                case 1:
                    recipes = _a.sent();
                    if (recipes.length > 0) {
                        recipes.forEach(printRecipe);
                    }
                    else {
                        console.log("No recipes found for the keyword:", query);
                    }
                    return [2 /*return*/];
            }
        });
    });
}
main()
    .then(function () { return console.log("Application finished."); })
    .catch(function (error) { return console.error(error); });
