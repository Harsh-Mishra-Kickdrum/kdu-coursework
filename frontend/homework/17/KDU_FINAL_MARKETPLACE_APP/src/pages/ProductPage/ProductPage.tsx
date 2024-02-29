// ProductPage.tsx
import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { fetchData } from '../../utils/fetchData';
import { Product } from '../../types/apptypes';
import * as S from './ProductPage.style'; // Import the styles

const ProductPage: React.FC = () => {
  const { productId } = useParams<{ productId: string }>();
  const [product, setProduct] = React.useState<Product | null>(null);

  useEffect(() => {
    const fetchProduct = async () => {
      const data = await fetchData(`https://fakestoreapi.com/products/${productId}`);
      setProduct(data);
    };

    fetchProduct();
  }, [productId]);

  if (!product) {
    return <div>Loading...</div>;
  }

  return (
    <S.Section>
      <S.NavbarContainer>
        {/* Additional navbar content can be added here if needed */}
      </S.NavbarContainer>

      <S.Title>{product.title}</S.Title>
      <S.Container>
        <S.Image src={product.image} alt={product.title} />
        <S.Details>
          <S.Category>Model : {product.category}</S.Category>
          <S.Price>Price : ${product.price}</S.Price>
          <S.DescriptionHeading>Description:</S.DescriptionHeading>
          <S.Description>{product.description}</S.Description>
          <S.BackButton to="/">Back to Products</S.BackButton>
        </S.Details>
      </S.Container>
    </S.Section>
  );
};

export default ProductPage;
