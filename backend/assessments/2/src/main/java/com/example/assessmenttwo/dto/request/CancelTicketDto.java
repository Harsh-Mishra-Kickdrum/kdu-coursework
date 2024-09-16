package com.example.assessmenttwo.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Dto class to cancel the booking from the request
 */

@Data
@NoArgsConstructor
public class CancelTicketDto {
    private Long bookingId;
}
