import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useAppDispatch } from "../../store/hooks";
import ProductCard from "../../components/ProductCard/ProductCard";
import { getProducts } from "../../features/products/productsSlice";
import { RootState } from "../../store/rootReducer";
import LoaderComponent from "../../components/Loader/Loader";
import Snackbar from "../../components/Snackbar/Snackbar";
import * as S from "./homepage.style";

const HomePage: React.FC = () => {
  const dispatch = useAppDispatch();
  const { items, loading, snackbar } = useSelector(
    (state: RootState) => state.products
  );

  // Local state to manage Snackbar visibility
  const [showSnackbar, setShowSnackbar] = useState(false);

  useEffect(() => {
    dispatch(getProducts());
  }, [dispatch]);

  // Effect to control Snackbar visibility
  useEffect(() => {
    if (snackbar.visible) {
      setShowSnackbar(true);
      const timer = setTimeout(() => {
        setShowSnackbar(false);
      }, 3000); // Dismiss the Snackbar after 3 seconds

      return () => clearTimeout(timer);
    }
  }, [snackbar.visible]);

  // Check if fetch operation failed and snackbar is for an error
  const fetchFailed =
    snackbar.visible && snackbar.type === "error" && !showSnackbar;

  return (
    <S.Section>
      {loading ? (
        <LoaderComponent />
      ) : (
        <>
          {showSnackbar && (
            <Snackbar
              message={snackbar.message}
              type={snackbar.type}
              onClose={() => setShowSnackbar(false)}
            />
          )}
          {
            !fetchFailed ? (
              <>
                <S.Title>
                  <S.pi>KDU</S.pi> MARKETPLACE
                </S.Title>
                <S.ProductsContainer>
                  {items.length > 0 ? (
                    items.map((product) => (
                      <ProductCard key={product.id} product={product} />
                    ))
                  ) : (
                    <S.EmptyContainer>No products found</S.EmptyContainer>
                  )}
                </S.ProductsContainer>
              </>
            ) : null /* Render nothing here if fetch failed */
          }
        </>
      )}
    </S.Section>
  );
};

export default HomePage;
