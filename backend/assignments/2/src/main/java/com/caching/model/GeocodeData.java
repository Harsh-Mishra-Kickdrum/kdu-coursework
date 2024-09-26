package com.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The GeocodeData class represents the data model for geocoding information.
 * It holds details such as address, latitude, longitude, and geocode.
 * This class is used to store and transfer geocoding and reverse geocoding data.
 */
@Data
@AllArgsConstructor
public class GeocodeData {
    private String address;
    private Double latitude;
    private Double longitude;
    private String geocode;

    /**
     * Constructor for creating GeocodeData with address and geocode.
     * This constructor is typically used for geocoding where an address is converted to its geocode.
     *
     * @param address The address as a String.
     * @param geocode The geocode as a String.
     */
    public GeocodeData(String address, String geocode) {
        this.address = address;
        this.geocode = geocode;
    }

    /**
     * Constructor for creating GeocodeData with address, latitude, and longitude.
     * This constructor is typically used for reverse geocoding where latitude and longitude are converted to an address.
     *
     * @param address  The address as a String.
     * @param latitude The latitude as a Double.
     * @param longitude The longitude as a Double.
     */
    public GeocodeData(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
