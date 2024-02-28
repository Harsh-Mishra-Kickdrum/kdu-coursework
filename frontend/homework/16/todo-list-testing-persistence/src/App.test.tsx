import React from "react";
import { render, screen } from "@testing-library/react";
import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react";
import { store, persistor } from "./redux/store/configureStore";
import App from "./App";
import { describe, it, expect, vi } from "vitest";

// Mock components that are imported into App to isolate the test
vi.mock("./Components/Header/Header", () => ({
  default: () => <div>HeaderMock</div>,
}));
vi.mock("./Components/AddItemForm/AddItemForm", () => ({
  default: () => <div>AddItemFormMock</div>,
}));
vi.mock("./Components/TodoList/TodoList", () => ({
  default: () => <div>TodoListMock</div>,
}));

describe("App component", () => {
  it("renders without crashing", () => {
    render(
      <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
          <App />
        </PersistGate>
      </Provider>
    );

    expect(screen.getByText("HeaderMock")).toBeInTheDocument();
    expect(screen.getByText("AddItemFormMock")).toBeInTheDocument();
    expect(screen.getByText("TodoListMock")).toBeInTheDocument();
  });
});
