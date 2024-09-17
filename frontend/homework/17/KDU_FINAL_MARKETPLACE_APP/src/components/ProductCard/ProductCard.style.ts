// ProductCard.style.ts
import styled from "styled-components";
import { Link } from "react-router-dom";

export const Card = styled.div`
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  margin: 10px;
  width: 220px; /* Fixed width */
  height: 400; /* Fixed height */
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  background-color:white;
`;

export const ProductImage = styled.img`
  max-width: 100%;
  max-height: 200px; /* Fixed max height */
  border-radius: 8px;
  margin-bottom: 10px;
`;

export const ProductTitle = styled.h3`
  color: #333;
  margin: 10px 0;
`;

export const ProductPrice = styled.p`
  color: #666;
  margin: 5px 0;
`;

export const DetailsLink = styled(Link)`
  background-color: #007bff;
  color: white;
  padding: 8px 16px;
  text-decoration: none;
  border-radius: 4px;
  margin-top: auto; /* Align to the bottom */
  &:hover {
    background-color: #0056b3;
  }
`;
