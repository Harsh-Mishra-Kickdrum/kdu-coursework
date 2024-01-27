package com.caching.repository;

import com.caching.model.GeocodeData;
import java.util.Optional;

/**
 * Interface for a repository handling the caching of geocoding data.
 */
public interface GeocodingCacheRepositoryInterface {

    /**
     * Saves geocode data in the cache.
     *
     * @param data The geocode data to be saved in the cache.
     */
    void save(GeocodeData data);

    /**
     * Finds geocode data based on an address.
     *
     * @param address The address to find geocode data for.
     * @return An Optional containing the found geocode data, or an empty Optional if not found.
     */
    Optional<GeocodeData> findByAddress(String address);

    /**
     * Finds geocode data based on latitude and longitude.
     *
     * @param latitude The latitude of the location to find geocode data for.
     * @param longitude The longitude of the location to find geocode data for.
     * @return An Optional containing the found geocode data, or an empty Optional if not found.
     */
    Optional<GeocodeData> findByLatLong(double latitude, double longitude);

    /**
     * Deletes geocode data from the cache by address.
     *
     * @param address The address of the geocode data to delete.
     */
    void deleteByAddress(String address);

    /**
     * Deletes geocode data from the cache by latitude and longitude.
     *
     * @param latitude The latitude of the location of the geocode data to delete.
     * @param longitude The longitude of the location of the geocode data to delete.
     */
    void deleteByLatLong(double latitude, double longitude);

    /**
     * Deletes all geocode data from the cache.
     */
    void deleteAll();
}
