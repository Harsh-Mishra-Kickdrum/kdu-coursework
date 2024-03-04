// client/src/components/Navbar/Navbar.tsx
import React from 'react';
import { Link } from 'react-router-dom';
import * as S from './Navbar.styles';
import logo from '../../assets/logo.jpg';


/**
 * Renders the Navbar component.
 *
 * @return {JSX.Element} The rendered Navbar component
 */

const Navbar: React.FC = () => {
  return (
    <S.NavbarContainer>
      <S.LogoLink>

        <Link to="/">
          <S.Logo src={logo} alt="logo" />
        </Link>
      </S.LogoLink>
      <S.ProductName>KDU Stock Market</S.ProductName>
      <S.NavLinks>
  
       
         <Link to="/summarizer" style={{ textDecoration: 'none' }}><S.NavLinkStyled> Summarizer</S.NavLinkStyled></Link>
       <Link to="/portfolio" style={{ textDecoration: 'none' }}><S.NavLinkStyled>My Portfolio</S.NavLinkStyled></Link>
       
      </S.NavLinks>
    </S.NavbarContainer>
  );
};

export default Navbar;
