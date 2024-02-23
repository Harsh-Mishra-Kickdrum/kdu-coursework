// TodoList.tsx
import React from "react";
import { useTodos } from "../../context/TodoContext"; 
import Item from "../Item/Item";

/**
 * TodoList component that displays a list of todos and allows the user to delete them.
 *
 * @return {JSX.Element} The JSX for the todo list component
 */

const TodoList: React.FC = () => {
  const { filteredTodos: todos, deleteTodo: onDelete } = useTodos();
  const emptyMessage = "No items found.";

  if (!todos.length) {
    return <p className="empty-message">{emptyMessage}</p>;
  }

  return (
    <ul className="todo-list">
      {todos.map((todo) => (
        <Item key={todo.id} todo={todo} onDelete={onDelete} />
      ))}
    </ul>
  );
};

export default TodoList;
