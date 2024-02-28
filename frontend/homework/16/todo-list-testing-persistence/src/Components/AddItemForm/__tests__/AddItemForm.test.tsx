// Import necessary utilities and components from their respective libraries
import React from "react";
import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { Provider } from "react-redux";
import configureStore from "redux-mock-store"; // A library for creating mock Redux stores
import AddItemForm from "../AddItemForm"; // The component to be tested
import { addTodo } from "../../../redux/reducers/todoSlice"; // The action creator to be tested

// Setup a mock Redux store using redux-mock-store
const mockStore = configureStore([]);
const store = mockStore({});

// Describe a test suite for the AddItemForm component
describe("AddItemForm", () => {
  // Define a test case
  it("updates input field correctly and dispatches addTodo action on form submit", async () => {
    // Render the AddItemForm component within a mock Redux Provider
    render(
      <Provider store={store}>
        <AddItemForm />
      </Provider>
    );

    // Query the input field by its placeholder text and simulate user typing
    const inputField = screen.getByPlaceholderText("Add new todo");
    userEvent.type(inputField, "New Todo Item");

    // Assert that the input field updates asynchronously as the user types
    await screen.findByDisplayValue("New Todo Item");

    // Query the submit button by its role and accessible name, then simulate a click event
    const submitButton = screen.getByRole("button", { name: /submit/i });
    userEvent.click(submitButton);

    // Wait for the expected Redux action to be dispatched upon form submission
    await waitFor(() => {
      const actions = store.getActions(); // Retrieve the list of dispatched actions from the mock store
      // Assert that the dispatched actions contain the expected addTodo action with the correct payload
      expect(actions).toEqual([addTodo("New Todo Item")]);
    });
  });
});
