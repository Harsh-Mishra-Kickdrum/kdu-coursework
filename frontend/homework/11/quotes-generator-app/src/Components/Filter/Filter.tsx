// src/components/Filter/Filter.tsx
import React from "react";
import "./Filter.scss";

interface FilterProps {
  filters: string[];
  removeFilter: (filter: string) => void;
}

/**
 * Filter component that renders a list of filters and allows removing them.
 *
 * @param {FilterProps} filters - the list of filters to display
 * @param {Function} removeFilter - function to remove a filter
 * @return {ReactNode} the rendered filter container
 */

const Filter: React.FC<FilterProps> = ({ filters, removeFilter }) => {
  return (
    <div className="filterContainer">
      {filters.map((filter, index) => (
        <div key={index} className="filterTag">
          {filter}
          <button className="filter-delete-btn" onClick={() => removeFilter(filter)}> x </button>
        </div>
      ))}
    </div>
  );
};

export default Filter;
