import styled from "styled-components";

interface SnackbarContainerProps {
  type: "success" | "error";
}

export const SnackbarContainer = styled.div<SnackbarContainerProps>`
  position: fixed;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  padding: 16px;
  color: white;
  border-radius: 5px;
  text-align: center;
  z-index: 1000; 
  background-color: ${({ type }) => (type === "success" ? "green" : "red")};
  box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
`;
