import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { TodoItem } from "../../type/type";
import {
  toggleTodo,
  deleteTodo,
  editTodo,
} from "../../redux/reducers/todoSlice"; 
import "./Item.scss";

interface ItemProps {
  todo: TodoItem;
  onDelete: (id: string) => void;
}

const Item: React.FC<ItemProps> = ({ todo }) => {
  const dispatch = useDispatch();
  const [isEditing, setIsEditing] = useState(false);
  const [editText, setEditText] = useState(todo.text);

  // Toggle completion status
  const handleToggle = () => {
    dispatch(toggleTodo(todo.id));
  };

  // Delete a todo item
  const handleDelete = () => {
    dispatch(deleteTodo(todo.id));
  };

  // Start editing a todo item
  const handleEdit = () => {
    setIsEditing(true);
  };

  // Save the edited todo item
  const handleSaveEdit = () => {
    dispatch(
      editTodo({
        id: todo.id,
        text: editText,
      })
    );
    setIsEditing(false);
  };

  // Handle edit text change
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEditText(e.target.value);
  };

  return (
    <div className={`item ${todo.completed ? "completed" : ""}`}>
      {isEditing ? (
        <input className="edit"
          type="text"
          value={editText}
          onChange={handleChange}
          onBlur={handleSaveEdit} // Save on losing focus
          onKeyDown={(e) => e.key === "Enter" && handleSaveEdit()} // Save on Enter key
        />
      ) : (
        <>
          <input className="checkbox"
            type="checkbox"
            checked={todo.completed}
            onChange={handleToggle}
          />
          <span className="text" onDoubleClick={handleEdit}>{todo.text}</span>
          <button className="delete" onClick={handleDelete}>
            X
          </button>
        </>
      )}
    </div>
  );
};

export default Item;
