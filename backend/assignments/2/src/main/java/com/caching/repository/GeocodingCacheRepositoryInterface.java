package com.caching.repository;

import com.caching.model.GeocodeData;

import java.util.Optional;

/**
 * The GeocodingCacheRepositoryInterface defines the contract for caching operations
 * related to geocoding and reverse-geocoding. It outlines methods for saving, retrieving,
 * and deleting GeocodeData.
 */
public interface GeocodingCacheRepositoryInterface {

    /**
     * Saves the provided GeocodeData in the cache.
     *
     * @param data The GeocodeData to be saved in the cache.
     */
    void save(GeocodeData data);

    /**
     * Retrieves GeocodeData by address.
     *
     * @param address The address for which GeocodeData is to be retrieved.
     * @return An Optional containing the GeocodeData if found, otherwise an empty Optional.
     */
    Optional<GeocodeData> findByAddress(String address);

    /**
     * Retrieves GeocodeData by latitude and longitude.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return An Optional containing the GeocodeData if found, otherwise an empty Optional.
     */
    Optional<GeocodeData> findByLatLong(double latitude, double longitude);

    /**
     * Evicts the GeocodeData associated with the specified address from the cache.
     *
     * @param address The address whose associated GeocodeData is to be removed from the cache.
     */
    void deleteByAddress(String address);

    /**
     * Clears all entries from the cache.
     */
    void deleteAll();
}
