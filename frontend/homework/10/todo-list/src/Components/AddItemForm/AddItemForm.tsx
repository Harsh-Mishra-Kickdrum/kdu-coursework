import React, { useState } from "react";
import "./AddItemForm.scss"


interface AddItemFormProps {
  onSubmit: (text: string) => void;
}


/**
 * React component for a form to add an item.
 *
 * @param {AddItemFormProps} onSubmit - Function to handle form submission
 * @return {ReactElement} The form component
 */

const AddItemForm: React.FC<AddItemFormProps> = ({ onSubmit }) => {
  const [text, setText] = useState("");

  /**
   * Handles the form submission event.
   *
   * @param {React.FormEvent<HTMLFormElement>} event - the form submission event
   * @return {void}
   */
  
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    onSubmit(text.trim()); // Trim whitespace
    setText("");
  };

  return (
    <>
    <p >Add Items</p>
      <form className="add-item-form" onSubmit={handleSubmit}>
        <input className="inputField"
          type="text"
          placeholder=""
          value={text}
          onChange={(e) => setText(e.target.value)}
        />
        <button className="submitButton" type="submit">Submit</button>
      </form>
      <p>Items</p>
    </>
  );
};

export default AddItemForm;
