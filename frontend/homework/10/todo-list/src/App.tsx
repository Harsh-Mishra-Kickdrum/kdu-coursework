import React, { useState, useEffect } from "react";
import Header from "./Components/Header/Header";
import AddItemForm from "./Components/AddItemForm/AddItemForm";
import TodoList from "./Components/TodoList/TodoList";
import { TodoItem } from "../type";
import "../src/App.css";

const App: React.FC = () => {
  const [todos, setTodos] = useState<TodoItem[]>([]);
  const [searchTerm, setSearchTerm] = useState("");

  // Load from localStorage (optional)
  useEffect(() => {
    const storedTodos = localStorage.getItem("todos");
    if (storedTodos) {
      setTodos(JSON.parse(storedTodos));
    }
  }, []);

  // Save to localStorage (optional)
  useEffect(() => {
    localStorage.setItem("todos", JSON.stringify(todos));
  }, [todos]);

  // Add new item
  const addTodo = (text: string) => {
    if (!text.trim()) {
      return; // Ignore empty text
    }
    const newTodo: TodoItem = {
      id: Math.random().toString(36).substring(2, 15),
      text,
    };
    setTodos([...todos, newTodo]);
  };

  // Delete item
  const deleteTodo = (id: string) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  // Filter todos based on search term
  const filteredTodos = todos.filter((todo) =>
    todo.text.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="container">
      <Header title="My TODO List" onSearch={setSearchTerm} />
      <div className="contentBox">
        <AddItemForm onSubmit={addTodo} />
        {filteredTodos.length > 0 ? (
          <TodoList todos={filteredTodos} onDelete={deleteTodo} />
        ) : (
          <p className="empty-message">No TODO items found.</p>
        )}
      </div>
    </div>
  );
};

export default App;
