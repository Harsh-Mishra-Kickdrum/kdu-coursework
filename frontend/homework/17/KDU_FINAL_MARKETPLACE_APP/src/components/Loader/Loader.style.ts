// src/components/Loader/Loader.styles.ts
import styled, { keyframes } from "styled-components";

const spin = keyframes`
  0% {
    transform: translate(-50%, -50%) rotate(0deg);
  }
  100% {
    transform: translate(-50%, -50%) rotate(360deg);
  }
`;

export const Loader = styled.div`
  border: 16px solid #f3f3f3; /* Light grey */
  border-top: 16px solid #3498db; /* Blue */
  border-radius: 50%;
  width: 120px;
  height: 120px;
  animation: ${spin} 2s linear infinite;
  position: fixed; /* or absolute */
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
`;
