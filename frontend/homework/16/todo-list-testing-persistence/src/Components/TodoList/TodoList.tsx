import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../redux/store/configureStore";
import Item from "../Item/Item";
import { deleteTodo } from "../../redux/reducers/todoSlice";


/**
 * Renders a list of todos based on the search term.
 *
 * @return {JSX.Element} The rendered todo list
 */

const TodoList: React.FC = () => {
  const todos = useSelector((state: RootState) => state.todos.todos);
  const searchTerm = useSelector((state: RootState) => state.search.searchTerm); // Access search term
  const dispatch = useDispatch();

  const emptyMessage = "No items found.";

  // Filter todos based on the search term
  const filteredTodos = todos.filter((todo) =>
    todo.text.toLowerCase().includes(searchTerm.toLowerCase())
  );

  if (!filteredTodos.length) {
    return <p className="empty-message">{emptyMessage}</p>;
  }

  /**
   * Function to handle the deletion of a todo item.
   *
   * @param {string} id - The ID of the todo item to be deleted
   * @return {void}
   */

  const onDelete = (id: string) => {
    dispatch(deleteTodo(id));
  };

  return (
    <ul className="todo-list">
      {filteredTodos.map((todo) => (
        <Item key={todo.id} todo={todo} onDelete={onDelete} />
      ))}
    </ul>
  );
};

export default TodoList;
