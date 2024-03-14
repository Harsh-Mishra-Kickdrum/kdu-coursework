import React from "react";
import { TodoItem } from "../../../type";
import "./Item.scss";
interface ItemProps {
  todo: TodoItem;
  onDelete: (id: string) => void;

}


/**
 * React functional component for rendering an item.
 *
 * @param {ItemProps} todo - the item to be rendered
 * @param {Function} onDelete - callback function for deleting the item
 * @return {JSX.Element} The rendered item component
 */


const Item: React.FC<ItemProps> = ({ todo, onDelete }) => {
  /**
   * This function handles the deletion of a todo item.
   *
   * @param {type} paramName - description of parameter
   * @return {type} description of return value
   */
  const handleDelete = () => {
    onDelete(todo.id);
  };

  return (
    <div className="item">
      <span>{todo.text}</span>
      <button className="delete" onClick={handleDelete}>
        X
      </button>
    </div>
  );
};

export default Item;
