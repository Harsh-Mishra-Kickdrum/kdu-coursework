import React from "react";
import { Provider } from "react-redux"; 
import {store} from "./redux/store/configureStore"; 
import Header from "./Components/Header/Header";
import AddItemForm from "./Components/AddItemForm/AddItemForm";
import TodoList from "./Components/TodoList/TodoList";
import "./App.css";


/**
 * React functional component for rendering the main application.
 *
 * @return {JSX.Element} The rendered main application component
 */

const App: React.FC = () => {
  return (
    <Provider store={store}>
      <div className="container">
        <Header title="My TODO List" />
        <div className="contentBox">
          <AddItemForm />
          <TodoList />
        </div>
      </div>
    </Provider>
  );
};

export default App;
