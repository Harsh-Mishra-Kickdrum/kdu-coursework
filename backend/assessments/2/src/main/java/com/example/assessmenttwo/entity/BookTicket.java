package com.example.assessmenttwo.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * Entity class to book ticket
 */

@Entity
@Table(name = "booked_ticket")
@Data
@NoArgsConstructor // JPA entities should have a no-argument constructor for framework use.
public class BookTicket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "transaction_date",nullable = false)
    private OffsetDateTime transactionDate;

    @Column(name ="user_id",nullable =false)
    private Long userId;

    @Column(name ="event_id",nullable =false)
    private Long eventId;

    /**
     * Many-to-One relationship to the user entity.
     * one user can have multiple bookings
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * Many-to-One relationship to the Catalog entity.
     * There can be many events booked
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "eventId")
    private Catalog events;

    @Column(name = " total_amount")
    private double totalAmount;


}
