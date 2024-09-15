package com.example.springjpademo.repository;

import com.example.springjpademo.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Repository interface for {@link Shift} entities.
 * Extends JpaRepository to provide CRUD operations and more on Shift entities.
 */
@Repository
public interface ShiftRepository extends JpaRepository<Shift, UUID> {

    /**
     * Custom query to find shifts within a given start and end date, ordered by name.
     *
     * @param startDate The start date of the shift period.
     * @param endDate The end date of the shift period.
     * @return A list of {@link Shift} objects within the specified date range.
     */
    @Query("SELECT s FROM Shift s WHERE s.startDate = :startDate AND s.endDate = :endDate ORDER BY s.name")
    List<Shift> findTopThree(LocalDate startDate, LocalDate endDate);
}
