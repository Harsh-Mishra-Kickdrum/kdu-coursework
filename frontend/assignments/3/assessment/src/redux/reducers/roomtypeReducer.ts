export const roomTypeReducer = (state = "Single Room", action) => {
  switch (action.type) {
    case "SET_ROOM_TYPE":
      return action.payload;
    default:
      return state;
  }
};
