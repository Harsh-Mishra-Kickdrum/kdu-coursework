// Import necessary functions and types from Redux Toolkit and the todo slice.
import { configureStore, Store } from "@reduxjs/toolkit";
import { todoSlice, addTodo, TodoItem } from "../reducers/todoSlice";


// Describe a test suite for the todoSlice functionality.
describe("todoSlice", () => {
  let store: Store<any>; // Declare a variable to hold the Redux store.

  // Before each test, configure and initialize the store with the todoSlice reducer.
  beforeEach(() => {
    store = configureStore({ reducer: { todos: todoSlice.reducer } });
  });

  // Test case for adding new todo items.
  it("should handle adding new todos", () => {
    const text = "Test Todo"; // Define the text for the new todo item.
    store.dispatch(addTodo(text)); // Dispatch the addTodo action with the new todo text.
    const state = store.getState().todos; // Retrieve the current state of the todos slice.
    // Find the added todo item in the state by matching its text.
    const addedTodo = state.todos.find((todo: TodoItem) => todo.text === text);

    // Assertions to verify the added todo item's properties.
    expect(addedTodo).not.toBeUndefined(); // Ensure the todo item was added successfully.
    expect(addedTodo.text).toEqual(text); // Verify the text of the added todo matches the input.
    expect(addedTodo.completed).toBe(false); // Check that the new todo item is marked as incomplete.
  });
});
