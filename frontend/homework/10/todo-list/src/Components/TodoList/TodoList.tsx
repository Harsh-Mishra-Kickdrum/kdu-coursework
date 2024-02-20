import React from "react";
import {TodoItem} from "../../../type";
import Item from "../Item/Item";
interface TodoListProps {
  todos: TodoItem[];
  onDelete: (id: string) => void;
}


/**
 * This function handles the deletion of a todo item.
 *
 * @param {undefined} - no parameters
 * @return {void} no return value
 */
const TodoList: React.FC<TodoListProps> = ({ todos, onDelete }) => {
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
