// AddItemForm.tsx
import React, { useState } from "react";
import { useTodos } from "../../context/TodoContext"; 
import "./AddItemForm.scss";


/**
 * A React functional component for adding items.
 *
 * @return {ReactElement} The add item form component
 */

const AddItemForm: React.FC = () => {
  const [text, setText] = useState("");
  const { addTodo } = useTodos();

  /**
   * Handles the form submission event by preventing the default behavior, adding a trimmed todo item, and resetting the text input.
   *
   * @param {React.FormEvent<HTMLFormElement>} event - the form submission event
   * @return {void}
   */
  
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    addTodo(text.trim());
    setText("");
  };

  return (
    <>
      <p>Add Items</p>
      <form className="add-item-form" onSubmit={handleSubmit}>
        <input
          className="inputField"
          type="text"
          placeholder=""
          value={text}
          onChange={(e) => setText(e.target.value)}
        />
        <button className="submitButton" type="submit">
          Submit
        </button>
      </form>
      <p>Items</p>
    </>
  );
};

export default AddItemForm;
