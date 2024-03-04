// src/components/Loader/LoaderComponent.tsx
import React from "react";
import { Loader } from "./Loader.styles";

interface LoaderComponentProps {
  loading: boolean;
}

const LoaderComponent: React.FC<LoaderComponentProps> = ({ loading }) => {
  return loading ? <Loader /> : null;
};

export default LoaderComponent;
