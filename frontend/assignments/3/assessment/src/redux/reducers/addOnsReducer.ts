export const addOnsReducer = (state = [], action) => {
  switch (action.type) {
    case "ADD_ON":
      return [...state, action.payload];
    case "REMOVE_ON":
      return state.filter((addOn) => addOn !== action.payload);
    default:
      return state;
  }
};
