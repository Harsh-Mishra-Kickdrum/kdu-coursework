import { useDispatch } from 'react-redux';
import type { AppDispatch } from './index'; 

/**
 * Returns the application dispatch function.
 *
 * @return {AppDispatch} The application dispatch function
 */

export const useAppDispatch = (): AppDispatch => useDispatch<AppDispatch>();
