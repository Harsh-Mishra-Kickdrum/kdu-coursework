// TodoContext.tsx
import React, {
  createContext,
  useContext,
  useState,
  ReactNode,
  useEffect,
} from "react";
import { TodoItem } from "../../type";

interface TodoContextType {
  todos: TodoItem[];
  addTodo: (text: string) => void;
  deleteTodo: (id: string) => void;
  setSearchTerm: React.Dispatch<React.SetStateAction<string>>;
  filteredTodos: TodoItem[];
}

// Create a context with a default value
const TodoContext = createContext<TodoContextType | undefined>(undefined);

interface TodoProviderProps {
  children: ReactNode;
}

/**
 * TodoProvider component to manage todo items.
 *
 * @param {TodoProviderProps} children - The children components to be wrapped by the provider.
 * @return {JSX.Element} The provider component with todo context.
 */

export const TodoProvider: React.FC<TodoProviderProps> = ({ children }) => {
  const [todos, setTodos] = useState<TodoItem[]>([]);
  const [searchTerm, setSearchTerm] = useState("");

  // Load from localStorage
  useEffect(() => {
    const storedTodos = localStorage.getItem("todos");
    if (storedTodos) {
      setTodos(JSON.parse(storedTodos));
    }
  }, []);

  // Save to localStorage
  useEffect(() => {
    localStorage.setItem("todos", JSON.stringify(todos));
  }, [todos]);

  /**
   * A function that adds a new todo item to the list.
   *
   * @param {string} text - the text of the new todo item
   * @return {void}
   */

  const addTodo = (text: string) => {
    if (!text.trim()) return; // Ignore empty text
    const newTodo: TodoItem = {
      id: Math.random().toString(36).substring(2, 15),
      text,
    };
    setTodos([...todos, newTodo]);
  };

  /**
   * Deletes a todo with the specified ID from the todos list.
   *
   * @param {string} id - The ID of the todo to be deleted
   * @return {void}
   */

  const deleteTodo = (id: string) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const filteredTodos = todos.filter((todo) =>
    todo.text.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <TodoContext.Provider
      value={{ todos, addTodo, deleteTodo, setSearchTerm, filteredTodos }}
    >
      {children}
    </TodoContext.Provider>
  );
};


/**
 * Returns the todo context.
 *
 * @return {TodoContextType} The todo context
 */

export const useTodos = (): TodoContextType => {
  const context = useContext(TodoContext);
  if (context === undefined) {
    throw new Error("useTodos must be used within a TodoProvider");
  }
  return context;
};
