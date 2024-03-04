// client/src/features/dashboard/StockList.styles.ts
import styled from 'styled-components';

export const StockListContainer = styled.div`
  margin-top: 20px;
  margin-bottom: 20px; /* Add margin at the bottom to create space */
  height: 100vh; /* Set a fixed height for the container */
//   overflow: auto; /* Enable scrolling */

`;

export const Table = styled.table`
  width: 80%;
  margin-top: 10px;
  margin-left: 10%;
  border: 4px solid #5a6065;
  border-radius: 1em;
  background-color: #f8f9fa;
  margin-bottom: 20px; /* Set a fixed height for the container */
  cell-spacing: 0;
  th,
  td {
    border-bottom: 1px solid #ddd; /* Change to a solid grey line */
    padding: 10px;
    text-align: left;
    background: transparent;
    padding-top: 20px;
    padding-bottom: 20px;
  }

  th {
    background-color: #f8f9fa;
    border-bottom: 3px solid #5a6065;
    padding-top: 5px;
    padding-bottom: 10px;
    padding-left: 20px;
    // border:none;
  }

  td {
    padding-right: 50px;
    padding-left: 20px;
    &:first-child {
      &:hover {
              
        font-weight: bold;
      }
    }

    tr {
      margin-bottom: 10px; /* Add margin at the bottom of each row */
      // border-bottom: 25px solid #f8f9fa;
      border-bottom: 25px solid black;
      margin-left: 10px;
      margin-right: 10px;
    }
  }
`;


export const AddToWatchlistButton = styled.button<{ isAdded: boolean; isHovered: boolean }>`
  color: white;
  border: none;
  cursor: pointer;
  text-align: center;
  justify-content: center;
  border-radius: 50%;
  background-color: white;

  &:hover {

    color: white;
  }

  .pi-times {
    background-color: white !important;
    color: red !important;
  }

  .pi-check-circle{
    border-radius:100%;
    font-size:18px !important;
  }
`;





export const Pagination = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 20px ;
  margin-bottom: 20px ;
  margin-left: 34%;

  ul {
    list-style: none;
    display: flex;
    padding: 0;

    li {
      margin: 0 5px;
      cursor: pointer;
      padding: 5px 10px;
      border:none;
        color: #1971c2;
        border-radius: 50%;

      &.active {
        background-color: #e9ecef;
        border : 2px solid #1971c2;
        color: #1971c2;
      }
    }
  }
`;

export const Arrow = styled.button`
  color: #1971c2;
  background-color: transparent !important;
  border: none;
  cursor: pointer;
  font-size: 2em;
  text-align: center;
  padding: 5px 10px;
  border-radius: 3px;
  margin: 0 5px;
  
  &:disabled {
    background-color: transparent !important;
    cursor: not-allowed;
  }
`;



