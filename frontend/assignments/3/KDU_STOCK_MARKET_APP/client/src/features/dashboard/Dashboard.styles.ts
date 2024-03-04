// client/src/features/dashboard/Dashboard.styles.ts
import styled, { css } from 'styled-components';

interface TabProps {
  active: boolean;
}

export const SpinnerContainer = styled.div<{ loading: boolean }>`
  // display: ${(props) => (props.loading ? 'flex' : 'none')};
  // align-items: center;
  // justify-content: center;
  // position: fixed;
  // top: 0;
  // left: 0;
  // width: 100%;
  // height: 100%;
  // background-color: rgba(255, 255, 255, 0.7); /* Semi-transparent white background */
  // z-index: 1000;
`;

export const Spinner = styled.div`
  border: 16px solid #f3f3f3;
  // border-top: 16px solid #3498db;
  // border-radius: 50%;
  // width: 120px;
  // height: 120px;
 
`;


export const DashboardContainer = styled.div`
  padding: 20px;
`;

export const Tabs = styled.div`
  display: flex;
  margin-bottom: 20px;
`;

export const Tab = styled.div<TabProps>`
  padding: 10px;
  margin: 0;
  cursor: pointer;
  user-select: none;
  color: black;

  ${(props) =>
    props.active &&
    css`
      text-decoration: underline;
      text-decoration-color: #1971c2;
      text-underline-offset: 8px;
      text-decoration-thickness: 3px;
    `}
`;

export const TabContent = styled.div<TabProps>`
  display: ${(props) => (props.active ? 'block' : 'none')};
`;

export const Table = styled.table`
  width: 80%;
  margin-top: 10px;
  margin-left: 10%;
  border: 4px solid #5a6065;
  border-radius: 1em;
  background-color: #f8f9fa;
  cell-spacing: 0;

  th,
  td {
    border-bottom: 1px solid #5a6065;
    padding: 10px;
    text-align: left;
    background: transparent;
    padding-top: 20px;
    padding-bottom: 20px;
  }

  th {
    background-color: #f8f9fa;
    border-bottom: 3px solid #5a6065;
    padding-top: 20px;
    padding-bottom: 20px;
  }

  td {
    &:first-child {
      font-weight: bold;
    }
  }
`;

export const AddToWatchlistButton = styled.button`
  background-color: #1971c2;
  color: white;
  border: none;
  cursor: pointer;
  text-align: center;
  justify-content: center;
  margin-left: 40%;
  border-radius: 50%;
`;

export const RemoveFromWatchlistButton = styled.button`
  background-color: #d9534f;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
`;

export const StockListContainer = styled.div`
  margin-top: 20px;
  height: 400px; /* Set a fixed height for the container */
  overflow: auto; /* Enable scrolling */
`;

export const WatchListContainer = styled.div`
  margin-top: 20px;
`;



