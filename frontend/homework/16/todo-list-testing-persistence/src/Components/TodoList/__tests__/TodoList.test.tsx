import React from "react";
import { Provider } from "react-redux";
import configureMockStore from "redux-mock-store";
import { render, fireEvent, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import TodoList from "../TodoList";
import { RootState } from "../../../redux/store/configureStore";

// Initialize the mock store with a type parameter for better type checking
const mockStore = configureMockStore<Partial<RootState>>();

describe("TodoList", () => {
  it("renders the todo list and deletes an item", () => {
    // Define the initial state for the test, including two todos
    const initialState: Partial<RootState> = {
      todos: {
        todos: [
          { id: "1", text: "First todo", completed: false },
          { id: "2", text: "Second todo", completed: false },
        ],
      },
      search: {
        searchTerm: "",
      },
    };

    // Create a mock store with the initial state
    const store = mockStore(initialState);
    // Mock the dispatch function
    store.dispatch = vi.fn();

    // Render the TodoList component within a Redux Provider
    render(
      <Provider store={store}>
        <TodoList />
      </Provider>
    );

    // Assert that both todos are rendered
    expect(screen.getByText("First todo")).toBeInTheDocument();
    expect(screen.getByText("Second todo")).toBeInTheDocument();

    // Simulate the deletion of the first todo by clicking the delete button
    // Assume the delete buttons are labeled with "X"
    const deleteButtons = screen.getAllByText("X");
    fireEvent.click(deleteButtons[0]);

    // Verify that the correct delete action was dispatched
    expect(store.dispatch).toHaveBeenCalledWith({
      type: "todos/deleteTodo",
      payload: "1",
    });
  });

  it("displays a message when no todos match the search term", () => {
    // Define an initial state where no todos match the current search term
    const initialState: Partial<RootState> = {
      todos: {
        todos: [],
      },
      search: {
        searchTerm: "nonexistent",
      },
    };

    // Create a mock store with this initial state
    const store = mockStore(initialState);

    // Render the TodoList component
    render(
      <Provider store={store}>
        <TodoList />
      </Provider>
    );

    // Verify that a message indicating no items found is displayed
    expect(screen.getByText("No items found.")).toBeInTheDocument();
  });
});
