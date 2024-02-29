import { useSelector } from "react-redux";
import { Loader } from "./Loader.style";

import { RootState } from "../../store/rootReducer"; 

/**
 * Function component for rendering a Loader based on the loading state.
 *
 * @return {JSX.Element} The Loader component or null
 */
const LoaderComponent = () => {

  const loading = useSelector((state: RootState) => state.products.loading);

  return loading ? <Loader /> : null;
};

export default LoaderComponent;
