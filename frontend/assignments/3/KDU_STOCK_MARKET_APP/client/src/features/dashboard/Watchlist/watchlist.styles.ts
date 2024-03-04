// watchlist.styles.ts
import styled from 'styled-components';

export const WatchListContainer = styled.div`
  margin-top: 20px;
  margin-bottom: 20px;
  height: 100vh; 
`;

export const Table = styled.table`

  width: 80%;
  margin-top: 10px;
  margin-left: 10%;
  border: 4px solid #5a6065;
  border-radius: 1em;
  background-color: #f8f9fa;
  margin-bottom: 20px;
  cell-spacing: 0;
  th,
  td {
    border-bottom: 1px solid #ddd;
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
    // border: none;
  }

  td {
    padding-right: 50px;
    padding-left: 20px;
    width:25%;

    &:first-child {
      &:hover {
        font-weight: bold; // Make text bold on hover
      }
    }
    }

    tr {
      margin-bottom: 10px;
      border-bottom: 25px solid grey;
      margin-left: 10px;
      margin-right: 10px;
    }
  }
`;



export const RemoveFromWatchlistButton = styled.button`
  color: #f8f9fa;
  background-color: white;
  border: none;
  text-align: center;
  justify-content: center;
  padding: 5px 10px;
  padding-right: 10px;
  cursor: pointer;
  .pi {
    background-color: #f8f9fa !important;
  }
  .pi-times {
    background-color: #f8f9fa !important;
    color: red !important;
  }
`;

export const Pagination = styled.div`
  display: flex;
  position: relative;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 35rem;
  // margin-right: ;

  ul {
    list-style: none;
    display: flex;
    padding: 0;

    li {
      margin: 0 5px;
      cursor: pointer;
      padding: 5px 10px;
      border: none;
      color: #1971c2;
      border-radius: 50%;

      &.active {
        background-color: #e9ecef;
        border: 2px solid #1971c2;
        color: #1971c2;
      }
    }
  }
`;

export const paginationsection = styled.div`
width:100%;
display:flex;
align-items: center;
justify-content: center;
text-align:center;
margin-left:110px !important;
`

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
