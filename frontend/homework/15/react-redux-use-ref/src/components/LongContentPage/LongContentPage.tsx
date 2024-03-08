// LongContentPage.tsx
// This component is designed to demonstrate a page with long content, including a "Scroll to Top" button.

// Importing React from the react package.
import React from "react";
// Importing the ScrollToTop component for the "Scroll to Top" functionality.
import { ScrollToTop } from "../ScrollToTop/ScrollToTop";
// Importing the specific stylesheet for styling this component.
import "./LongContentPage.scss";

// Defining the LongContentPage component as a functional component with TypeScript.
const LongContentPage: React.FC = () => {
  // The component returns a div with a class "container" wrapping the entire content.
  return (
    <div className="container">
      {/* // A heading is displayed at the top of the page. */}
      <h1 className="heading">Long Content Page</h1>

      {/* // Using Array(20).keys() to generate an array of 20 elements, mapped to */}
      {/* paragraphs to simulate long content. */}
      {[...Array(20).keys()].map((i) => (
        
        // Each paragraph has a class "para-content" for styling and a unique key prop.
        <p className="para-content" key={i}>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque
          et euismod ligula. Morbi mattis pretium eros, et ultricies felis
          viverra id. Nulla facilisi. Donec ac venenatis elit, et efficitur dui.
          Sed in massa vitae nunc fermentum molestie. Praesent ac nisi ac dui
          dapibus pretium in nec arcu. Aliquam erat volutpat.
        </p>
      ))}

      {/* // Including the ScrollToTop component to allow users to easily navigate
      back to the top of the page. */}

      <ScrollToTop />
    </div>
  );
};

// Exporting the component to be used in other parts of the application.
export default LongContentPage;
