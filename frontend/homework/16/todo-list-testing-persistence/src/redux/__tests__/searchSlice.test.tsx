// Import the reducer and action creator from the search slice.
import searchReducer, { setSearchTerm } from "../reducers/searchSlice";

// Group tests related to the search slice functionality.
describe("searchSlice", () => {
  // Define the initial state expected before any actions have been dispatched.
  // This represents the default state of the search feature.
  const initialState = {
    searchTerm: "",
  };

  // Test case to verify that the reducer correctly returns the initial state
  // when no action is provided or when the application initializes.
  it("should return the initial state", () => {
    // The reducer is called with an undefined state and a dummy initialization action type.
    // It's expected to return the initial state, indicating it correctly handles initialization.
    expect(searchReducer(undefined, { type: "@@INIT" })).toEqual(initialState);
  });

  // Test case to verify that the reducer updates the state in response to the setSearchTerm action.
  it("should handle setSearchTerm", () => {
    // Define a sample search term to update the state with.
    const exampleSearchTerm = "example";
    // Define the expected state after the setSearchTerm action is dispatched.
    const expectedState = {
      searchTerm: exampleSearchTerm,
    };

    // Dispatch the setSearchTerm action with the example search term as payload,
    // and verify that the new state matches the expected state.
    // This confirms that the reducer correctly handles updating the search term.
    expect(
      searchReducer(initialState, setSearchTerm(exampleSearchTerm))
    ).toEqual(expectedState);
  });
});
