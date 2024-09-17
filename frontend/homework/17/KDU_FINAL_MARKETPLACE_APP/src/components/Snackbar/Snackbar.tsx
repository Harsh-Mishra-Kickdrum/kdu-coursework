
// Snackbar.tsx
import React, { useEffect } from "react";
import { SnackbarContainer } from "./Snackbar.styles";

interface SnackbarProps {
  message: string;
  type: "success" | "error";
  onClose: () => void; // Add onClose prop
  duration?: number; // Optional duration prop
}


/**
 * A React functional component for displaying a Snackbar message with a specified duration.
 *
 * @param {string} message - The message to be displayed in the Snackbar
 * @param {string} type - The type of the Snackbar (e.g., success, error, warning)
 * @param {Function} onClose - The function to be called when the Snackbar is closed
 * @param {number} duration - The duration in milliseconds for which the Snackbar is displayed (default is 3000)
 * @return {ReactElement} The SnackbarContainer component with the specified type and message
 */

const Snackbar: React.FC<SnackbarProps> = ({ message, type, onClose, duration = 3000 }) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose(); // Call onClose to hide the Snackbar after the duration
    }, duration);

    return () => clearTimeout(timer); // Clean up the timer
  }, [onClose, duration]);

  return <SnackbarContainer type={type}>{message}</SnackbarContainer>;
};

export default Snackbar;
