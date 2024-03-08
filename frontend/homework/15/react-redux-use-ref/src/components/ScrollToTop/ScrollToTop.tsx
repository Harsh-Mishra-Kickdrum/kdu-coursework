// ScrollToTop.tsx
// This component provides a "Scroll to Top" button that becomes visible when the user scrolls down a certain amount on the page.

// Importing useEffect for side effects and useRef for referencing DOM elements from React.
import { useEffect, useRef } from "react";
// Importing the specific stylesheet for styling this component.
import "./ScrollToTop.scss";

// Defining the ScrollToTop component as a functional component with TypeScript.
export const ScrollToTop: React.FC = () => {
  // Creating a ref for the button element to directly manipulate its properties.
  const ref = useRef<HTMLButtonElement>(null);

  // useEffect hook to add and clean up the scroll event listener.
  useEffect(() => {
    // Defining the handleScroll function to dynamically show or hide the button based on scroll position.
    const handleScroll = () => {
      if (ref.current) {
        // Setting the display style of the button depending on the scroll position.
        ref.current.style.display = window.scrollY > 100 ? "block" : "none";
      }
    };

    // Adding the scroll event listener to the window object.
    window.addEventListener("scroll", handleScroll);
    // Cleanup function to remove the event listener when the component unmounts or re-renders.
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  // handleClick function to smoothly scroll the user back to the top of the page.
  const handleClick = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  // Returning a button element that is conditionally displayed based on the user's scroll position.
  // The button is styled with the class "scroll-top-btn" and is bound to the handleClick function on click.
  return (
    <button className="scroll-top-btn" ref={ref} onClick={handleClick}>
      Scroll to Top
    </button>
  );
};
