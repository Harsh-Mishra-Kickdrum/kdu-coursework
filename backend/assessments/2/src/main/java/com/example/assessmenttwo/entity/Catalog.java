package com.example.assessmenttwo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Entity class which shows the all events with the available no of tickets and venue and date.
 */


@Entity
@Table(name = "catalog")
@Data
@NoArgsConstructor // JPA entities should have a no-argument constructor for framework use.
public class Catalog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long eventId;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "event_date",nullable = false)
    private OffsetDateTime eventDate;

    @Column(name = "venue",nullable = false)
    private String venue;

    @Column(name = "available_tickets")
    private int availableTickets;


}
