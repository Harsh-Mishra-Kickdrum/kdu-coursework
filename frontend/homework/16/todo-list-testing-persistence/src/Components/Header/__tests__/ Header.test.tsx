// Import necessary utilities from React Testing Library and other dependencies
import { render, screen, fireEvent } from "@testing-library/react";
import Header from "../Header"; // The component under test
import { Provider } from "react-redux";
import { configureStore } from "@reduxjs/toolkit";
import rootReducer from "../../../redux/reducers/rootReducer"; // Root reducer to setup the Redux store

// Describes a group of tests for the Header component
describe("Header", () => {
  // Setup a mock Redux store for testing, using the actual rootReducer to closely mimic app state
  const store = configureStore({ reducer: rootReducer });

  // Test case to verify that the Header component renders with a given title
  it("renders with a title", () => {
    const testTitle = "Test Todo App";
    // Render the Header component within a Redux Provider to provide necessary context
    render(
      <Provider store={store}>
        <Header title={testTitle} />
      </Provider>
    );

    // Assert that the provided title is present in the document
    expect(screen.getByText(testTitle)).toBeInTheDocument();
  });

  // Test case to check if the setSearchTerm action is dispatched when the search input changes
  it("dispatches setSearchTerm action on search input change", () => {
    const testTitle = "Test Todo App";
    // Render the Header component, similar to the previous test
    render(
      <Provider store={store}>
        <Header title={testTitle} />
      </Provider>
    );

    // Find the search input by its placeholder text
    const searchInput = screen.getByPlaceholderText("Search Items ...." ) as HTMLInputElement;

    // Simulate typing a new search term into the search input
    fireEvent.change(searchInput, { target: { value: "New Search Term" } });


  });
});
