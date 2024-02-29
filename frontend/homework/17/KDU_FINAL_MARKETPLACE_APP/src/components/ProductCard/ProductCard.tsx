// Importing necessary modules and components from React, React Router, and styled-components
import React from "react";
import { Link } from "react-router-dom";
import { Product } from "../../types/apptypes"; // Importing the Product type definition
import * as S from "./ProductCard.style"; // Import the styles specifically for ProductCard

// TypeScript interface to type-check the props received by the ProductCard component
interface ProductCardProps {
  product: Product;
}

/**
 * The ProductCard component displays a single product's basic information.
 * It shows the product's image, title, and price, and provides a link to the product's detailed view.
 *
 * @param {ProductCardProps} props - Component props containing a product object.
 * @returns {JSX.Element} The ProductCard component.
 */
const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  return (
    <S.Card>
      {/* Product image */}
      <S.ProductImage src={product.image} alt={product.title} />
      {/* Product title */}
      <S.ProductTitle>{product.title}</S.ProductTitle>
      {/* Product price */}
      <S.ProductPrice>${product.price}</S.ProductPrice>
      {/* Link to product details page */}
      <S.DetailsLink to={`/products/${product.id}`}>Details</S.DetailsLink>
    </S.Card>
  );
};

// Exporting the ProductCard component to be used in other parts of the application
export default ProductCard;
