import React from "react";
import "./Spinner.scss";


/**
 * React functional component for displaying a spinner.
 *
 * @return {ReactElement} The spinner component
 */

const Spinner: React.FC = () => {
  return (
    <div className="spinner">
      <div className="spinner-inner" />
    </div>
  );
};


export default Spinner;
