package com.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing the response of a geocoding request.
 * This class encapsulates the information returned from a geocoding operation,
 * including the original address, and its corresponding latitude and longitude.
 */
@Data
@AllArgsConstructor
public class GeocodeResponse {
    /**
     * The address that was geocoded. This is the input address provided in the geocoding request.
     */
    private String address;

    /**
     * The latitude component of the geographic coordinates corresponding to the geocoded address.
     */
    private Double latitude;

    /**
     * The longitude component of the geographic coordinates corresponding to the geocoded address.
     */
    private Double longitude;
}
