// Navbar.tsx
import React from "react";
import * as S from "./Navbar.styles";

/**
 * Render the header component with a title and search input.
 *
 * @param {string} title - The title for the header
 * @return {ReactNode} The rendered header component
 */
const Header: React.FC<{ title: string }> = ({ title }) => {


  return (
    <>
      <S.NavbarContainer>
      <header className="NavbarContainer">
        <h1>{title}</h1>
      </header>
    </S.NavbarContainer>
    </>
  );
};

export default Header;
