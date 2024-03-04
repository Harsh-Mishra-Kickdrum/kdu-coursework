
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { StockProvider } from "./Context/StockContext"; 
import Navbar from "./components/Navbar/Navbar";
import Dashboard from "./features/dashboard/Dashboard";
import StockDetail from "./features/stock/StockDetail"; 
import Portfolio from "./features/portfolio/Portfolio";
import { useEffect, useState } from "react";
import Summarizer from "./features/Summarizer/Summarizer";


/**
 * Render the main application component.
 *
 * @return {JSX.Element} The JSX element representing the main application component.
 */

function App() {
  // State to store the fetched transactions data
  const [transactionsData, setTransactionsData] = useState([]);

  useEffect(() => {
    // Fetch data from the API
    const fetchData = async () => {
      try {
        const response = await fetch(
          "https://dev-9x9ul9opv3e623n.api.raw-labs.com/mock/3"
        );
        const data = await response.json();
        setTransactionsData(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData(); // Call the fetchData function
  }, []);
  return (
    <StockProvider>
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/summarizer" element={<Summarizer />} />

          <Route path="/stock/:stockSymbol" element={<StockDetail />} />
          <Route
            path="/portfolio"
            element={<Portfolio transactionsData={transactionsData} />}
          />
        </Routes>
      </Router>
    </StockProvider>
  );
}

export default App;
