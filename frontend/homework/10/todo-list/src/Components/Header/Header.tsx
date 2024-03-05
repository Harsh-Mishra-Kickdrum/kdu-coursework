import React, { useState } from "react";
import "./Header.scss";

interface HeaderProps {
  title: string;
  onSearch?: (searchTerm: string) => void;
}


/**
 * React functional component for rendering a header with a search input.
 *
 * @param {HeaderProps} title - The title of the header
 * @param {HeaderProps} onSearch - The callback function for handling search
 * @return {JSX.Element} The header JSX element
 */

const Header: React.FC<HeaderProps> = ({ title, onSearch }) => {
  const [searchTerm, setSearchTerm] = useState("");

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
    onSearch?.(event.target.value); // Call provided onSearch callback if available
  };

  return (
    <header className="header">
      <h1>{"Item Lister"}</h1>
      <input
        type="text"
        placeholder="Search Items ...."
        value={searchTerm}
        onChange={handleSearchChange}
      />
    </header>
  );
};

export default Header;
