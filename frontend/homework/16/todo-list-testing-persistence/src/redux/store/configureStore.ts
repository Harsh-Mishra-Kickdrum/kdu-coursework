import { configureStore } from "@reduxjs/toolkit";
import storage from "redux-persist/lib/storage"; 
import { persistReducer, persistStore } from "redux-persist";
import rootReducer from "../reducers/rootReducer";


// Persist config
const persistConfig = {
  key: "root", // The key for the persist
  storage, // The storage to use (localStorage)
};

// Wrap rootReducer with persistReducer
const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer, // Use the persisted reducer

middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        // Ignore these action types
        ignoredActions: ['persist/PERSIST', 'persist/REHYDRATE', 'persist/REGISTER'],
        // Or ignore paths in the state
        // ignoredPaths: ['items.someNonSerializablePath'],
      },
    }),
});





// Create a persistor for the store
export const persistor = persistStore(store);

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
