import React from "react";
import styled from "styled-components";

export const SubmitAndPrice = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #f5f5f5;
  border-radius: 5px;
  margin-bottom: 20px;
`;

export const PriceText = styled.h1`
  font-size: 24px;
  margin: 0;
  color: #333;
`;

export const SubmitButton = styled.button`
  background-color: #f08a5d;
  color: #fff;
  padding: 8px 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;

  &:hover {
    background-color: #fff;
    color: #f08a5d;
    border: 2px solid #f08a5d;
  }
`;

export const SubmitAndPriceWithPrice: React.FC<{ price: number }> = ({
  price,
}) => {
  return (
    <SubmitAndPrice>
      <PriceText>Cost + 185 GST = {price}</PriceText>
      <SubmitButton>Submit</SubmitButton>
    </SubmitAndPrice>
  );
};
