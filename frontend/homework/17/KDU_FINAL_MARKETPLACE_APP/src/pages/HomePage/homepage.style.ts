// homepage.style.ts
import styled from "styled-components";



export const Section = styled.section`
  margin: auto;
  background-color:#f3f3f3;
  margin-top: -35px;
`;

export const Title = styled.h1`
  text-align: center;
  font-size: 2.5rem;
  color: #2a2a72;
  letter-spacing: 2px;
  margin-bottom: 20px;
  padding-top: 60px;
  margin-top:100px;
`;

export const pi = styled.b`
  text-align: center;
  font-size: 2.5rem;
  color: #000000;
  letter-spacing: 2px;
  margin-bottom: 20px;
`;

export const ProductsContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 50px;
  margin-left: 30px;
`;

// export const EmptyContainer = styled.div`
//   text-align: center;
//   width: 100%;
//   font-size: 2rem;
// `;



export const EmptyContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; // Ensure it covers the full viewport height
  width: 100vw; // Ensure it covers the full viewport width
  text-align: center;
  position: fixed; // Keep it constant, overlaying other content
  top: 0;
  left: 0;
  margin: 0; // Remove default margins
  padding: 0; // Remove default padding
  background: white; // Or any background color that fits your design
  z-index: 10; // Ensure it's above other content (adjust as necessary)
`;
