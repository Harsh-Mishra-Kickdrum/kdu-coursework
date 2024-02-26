// Header.tsx
import React from "react";
import { useDispatch } from "react-redux";
import { setSearchTerm } from "../../redux/reducers/searchSlice"; 
import "./Header.scss";

/**
 * Render the header component with a title and search input.
 *
 * @param {string} title - The title for the header
 * @return {ReactNode} The rendered header component
 */
const Header: React.FC<{ title: string }> = ({ title }) => {
  const dispatch = useDispatch();

  /**
   * Handles the change event for the search input, dispatching the setSearchTerm action.
   *
   * @param {React.ChangeEvent<HTMLInputElement>} event - the change event object
   * @return {void}
   */
  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setSearchTerm(event.target.value));
  };

  return (
    <header className="header">
      <h1>{title}</h1>
      <input
        type="text"
        placeholder="Search Items ...."
        onChange={handleSearchChange}
      />
    </header>
  );
};

export default Header;
