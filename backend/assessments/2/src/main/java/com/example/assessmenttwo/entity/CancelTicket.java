package com.example.assessmenttwo.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class to cancel the booking
 */

@Entity
@Table(name = "cancled_ticket")
@Data
@NoArgsConstructor // JPA entities should have a no-argument constructor for framework use.
public class CancelTicket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long cancellingId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cancelling_id", referencedColumnName = "bookingId")
    private BookTicket bookedTicket;

    @Column(name = "booking_id")
    private Long bookingId;


}
