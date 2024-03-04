

import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  padding: 20px;
`;

export const StockInfoContainer = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  padding: 20px;
  margin-right: 10px;
  width: 75%;
`;

export const DetailsContainer = styled.div`
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap; /* Allows items to wrap in smaller screens */
  gap: 10px; /* Adds some space between the detail boxes */
`;

export const DetailBox = styled.div`
  flex-grow: 1;
  flex-basis: calc(
    20% - 10px
  ); /* Adjusts width to fit 5 items in a row, accounting for gap */
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center content vertically */
  align-items: center; /* Center content horizontally */
  padding: 10px;
  background-color: white;
  border-radius: 5px;
  border:2px solid black;
  height: 40PX;
  font-size:20px;
`;

export const DetailBoxSymbol = styled.div`

  display: flex;
  flex-direction: column;
  justify-content: center; /* Center content vertically */
  align-items: center; /* Center content horizontally */
  padding: 10px;
  background-color: white;
  border-radius: 5px;
  border: 2px solid black;
  height: 200% !important ;
`;


export const GraphContainer = styled.div`
  background-color: #ffffff;
  height: 560px;
  margin-top: 65px;
  border: 2px SOLID BLACK;
`;

export const SideContainer = styled.div`
  width: 25%;
`;

export const HistoryContainer = styled.div`
  background-color: #ffffff;
  padding: 20px;
  margin-bottom: 15px;
  height: 41%;
  border: 2px SOLID BLACK;
  margin-top: 20px;
`;

export const NotificationContainer = styled.div`
  background-color: #ffffff;
  padding: 20px;
  height: 39%;
  border: 2px SOLID BLACK;
`;

export const PriceIndicator = styled.div`
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content:spaced-between;
  svg {
    margin-left: 5px;
    fill: green; // Example for an up arrow
  }
`;

export const ActionButton = styled.button<{ buy?: boolean }>`
  background-color: ${(props) => (props.buy ? "#b2f2bb" : "#ffc9c9")};
  color: ${(props) => (props.buy ? "#76cb83" : "#e33f3f")};
  border: 2px solid ${(props) => (props.buy ? "#76cb83" : "#e33f3f")};
  cursor: pointer;
  &:hover {
    opacity: 0.8;
  }
`;

export const QuantityInput = styled.input`
  padding: 10px;
  border: 1px solid #ffffff;
  border-radius: 5px;
  width: 14%;
  border: 2px solid black;
  font-size:20px;
`;

export const SymbolNameContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`;

export const SmallDetailBox = styled.div`
  padding: 5px;
  background-color: #f9f9f9;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin: 5px;
  text-align: center;
`;

