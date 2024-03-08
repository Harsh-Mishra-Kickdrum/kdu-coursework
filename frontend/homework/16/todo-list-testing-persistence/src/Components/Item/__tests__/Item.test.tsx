import React from "react";
import { Provider } from "react-redux";
import configureMockStore from "redux-mock-store";
import { render, screen } from "@testing-library/react";
import { describe, it, expect, vi } from "vitest";
import Item from "../Item";
import { TodoItem } from "../../../type/type";

// Initialize the mock store creator
const mockStore = configureMockStore();

describe("Item Component", () => {
  // Define a mock todo item for testing
  const mockTodo: TodoItem = {
    id: "1",
    text: "Test Todo",
    completed: false,
  };

  // Setup function to render the component within a test
  const setup = (todo: TodoItem = mockTodo) => {
    // Create a mock store with predefined state
    const store = mockStore({
      todos: [mockTodo],
    });

    // Mock the onDelete function using vitest's function mocking
    const onDeleteMock = vi.fn();

    // Render the Item component within a Redux Provider and return utilities for testing
    return {
      ...render(
        <Provider store={store}>
          <Item todo={todo} onDelete={onDeleteMock} />
        </Provider>
      ),
      store,
      onDeleteMock,
    };
  };

  it("renders the todo item correctly", () => {
    // Call the setup function to render the component
    setup();

    // Assert that the todo item's text is rendered in the document
    expect(screen.getByText("Test Todo")).toBeInTheDocument();

    // Assert that the checkbox indicating completion status is not checked for a new todo
    expect(screen.getByRole("checkbox")).not.toBeChecked();
  });

});
