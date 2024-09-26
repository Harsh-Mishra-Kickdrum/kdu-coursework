package com.caching.service;

import com.caching.dto.GeocodeResponse;
import com.caching.dto.ReverseGeocodeResponse;

/**
 * The GeocodingService interface defines the contract for geocoding services.
 * It provides methods for obtaining geocode and reverse geocode information,
 * as well as for evicting all caches related to geocoding.
 */
public interface GeocodingService {

    /**
     * Retrieves geocoding information for a given address.
     *
     * @param address The address for which geocoding information is requested.
     * @return A GeocodeResponse containing the geocoding details for the provided address.
     */
    GeocodeResponse getGeocode(String address);

    /**
     * Retrieves reverse geocoding information based on latitude and longitude.
     *
     * @param latitude  The latitude coordinate for the location.
     * @param longitude The longitude coordinate for the location.
     * @return A ReverseGeocodeResponse containing the address information for the provided coordinates.
     */
    ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude);

    /**
     * Evicts all caches related to geocoding and reverse geocoding.
     * This is typically used to clear cached data and ensure fresh information is obtained.
     */
    void evictAllCaches();
}
