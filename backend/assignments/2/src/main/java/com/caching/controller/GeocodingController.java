package com.caching.controller;

import com.caching.dto.GeocodeResponse;
import com.caching.dto.ReverseGeocodeResponse;
import com.caching.service.GeocodingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for handling geocoding and reverse geocoding requests.
 * This controller provides endpoints for converting addresses to geographic coordinates and vice versa.
 */
@RestController
public class GeocodingController {

    private static final Logger logger = LoggerFactory.getLogger(GeocodingController.class);

    private GeocodingServiceImpl geocodingService;

    /**
     * Autowired constructor for dependency injection of GeocodingServiceImpl.
     *
     * @param geocodingService The service class handling geocoding operations.
     */
    @Autowired
    public GeocodingController(GeocodingServiceImpl geocodingService){
        this.geocodingService = geocodingService;
    }

    /**
     * Endpoint for performing geocoding - converting an address to geographic coordinates.
     * Access this endpoint via GET request at /geocoding with an address parameter.
     *
     * @param address The address to be geocoded.
     * @return GeocodeResponse containing latitude and longitude.
     */
    @GetMapping("/geocoding")
    public GeocodeResponse geocode(@RequestParam("address") String address) {
        logger.info("Request received for geocoding: address={}", address);
        return geocodingService.getGeocode(address);
    }

    /**
     * Endpoint for performing reverse geocoding - converting geographic coordinates to an address.
     * Access this endpoint via GET request at /reverse-geocoding with latitude and longitude parameters.
     *
     * @param latitude The latitude component of the geographic coordinates.
     * @param longitude The longitude component of the geographic coordinates.
     * @return ReverseGeocodeResponse containing the resolved address.
     */
    @GetMapping("/reverse-geocoding")
    public ReverseGeocodeResponse reverseGeocode(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        logger.info("Request received for reverse geocoding: latitude={}, longitude={}", latitude, longitude);
        return geocodingService.getReverseGeocode(latitude, longitude);
    }
}
