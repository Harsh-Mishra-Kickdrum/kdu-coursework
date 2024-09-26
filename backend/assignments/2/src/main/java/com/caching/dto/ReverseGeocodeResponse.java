package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing the response of a reverse geocoding request.
 * This class encapsulates the information returned from a reverse geocoding operation,
 * including the latitude and longitude provided in the request, and the corresponding human-readable address.
 */
@Data
@AllArgsConstructor
public class ReverseGeocodeResponse {
    /**
     * The latitude component of the geographic coordinates provided in the reverse geocoding request.
     */
    private Double latitude;

    /**
     * The longitude component of the geographic coordinates provided in the reverse geocoding request.
     */
    private Double longitude;

    /**
     * The human-readable address corresponding to the provided latitude and longitude coordinates.
     * This is the result of the reverse geocoding operation.
     */
    private String address;
}
