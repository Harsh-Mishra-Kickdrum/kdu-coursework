
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { fetchData } from "../../utils/fetchData";
import { Product } from "../../types/apptypes"; 
interface ProductsState {
  items: Product[];
  loading: boolean;
  error: string | null;
  snackbar: {
    visible: boolean;
    message: string;
    type: "success" | "error";
  };
}

const initialState: ProductsState = {
  items: [],
  loading: false,
  error: null,
  snackbar: {
    visible: false,
    message: "",
    type: "success",
  },
};

export const getProducts = createAsyncThunk<Product[]>(
  "products/getProducts",
  async (_, { rejectWithValue }) => {
    try {
      const data = await fetchData("https://fakestoreapi.com/products");
      return data;
    } catch (error) {
      if (error instanceof Error) {
        return rejectWithValue(error.message);
      }
      return rejectWithValue("An unknown error occurred");
    }
  }
);

const productsSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    /**
     * Updates the snackbar visibility, message, and type in the state.
     *
     * @param {type} state - the current state object
     * @param {type} action - the action object containing payload
     * @return {type} undefined
     */

    showSnackbar: (state, action) => {
      state.snackbar.visible = true;
      state.snackbar.message = action.payload.message;
      state.snackbar.type = action.payload.type;
    },
    /**
     * Hides the snackbar and resets its message and type.
     *
     * @param {object} state - the state object
     * @return {void}
     */
    hideSnackbar: (state) => {
      state.snackbar.visible = false;
      state.snackbar.message = "";
      state.snackbar.type = "success";
    },
  },
  /**
   * Defines extra reducers for handling different action cases.
   *
   * @param {object} builder - The action builder object.
   * @return {void}
   */
  extraReducers: (builder) => {
    builder
      .addCase(getProducts.pending, (state) => {
        state.loading = true;
      })
      .addCase(getProducts.fulfilled, (state, action) => {
        state.items = action.payload;
        state.loading = false;
        state.snackbar = {
          visible: true,
          message: "Products fetched successfully!",
          type: "success",
        };
      })
      .addCase(getProducts.rejected, (state, action) => {
        state.error = action.payload as string;
        state.loading = false;
        state.snackbar = {
          visible: true,
          message: "Failed to fetch products",
          type: "error",
        };
      });
  },
});

export const { showSnackbar, hideSnackbar } = productsSlice.actions;

export default productsSlice.reducer;
