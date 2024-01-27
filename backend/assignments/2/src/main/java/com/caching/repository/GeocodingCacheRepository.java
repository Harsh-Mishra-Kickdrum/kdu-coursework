package com.caching.repository;

import com.caching.model.GeocodeData;
import com.caching.model.LatLongKey;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The GeocodingCacheRepository class provides methods for storing, retrieving, and managing
 * GeocodeData in caches. It abstracts the caching logic and provides a clear interface
 * for geocoding and reverse-geocoding data operations.
 */
@Repository
public class GeocodingCacheRepository implements GeocodingCacheRepositoryInterface {

    private final Cache geocodingCache;
    private final Cache reverseGeocodingCache;

    /**
     * Constructor for GeocodingCacheRepository.
     * Initializes the geocoding and reverse-geocoding caches.
     *
     * @param cacheManager The CacheManager to retrieve caches from.
     */
    public GeocodingCacheRepository(CacheManager cacheManager) {
        this.geocodingCache = cacheManager.getCache("geocoding");
        this.reverseGeocodingCache = cacheManager.getCache("reverse-geocoding");
    }

    /**
     * Saves the provided GeocodeData in both geocoding and reverse-geocoding caches.
     *
     * @param data The GeocodeData to be saved in the cache.
     */
    @Override
    public void save(GeocodeData data) {
        geocodingCache.put(data.getAddress(), data);
        reverseGeocodingCache.put(new LatLongKey(data.getLatitude(), data.getLongitude()), data);
    }

    /**
     * Retrieves GeocodeData by address from the geocoding cache.
     *
     * @param address The address for which GeocodeData is to be retrieved.
     * @return An Optional containing the GeocodeData if found, otherwise an empty Optional.
     */
    @Override
    public Optional<GeocodeData> findByAddress(String address) {
        return Optional.ofNullable(geocodingCache.get(address, GeocodeData.class));
    }

    /**
     * Retrieves GeocodeData by latitude and longitude from the reverse-geocoding cache.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return An Optional containing the GeocodeData if found, otherwise an empty Optional.
     */
    @Override
    public Optional<GeocodeData> findByLatLong(double latitude, double longitude) {
        return Optional.ofNullable(reverseGeocodingCache.get(new LatLongKey(latitude, longitude), GeocodeData.class));
    }

    /**
     * Evicts the GeocodeData associated with the specified address from the geocoding cache.
     *
     * @param address The address whose associated GeocodeData is to be removed from the cache.
     */
    @Override
    public void deleteByAddress(String address) {
        geocodingCache.evict(address);
    }

    /**
     * Clears all entries from both geocoding and reverse-geocoding caches.
     */
    @Override
    public void deleteAll() {
        geocodingCache.clear();
        reverseGeocodingCache.clear();
    }
}
