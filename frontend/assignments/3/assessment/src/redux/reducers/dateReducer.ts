import { DateType } from "../../types/date.types";

export const dateReducer = (state = new Date(), action:DateType) => {
  switch (action.type) {
    case "SET_DATE":
      return action.payload;
    default:
      return state;
  }
};
