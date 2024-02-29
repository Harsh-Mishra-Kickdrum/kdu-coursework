// Importing necessary modules and components from React, React Router, React Icons, and styled-components
import React, { useContext, useState } from "react";
import AppContext from "../../context/AppContext";
import { useNavigate, useLocation } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import * as S from "./Navbar.styles";

// TypeScript interface to type-check the props received by the Navbar component
interface NavbarProps {
  categories: string[];
}

/**
 * The Navbar component provides a navigation bar with search, filter, and sort functionalities.
 * It uses context to manage global state and react-router for navigation.
 *
 * @param {NavbarProps} props - Component props containing categories for filtering.
 * @returns {JSX.Element} The Navbar component.
 */
const Navbar: React.FC<NavbarProps> = ({ categories }) => {
  // Destructuring necessary states and functions from the AppContext
  const { filter, setFilter, sort, setSort, setSearchQuery } =
    useContext(AppContext);

  // State to manage the search input value
  const [searchInput, setSearchInput] = useState("");

  // Hooks from react-router-dom to programmatically navigate and access the current location
  const navigate = useNavigate();
  const location = useLocation();

  // Boolean to check if the current page is a product page based on the URL
  const isProductPage = location.pathname.includes("/products/");

  /**
   * Handles changes to the filter dropdown.
   * @param {React.ChangeEvent<HTMLSelectElement>} event - The change event on the select element.
   */
  const handleFilterChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setFilter(event.target.value);
  };

  /**
   * Handles changes to the sort dropdown.
   * @param {React.ChangeEvent<HTMLSelectElement>} event - The change event on the select element.
   */
  const handleSortChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSort(event.target.value);
  };

  /**
   * Handles changes to the search input field.
   * @param {React.ChangeEvent<HTMLInputElement>} event - The change event on the input element.
   */
  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchInput(event.target.value);
  };

  /**
   * Handles the search button click event.
   * It sets the search query in the global state and navigates the user to the home page.
   */
  const handleSearchButtonClick = () => {
    setSearchQuery(searchInput);
    navigate("/");
  };

  return (
    <S.NavbarContainer>
      {/* Container for the search input and button */}
      <S.LeftContainer>
        <S.ActionItems>
          <S.SearchInput
            type="text"
            placeholder="Search"
            value={searchInput}
            onChange={handleSearchInputChange}
          />
          <S.SearchButtonContainer>
            <S.SearchButton onClick={handleSearchButtonClick}>
              <FaSearch size={15} />
            </S.SearchButton>
          </S.SearchButtonContainer>
        </S.ActionItems>
      </S.LeftContainer>

      {/* Container for the filter and sort options, not shown on product pages */}
      <S.RightContainer>
        {!isProductPage && (
          <>
            <S.WhiteText>Filter:</S.WhiteText>
            <S.ActionItems>
              <select value={filter} onChange={handleFilterChange}>
                <option value="all">All</option>
                {categories.map((category) => (
                  <option key={category} value={category}>
                    {category}
                  </option>
                ))}
              </select>
            </S.ActionItems>

            <S.WhiteText>Sort:</S.WhiteText>
            <S.ActionItems>
              <select value={sort} onChange={handleSortChange}>
                <option value="none">None</option>
                <option value="asc">Price (ASC)</option>
                <option value="desc">Price (DESC)</option>
              </select>
            </S.ActionItems>
          </>
        )}
      </S.RightContainer>
    </S.NavbarContainer>
  );
};

// Exporting the Navbar component to be used in other parts of the application
export default Navbar;
