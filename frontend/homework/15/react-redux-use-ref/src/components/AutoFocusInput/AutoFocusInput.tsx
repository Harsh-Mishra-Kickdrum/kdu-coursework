// AutoFocusInput.tsx
// This component demonstrates how to automatically focus an input field when the component mounts using React hooks.

// Importing necessary hooks from React
import { useRef, useEffect } from "react";
// Importing the specific stylesheet for styling this component
import "./AutoFocusInput.scss";

// Defining the AutoFocusInput component as a functional component with TypeScript
export const AutoFocusInput: React.FC = () => {
  // Using the useRef hook to create a reference to the input element. This allows us to interact with the DOM directly.
  const inputRef = useRef<HTMLInputElement>(null);

  // The useEffect hook is used here to perform side effects in the component.
  // In this case, it's used to automatically focus the input element when the component mounts.
  // The empty dependency array ([]) means this effect runs once after the initial render.
  useEffect(() => {
    // The conditional chaining (?.) ensures that focus() is called only if inputRef.current is not null.
    inputRef.current?.focus();
  }, []);

  // Rendering the component's UI
  // The div with className "input-area" wraps the input element to allow for additional styling or structure.
  // The input element is given a className for styling and the ref attribute is set to inputRef to link the DOM element to the ref created above.
  // The placeholder attribute provides a hint to the user about what to enter in the input field.
  return (
    <div className="input-area">
      <input
        className="input-text"
        ref={inputRef}
        type="text"
        placeholder="Auto Focused Input ..."
      />
    </div>
  );
};
