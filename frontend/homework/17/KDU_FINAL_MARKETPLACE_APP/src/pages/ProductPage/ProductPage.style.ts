// ProductPage.style.ts
import styled from "styled-components";
import { Link } from "react-router-dom";

export const Section = styled.section`
  text-align: center;
  color: black;
  background-color:#f3f3f3;
  height:100vh;
`;

export const Title = styled.h1`
  font-size: 3rem;
  margin-bottom: 20px;
  text-align: center;
  margin-left: -120px;
  color: #2a2a72;
`;

export const Container = styled.div`
  display: flex;
  justify-content: space-around;
  gap: 5%;
  text-align: start;
`;

export const Image = styled.img`
  height: 60vh;
  margin-left: 40px;
`;

export const Details = styled.div`
  display: flex;
  flex-direction: column;
  gap: 30px;
`;

export const Category = styled.h2`
  color: gray;
  margin:0;
`;

export const Price = styled.h3`
  color: #2a2a72;
  margin:0;
  font-weight: bolder;
`;

export const DescriptionHeading = styled.p`
  font-weight: bold;
  margin-bottom: -20px;
`;

export const Description = styled.p`
  margin-bottom: 10px;
`;

export const BackButton = styled(Link)`
  border: 1px solid #2996e1;
  border-radius: 5px;
  color: #2996e1;
  background-color: transparent;
  width: 190px;
  height: 50px;
  font-size: 1.3rem;
  font-weight: bold;
  display: inline-block;
  line-height: 48px;
  text-decoration: none;
  cursor: pointer;
  padding-left: 1rem;
`;

export const NavbarContainer = styled.nav`
  background-color: #2a2a72;
  display: flex;
  align-items: center;
  padding: 10px 90px;
  gap: 15px;
  /* Ensure no top margin */
  width: 100%; /* Full width */
`;

export const SortFilterContainer = styled.div`
  display: none; /* Hide sort and filter */
`;

export const DescriptionContent = styled.p`
  text-align: justify; /* Justify the text inside */
  padding: 10px;
  margin-right: 40px;
  margin-left: -23px;
`;
