package com.example.assessmenttwo.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * A Dto class for manipulation with the events in the Catalog Table
 */

@Data
@NoArgsConstructor
public class EventDto {
    private Long eventId;
    private String eventName;
    private OffsetDateTime eventDate;
    private String venue;
    private int availableTickets;
}
