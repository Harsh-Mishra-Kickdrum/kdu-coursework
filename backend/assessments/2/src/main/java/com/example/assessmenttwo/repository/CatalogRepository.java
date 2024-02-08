package com.example.assessmenttwo.repository;

import com.example.assessmenttwo.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Long> {

    /**
     * Modified update query
     * @param eventId
     * @param eventDate
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE catalog SET eventDate = :eventDate WHERE id = :eventId", nativeQuery = true)
    int updateCatalogEventDate(Long eventId, OffsetDateTime eventDate);


    /**
     * Modified update query
     * @param eventId
     * @param eventName
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE catalog SET eventName = :eventName WHERE id = :eventId", nativeQuery = true)
    int updateCatalogEventName(Long eventId, String eventName);



    @Modifying
    @Transactional
    @Query(value = "UPDATE catalog SET venue = :venue WHERE id = :eventId", nativeQuery = true)
    int updateCatalogVenue(Long eventId, String venueName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE catalog SET availableTickets = :availableTickets WHERE id = :eventId", nativeQuery = true)
    int updateCatalogAvailableTickets(Long eventId,int availableTickets);



    @Transactional
    void deleteById(Long eventId);



}