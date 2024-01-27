package com.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * The LatLongKey class represents a unique key for latitude and longitude coordinates.
 * This class is designed to be used as a key in caching mechanisms where the combination of latitude
 * and longitude is required to uniquely identify a geolocation.
 */
@Data
@AllArgsConstructor
public class LatLongKey {
        private final double latitude;
        private final double longitude;

        /**
         * Factory method to create a new LatLongKey instance.
         * This method provides a clean way to create instances of LatLongKey.
         *
         * @param latitude  The latitude of the geolocation.
         * @param longitude The longitude of the geolocation.
         * @return A new instance of LatLongKey with the given latitude and longitude.
         */
        public static LatLongKey createKey(double latitude, double longitude) {
                return new LatLongKey(latitude, longitude);
        }

        /**
         * Overridden equals method for comparing two LatLongKey objects.
         * This method is essential for correctly identifying unique keys in caches or collections.
         *
         * @param o The object to compare with this LatLongKey.
         * @return True if both objects are LatLongKey with same latitude and longitude, otherwise false.
         */
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LatLongKey)) return false;
                LatLongKey that = (LatLongKey) o;
                return Double.compare(that.latitude, latitude) == 0 &&
                        Double.compare(that.longitude, longitude) == 0;
        }

        /**
         * Overridden hashCode method to generate a hash code for LatLongKey.
         * This method ensures that LatLongKey objects that are equal will have the same hash code.
         *
         * @return A hash code value for the LatLongKey.
         */
        @Override
        public int hashCode() {
                return Objects.hash(latitude, longitude);
        }
}
