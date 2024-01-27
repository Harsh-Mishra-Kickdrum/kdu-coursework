package com.caching.repository;

import com.caching.model.GeocodeData;
import com.caching.model.LatLongKey;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for caching geocoding and reverse geocoding data.
 */
@Repository
public class GeocodingCacheRepository implements GeocodingCacheRepositoryInterface {

    private final Cache geocodingCache;
    private final Cache reverseGeocodingCache;

    /**
     * Constructs a GeocodingCacheRepository with a given cache manager.
     *
     * @param cacheManager the cache manager to manage the geocoding caches
     * @throws IllegalStateException if geocoding or reverse geocoding cache is not configured
     */
    public GeocodingCacheRepository(CacheManager cacheManager) {
        this.geocodingCache = cacheManager.getCache("geocoding");
        this.reverseGeocodingCache = cacheManager.getCache("reverse-geocoding");

        if (geocodingCache == null || reverseGeocodingCache == null) {
            throw new IllegalStateException("Cache configuration error");
        }
    }

    /**
     * Saves geocode data in the cache.
     *
     * @param data the geocode data to be cached
     */
    @Override
    public void save(GeocodeData data) {
        geocodingCache.put(data.getAddress(), data);
        reverseGeocodingCache.put(new LatLongKey(data.getLatitude(), data.getLongitude()), data);
    }

    /**
     * Finds geocode data by address.
     *
     * @param address the address to search for
     * @return an Optional containing the found geocode data, or an empty Optional if not found
     */
    @Override
    public Optional<GeocodeData> findByAddress(String address) {
        return Optional.ofNullable(geocodingCache.get(address, GeocodeData.class));
    }

    /**
     * Finds geocode data by latitude and longitude.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     * @return an Optional containing the found geocode data, or an empty Optional if not found
     */
    @Override
    public Optional<GeocodeData> findByLatLong(double latitude, double longitude) {
        return Optional.ofNullable(reverseGeocodingCache.get(new LatLongKey(latitude, longitude), GeocodeData.class));
    }

    /**
     * Deletes geocode data by address from the cache.
     *
     * @param address the address of the geocode data to delete
     */
    @Override
    public void deleteByAddress(String address) {
        Optional<GeocodeData> data = findByAddress(address);
        geocodingCache.evict(address);
        data.ifPresent(d -> reverseGeocodingCache.evict(new LatLongKey(d.getLatitude(), d.getLongitude())));
    }

    /**
     * Deletes geocode data by latitude and longitude from the cache.
     *
     * @param latitude  the latitude of the location
     * @param longitude the longitude of the location
     */
    @Override
    public void deleteByLatLong(double latitude, double longitude) {
        Optional<GeocodeData> data = findByLatLong(latitude, longitude);
        reverseGeocodingCache.evict(new LatLongKey(latitude, longitude));
        data.ifPresent(d -> geocodingCache.evict(d.getAddress()));
    }

    /**
     * Deletes all geocode data from both caches.
     */
    @Override
    public void deleteAll() {
        geocodingCache.clear();
        reverseGeocodingCache.clear();
    }
}
