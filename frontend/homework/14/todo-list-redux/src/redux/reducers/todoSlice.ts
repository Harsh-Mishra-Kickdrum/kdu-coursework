// redux/reducers/todoSlice.ts

import { createSlice, PayloadAction } from "@reduxjs/toolkit";

export interface TodoItem {
  id: string;
  text: string;
  completed: boolean;
}

interface TodosState {
  todos: TodoItem[];
}

const initialState: TodosState = {
  todos: [],
};

export const todoSlice = createSlice({
  name: "todos",
  initialState,
  reducers: {
    /**
     * Adds a new todo item to the state.
     *
     * @param {type} state - The current state
     * @param {type} action - The payload action containing the new todo item text
     * @return {void}
     */

    addTodo: (state, action: PayloadAction<string>) => {
      const newTodo: TodoItem = {
        id: Math.random().toString(36).substring(2, 15),
        text: action.payload,
        completed: false,
      };
      state.todos.push(newTodo);
    },

    /**
     * A function to toggle a todo's completion status.
     *
     * @param {type} state - the current state of the todos
     * @param {PayloadAction<string>} action - the action payload containing the todo id
     */

    toggleTodo: (state, action: PayloadAction<string>) => {
      const todo = state.todos.find((todo) => todo.id === action.payload);
      if (todo) {
        todo.completed = !todo.completed;
      }
    },

    /**
     * Deletes a todo from the state based on the action payload.
     *
     * @param {type} state - The current state
     * @param {PayloadAction<string>} action - The payload action containing the todo id to be deleted
     */

    deleteTodo: (state, action: PayloadAction<string>) => {
      state.todos = state.todos.filter((todo) => todo.id !== action.payload);
    },

    /**
     * Clear completed todos from the state.
     *
     * @param {object} state - The state object containing todos
     * @return {void}
     */

    clearCompletedTodos: (state) => {
      state.todos = state.todos.filter((todo) => !todo.completed);
    },

    /**
     * Edit a todo item in the state.
     *
     * @param {type} state - The current state
     * @param {type} action - The payload action containing the id and new text
     * @return {type} void
     */
    
    editTodo: (state, action: PayloadAction<{ id: string; text: string }>) => {
      const todo = state.todos.find((todo) => todo.id === action.payload.id);
      if (todo) {
        todo.text = action.payload.text;
      }
    },
  },
});

export const {
  addTodo,
  toggleTodo,
  deleteTodo,
  clearCompletedTodos,
  editTodo,
} = todoSlice.actions;

export default todoSlice.reducer;
