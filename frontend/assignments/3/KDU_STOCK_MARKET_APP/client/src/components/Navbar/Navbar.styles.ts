// client/src/components/Navbar/Navbar.styles.ts
import styled from 'styled-components';

export const NavbarContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 1rem;
  background-color: #1871c1;
  color: white;
  font-size: 1.2rem;
  height: 40px;
`;

export const LogoLink = styled.a`
  text-decoration: none;
`;

export const Logo = styled.img`
  width: 100px;
  height: 40px;
  padding: 0;

  &:hover {
    opacity: 0.8;
  }
`;

export const ProductName = styled.div`
  text-align: left;
  display: flex;
  width: 70%;
  color: white !important; /* Set color to white with !important */

`;

export const NavLinks = styled.div`
  display: flex;
  justify-content: flex-end;
  text-decoration: none;
  margin-left:10%;
`;

export const NavLink = styled.a`
  margin-left: 1rem;
  text-decoration: none;
  color: white !important; /* Set color to white with !important */
  opacity: 1;
  transition: opacity 0.3s ease;

  &:hover {
    opacity: 0.8;
    text-decoration: none; /* Remove underline on hover */
  }
`;

export const NavLinkStyled = styled(NavLink)`
  margin-left: 1rem;
  &:hover {
    opacity: 0.8;
    text-decoration: none; /* Remove underline on hover */
  }
`;

