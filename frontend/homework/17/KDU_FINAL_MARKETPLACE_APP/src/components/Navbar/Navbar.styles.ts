// Navbar.styles.ts
import styled from "styled-components";
import { Link } from "react-router-dom";


export const NavbarContainer = styled.nav`
  background-color: #2a2a72;
  display: flex;
  align-items: center;
  height: 3em;
  padding: 10px 20px;
  justify-content: space-between;
  position: fixed; // Make navbar fixed at the top
  top: 0; // Align to top
  left: 0; // Align to left
  width: 100%; // Take full width
  z-index: 1000; // Ensure it's on top of other content
`;



export const LeftContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

export const RightContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

export const ActionItems = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;

  select {
    background-color: white;
    color: black;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    margin-right: 50px; /* Add spacing to the right of the dropdown menus */
  }
`;

export const SearchInput = styled.input`
  height: 30px;
  background-color: white;
  color: black;
  border: 1px solid #ccc;
  padding: 0 10px;
`;

export const WhiteText = styled.span`
  color: white;
`;

export const SearchButtonContainer = styled.div`
  height: 32px;
  display: flex; /* Add display: flex for easier alignment */
  align-items: center; /* Align items vertically */
  vertical-align: top;
`;

export const SearchButton = styled.button`
  height: 105%;
  width: 30px;
  padding-left: 10px;
  background-color: #f3f3f3;
  border: 1px solid black;
  border-radius: 0px;
  color: black;
  margin-left:-20px;

  cursor: pointer;
  text-align: left;
  vertical-align: middle; /* Align the icon vertically in the middle */
`;

export const MobileSearchContainer = styled.div`
  display: none;
  @media (max-width: 768px) {
    display: flex;
    align-items: center;
    gap: 10px;
  }
`;

export const MobileActionItems = styled.div`
  display: none;
  @media (max-width: 768px) {
    display: flex;
    align-items: center;
    gap: 10px;
  }
`;

export const MobileSearchInput = styled.input`
  height: 30px;
  background-color: white;
  color: black;
  border: 1px solid #ccc;
  padding: 0 10px;
  width: 80px;
`;

export const MobileSearchButton = styled.button`
  height: 30px;
  width: 30px;
  background-color: white;
  color: black;
  border: none;
  cursor: pointer;
`;

export const MobileBackButton = styled(Link)`
  border: 1px solid #2996e1;
  border-radius: 5px;
  color: #2996e1;
  background-color: transparent;
  padding: 5px 10px;
  text-decoration: none;
  cursor: pointer;
`;
