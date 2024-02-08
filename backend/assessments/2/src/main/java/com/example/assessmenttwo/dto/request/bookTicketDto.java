package com.example.assessmenttwo.dto.request;

import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Dto class to book the ticket from the request
 */

@Data
@NoArgsConstructor
public class bookTicketDto {

    private Long bookingId;
    private OffsetDateTime transactionDate;

    private Long userId;

    private Long eventId;

    private int noOfTicket;



}
