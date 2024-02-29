import React, { createContext, useState } from "react";
import { Product } from "../types/apptypes"; // Importing the Product type


// Defining the shape of the context's value with TypeScript interface
interface AppContextProps {
  items: Product[];
  setItems: (items: Product[]) => void;
  filter: string;
  setFilter: (filter: string) => void;
  sort: string;
  setSort: (sort: string) => void;
  searchQuery: string;
  setSearchQuery: (searchQuery: string) => void;
  categories: string[];
}

// Creating the context with default values
const AppContext = createContext<AppContextProps>({
  items: [],
  setItems: () => {}, // Placeholder function
  filter: "all", // Default filter value
  setFilter: () => {}, // Placeholder function
  sort: "none", // Default sort value
  setSort: () => {}, // Placeholder function
  searchQuery: "", // Default search query value
  setSearchQuery: () => {}, // Placeholder function
  categories: ["Mens clothing", "Womens clothing", "Jewellery", "Electronics"], // Default categories
});

export default AppContext;



/**
 * AppContextProvider component wraps its children with the AppContext.Provider
 * allowing them to access the context values.
 *
 * @param {object} props - Props object
 * @param {React.ReactNode} props.children - Child nodes to have access to the context
 * @returns {JSX.Element} The Provider component with value set to the current state and setters.
 */
export const AppContextProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  // State for items with setter
  const [items, setItems] = useState<Product[]>([]);
  // State for filter with setter
  const [filter, setFilter] = useState<string>("all");
  // State for sort with setter
  const [sort, setSort] = useState<string>("none");
  // State for search query with setter
  const [searchQuery, setSearchQuery] = useState<string>("");

  // Providing context values to children components
  return (
    <AppContext.Provider
      value={{
        items,
        setItems,
        filter,
        setFilter,
        sort,
        setSort,
        searchQuery,
        setSearchQuery,
        // Static list of categories
        categories: [
          "Mens clothing",
          "Womens clothing",
          "Jewellery",
          "Electronics",
        ],
      }}
    >
      {children}
    </AppContext.Provider>
  );
};
