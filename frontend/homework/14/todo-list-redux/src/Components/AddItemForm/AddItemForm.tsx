// AddItemForm.tsx
import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "../../redux/reducers/todoSlice";
import "./AddItemForm.scss";

/**
 * A React functional component for adding items.
 *
 * @return {ReactElement} The add item form component
 */
const AddItemForm: React.FC = () => {
  const [text, setText] = useState("");
  const dispatch = useDispatch();

  /**
   * Handles the form submission event by preventing the default behavior,
   * dispatching the addTodo action with the trimmed todo item,
   * and resetting the text input.
   *
   * @param {React.FormEvent<HTMLFormElement>} event - the form submission event
   * @return {void}
   */
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (text.trim()) {
      dispatch(addTodo(text.trim()));
      setText("");
    }
  };

  return (
    <>
      <p>Add Items</p>
      <form className="add-item-form" onSubmit={handleSubmit}>
        <input
          className="inputField"
          type="text"
          placeholder="Add new todo"
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
