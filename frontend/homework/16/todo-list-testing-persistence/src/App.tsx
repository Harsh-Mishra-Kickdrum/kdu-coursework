import React from "react";
import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react";
import { store, persistor } from "./redux/store/configureStore";
import Header from "./Components/Header/Header";
import AddItemForm from "./Components/AddItemForm/AddItemForm";
import TodoList from "./Components/TodoList/TodoList";
import "./App.css";

/**
 * React functional component for rendering the main application with Redux Persist integration.
 *
 * @return {JSX.Element} The rendered main application component
 */
const App: React.FC = () => {
  return (
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        {" "}
        {/* Delay rendering until persisted state has been retrieved */}
        <div className="container">
          <Header title="My TODO List" />
          <div className="contentBox">
            <AddItemForm />
            <TodoList />
          </div>
        </div>
      </PersistGate>
    </Provider>
  );
};

export default App;
