package com.caching.service;

import com.caching.dto.GeocodeResponse;
import com.caching.dto.ReverseGeocodeResponse;
import com.caching.exception.CustomException;
import com.caching.model.GeocodeData;
import com.caching.repository.GeocodingCacheRepository;
import com.caching.utility.ExternalAPIUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service implementation for geocoding operations.
 */
@Service
public class GeocodingServiceImpl implements GeocodingService {

    private static final Logger logger = LoggerFactory.getLogger(GeocodingServiceImpl.class);
    private GeocodingCacheRepository geocodingCacheRepository;
    private ExternalAPIUtility externalAPIUtility;

    @Autowired
    public GeocodingServiceImpl(GeocodingCacheRepository geocodingCacheRepository, ExternalAPIUtility externalAPIUtility) {
        this.geocodingCacheRepository = geocodingCacheRepository;
        this.externalAPIUtility = externalAPIUtility;
    }

    /**
     * Retrieves geocode information for a given address.
     *
     * @param address The address to retrieve geocode information for.
     * @return A GeocodeResponse object containing the geocode information.
     */
    @Override
    public GeocodeResponse getGeocode(String address) {
        logger.info("Fetching geocode for address: {}", address);
        if ("Goa".equalsIgnoreCase(address)) {
            return fetchAndSaveGeocodeData(address);
        }
        return getCachedGeocodeData(address);
    }

    /**
     * Caches and retrieves geocode information for a given address.
     *
     * @param address The address to retrieve geocode information for.
     * @return A GeocodeResponse object containing the geocode information.
     */
    @Cacheable(value = "geocoding", key = "#address")
    private GeocodeResponse getCachedGeocodeData(String address) {
        return fetchAndSaveGeocodeData(address);
    }

    /**
     * Fetches and saves geocode information from an external API.
     *
     * @param address The address to retrieve geocode information for.
     * @return A GeocodeResponse object containing the geocode information.
     */
    private GeocodeResponse fetchAndSaveGeocodeData(String address) {
        try {
            return geocodingCacheRepository.findByAddress(address)
                    .map(data -> new GeocodeResponse(data.getAddress(), data.getLatitude(), data.getLongitude()))
                    .orElseGet(() -> {
                        GeocodeResponse response = externalAPIUtility.getGeocode(address);
                        geocodingCacheRepository.save(new GeocodeData(address, response.getLatitude(), response.getLongitude(), response.getAddress()));
                        return response;
                    });
        } catch (Exception e) {
            logger.error("Error fetching geocode for address: {}", address, e);
            throw new CustomException("Error fetching geocode for address: " + address);
        }
    }

    /**
     * Retrieves reverse geocode information for given latitude and longitude.
     *
     * @param latitude  The latitude for which to retrieve reverse geocode information.
     * @param longitude The longitude for which to retrieve reverse geocode information.
     * @return A ReverseGeocodeResponse object containing the reverse geocode information.
     */
    @Override
    @Cacheable(value = "reverse-geocoding", key = "{#latitude, #longitude}")
    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        logger.info("Fetching reverse geocode for latitude: {}, longitude: {}", latitude, longitude);
        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            throw new CustomException("Invalid latitude or longitude values");
        }
        return fetchAndSaveReverseGeocodeData(latitude, longitude);
    }

    /**
     * Fetches and saves reverse geocode information from an external API.
     *
     * @param latitude  The latitude for which to retrieve reverse geocode information.
     * @param longitude The longitude for which to retrieve reverse geocode information.
     * @return A ReverseGeocodeResponse object containing the reverse geocode information.
     */
    private ReverseGeocodeResponse fetchAndSaveReverseGeocodeData(double latitude, double longitude) {
        try {
            return geocodingCacheRepository.findByLatLong(latitude, longitude)
                    .map(data -> new ReverseGeocodeResponse(data.getLatitude(), data.getLongitude(), data.getAddress()))
                    .orElseGet(() -> {
                        ReverseGeocodeResponse response = externalAPIUtility.getReverseGeocode(latitude, longitude);
                        geocodingCacheRepository.save(new GeocodeData(response.getAddress(), latitude, longitude, response.getAddress()));
                        return response;
                    });
        } catch (Exception e) {
            logger.error("Error fetching reverse geocode for latitude: {}, longitude: {}", latitude, longitude, e);
            throw new CustomException("Error fetching reverse geocode for latitude: " + latitude + ", longitude: " + longitude);
        }
    }

    /**
     * Evicts all entries from the geocoding and reverse-geocoding caches.
     */
    @Override
    @CacheEvict(value = {"geocoding", "reverse-geocoding"}, allEntries = true)
    public void evictAllCaches() {
        logger.info("Evicting all caches");
    }
}
