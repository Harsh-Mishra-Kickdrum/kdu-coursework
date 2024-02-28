// HomePage.tsx
import React, { useContext, useEffect, useState } from 'react';
import AppContext from '../../context/AppContext';
import ProductCard from '../../components/ProductCard/ProductCard';
import { fetchData } from '../../utils/fetchData';
import { Product } from '../../types/apptypes';
import * as S from './homepage.style'; // Import the styles

const HomePage: React.FC = () => {
  const { items, setItems, filter, sort, searchQuery, categories } = useContext(AppContext);
  const [filteredItems, setFilteredItems] = useState<Product[]>([]);

  useEffect(() => {
    const fetchProducts = async () => {
      const data = await fetchData('https://fakestoreapi.com/products');
      setItems(data);
    };

    fetchProducts();
  }, [setItems]);

  useEffect(() => {
    const filterAndSortItems = () => {
      let filteredBySearch = items;

      if (searchQuery !== '') {
        filteredBySearch = items.filter((item) =>
          item.title.toLowerCase().includes(searchQuery.toLowerCase())
        );
      }

      const normalizedFilter = filter.toLowerCase();
      const filteredByCategory =
        normalizedFilter === 'all'
          ? filteredBySearch
          : filteredBySearch.filter((item) => item.category.toLowerCase() === normalizedFilter);

      const sortedItems = sortItems(filteredByCategory, sort);

      setFilteredItems(sortedItems);
    };

    filterAndSortItems();
  }, [items, filter, sort, searchQuery]);

  const sortItems = (items: Product[], sortOrder: string): Product[] => {
    if (sortOrder === 'asc') {
      return [...items].sort((a, b) => a.price - b.price);
    } else if (sortOrder === 'desc') {
      return [...items].sort((a, b) => b.price - a.price);
    }
    return items;
  };

  return (
    <S.Section>
      <S.Title> <S.pi>KDU</S.pi> MARKETPLACE</S.Title>
      <S.ProductsContainer>
        {filteredItems.length > 0 ? (
          filteredItems.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))
        ) : (
          <S.EmptyContainer>No products</S.EmptyContainer>
        )}
      </S.ProductsContainer>
    </S.Section>
  );
};

export default HomePage;
