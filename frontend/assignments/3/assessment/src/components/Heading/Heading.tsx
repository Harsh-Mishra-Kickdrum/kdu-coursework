// Navbar.tsx
import React from "react";
import * as S from "./Heading.styles";

/**
 * Render the header component with a title and search input.
 *
 * @param {string} title - The title for the header
 * @return {ReactNode} The rendered header component
 */
const Header: React.FC<{ title: string }> = ({ title }) => {
  return (
    <>
      <S.HeaderContainer>
        <header className="HeaderContainer">
          <h1>{title}</h1>
        </header>
      </S.HeaderContainer>
    </>
  );
};

export default Header;
