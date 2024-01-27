package com.caching.service;

import com.caching.dto.GeocodeResponse;
import com.caching.dto.ReverseGeocodeResponse;
import com.caching.model.GeocodeData;
import com.caching.repository.GeocodingCacheRepository;
import com.caching.utility.ExternalAPIUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * The GeocodingServiceImpl class implements the GeocodingService interface.
 * It provides concrete methods for obtaining geocode and reverse geocode information,
 * either from a cache or by querying an external API, and for evicting all related caches.
 */

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private static final Logger logger = LoggerFactory.getLogger(GeocodingServiceImpl.class);
    private GeocodingCacheRepository geocodingCacheRepository;
    private ExternalAPIUtility externalAPIUtility;
    @Autowired
    public GeocodingServiceImpl(GeocodingCacheRepository geocodingCacheRepository,ExternalAPIUtility externalAPIUtility){
        this.geocodingCacheRepository =  geocodingCacheRepository;
        this.externalAPIUtility =externalAPIUtility;
    }

    /**
     * Retrieves geocoding information for a given address, either from the cache or by querying an external API.
     *
     * @param address The address for which geocoding information is requested.
     * @return A GeocodeResponse containing the geocoding details for the provided address.
     */

    @Override
    public GeocodeResponse getGeocode(String address) {
        logger.info("Fetching geocode for address: {}", address);
        return geocodingCacheRepository.findByAddress(address)
                .map(data -> new GeocodeResponse(data.getAddress(), data.getLatitude(), data.getLongitude()))
                .orElseGet(() -> {
                    GeocodeResponse response = externalAPIUtility.getGeocode(address);
                    geocodingCacheRepository.save(new GeocodeData(address, response.getLatitude(), response.getLongitude(), response.getAddress()));
                    return response;
                });
    }

    /**
     * Retrieves reverse geocoding information based on latitude and longitude, either from the cache or by querying an external API.
     *
     * @param latitude  The latitude coordinate for the location.
     * @param longitude The longitude coordinate for the location.
     * @return A ReverseGeocodeResponse containing the address information for the provided coordinates.
     */
    @Override
    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        logger.info("Fetching reverse geocode for latitude: {}, longitude: {}", latitude, longitude);
        return geocodingCacheRepository.findByLatLong(latitude, longitude)
                .map(data -> new ReverseGeocodeResponse(data.getLatitude(), data.getLongitude(), data.getAddress()))
                .orElseGet(() -> {
                    ReverseGeocodeResponse response = externalAPIUtility.getReverseGeocode(latitude, longitude);
                    geocodingCacheRepository.save(new GeocodeData(response.getAddress(), latitude, longitude, response.getAddress()));
                    return response;
                });
    }

    /**
     * Evicts all caches related to geocoding and reverse geocoding.
     * This method is used to clear cached data and ensure fresh information is obtained.
     */
    @Override
    @CacheEvict(value = {"geocoding", "reverse-geocoding"}, allEntries = true)
    public void evictAllCaches() {
        logger.info("Evicting all caches");
    }
}
