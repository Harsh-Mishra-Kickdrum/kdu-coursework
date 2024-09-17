import React, { useContext } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
// Importing AppContext and its provider to manage global state across the app
import AppContext, { AppContextProvider } from "./context/AppContext";
// Importing page components
import HomePage from "./pages/HomePage/HomePage";
import ProductPage from "./pages/ProductPage/ProductPage";
import ErrorPage from "./pages/ErrorPage";
// Importing the Navbar component
import Navbar from "./components/Navbar/Navbar";

/**
 * The App component serves as the root component of the application.
 * It sets up the routing logic and provides global context to the components within it.
 *
 * @returns {JSX.Element} The App component wrapped in a Router and AppContextProvider.
 */
const App = () => {
  // Using useContext hook to access global state values
  const { categories } = useContext(AppContext);

  return (
    // Router component to handle client-side routing
    <Router>
      {/* AppContextProvider wraps the components that need access to the global state */}
      <AppContextProvider>
        {/* Navbar component is rendered with categories passed as props */}
        <Navbar categories={categories} />
        {/* Routes setup to render different pages based on the URL */}
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/products/:productId" element={<ProductPage />} />
          <Route path="*" element={<ErrorPage />} />
        </Routes>
      </AppContextProvider>
    </Router>
  );
};

export default App;
