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

import java.util.Optional;

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

    @Override
    public GeocodeResponse getGeocode(String address) {
        logger.info("Fetching geocode for address: {}", address);
        Optional<GeocodeData> cacheData = geocodingCacheRepository.findByAddress(address);

        if (cacheData.isPresent()) {
            GeocodeData data = cacheData.get();
            return new GeocodeResponse(data.getAddress(), data.getLatitude(), data.getLongitude());
        } else {
            GeocodeResponse response = externalAPIUtility.getGeocode(address);
            geocodingCacheRepository.save(new GeocodeData(address, response.getLatitude(), response.getLongitude(), response.getAddress()));
            return response;
        }
    }

    @Override
    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        logger.info("Fetching reverse geocode for latitude: {}, longitude: {}", latitude, longitude);
        Optional<GeocodeData> cacheData = geocodingCacheRepository.findByLatLong(latitude, longitude);

        if (cacheData.isPresent()) {
            GeocodeData data = cacheData.get();
            return new ReverseGeocodeResponse(data.getLatitude(), data.getLongitude(), data.getAddress());
        } else {
            ReverseGeocodeResponse response = externalAPIUtility.getReverseGeocode(latitude, longitude);
            geocodingCacheRepository.save(new GeocodeData(response.getAddress(), latitude, longitude, response.getAddress()));
            return response;
        }
    }

    @Override
    @CacheEvict(value = {"geocoding", "reverse-geocoding"}, allEntries = true)
    public void evictAllCaches() {
        logger.info("Evicting all caches");
    }
}
