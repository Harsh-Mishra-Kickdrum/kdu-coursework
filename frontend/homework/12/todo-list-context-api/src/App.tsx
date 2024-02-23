import React from "react";
import Header from "./Components/Header/Header";
import AddItemForm from "./Components/AddItemForm/AddItemForm";
import TodoList from "./Components/TodoList/TodoList";
import { TodoProvider } from "./context/TodoContext"; 
import "./App.css";

const App: React.FC = () => {
  return (
    <TodoProvider>
      <div className="container">
        <Header title="My TODO List" />
        <div className="contentBox">
          <AddItemForm />
          <TodoList />
        </div>
      </div>
    </TodoProvider>
  );
};

export default App;
