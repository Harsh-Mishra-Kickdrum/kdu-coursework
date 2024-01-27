package com.caching.utility;

import com.caching.dto.GeocodeResponse;
import com.caching.dto.ReverseGeocodeResponse;
import com.caching.exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for interacting with external geocoding APIs.
 * This class provides methods to perform geocoding and reverse geocoding operations.
 */
@Component
public class ExternalAPIUtility {

    private final Logger logger = LoggerFactory.getLogger(ExternalAPIUtility.class);

    @Value("${positionstack.api.key}")
    private String positionStackApiKey;

    /**
     * Retrieves geocode information for a given address.
     *
     * @param address the address to be geocoded.
     * @return GeocodeResponse containing the latitude, longitude, and formatted address.
     * @throws CustomException if an error occurs during the geocoding process.
     */
    public GeocodeResponse getGeocode(String address) {
        try {
            String encodedAddress = java.net.URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            URL url = new URL("http://api.positionstack.com/v1/forward?access_key=" + positionStackApiKey + "&query=" + encodedAddress);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode firstResult = root.path("data").path(0);
            double latitude = firstResult.path("latitude").asDouble();
            double longitude = firstResult.path("longitude").asDouble();
            String formattedAddress = firstResult.path("label").asText();

            return new GeocodeResponse(formattedAddress, latitude, longitude);
        } catch (IOException e) {
            logger.error("Error occurred during geocoding", e);
            throw new CustomException("Geocoding failed: " + e.getMessage());
        }
    }

    /**
     * Retrieves the address for given geographic coordinates.
     *
     * @param latitude the latitude of the location to reverse geocode.
     * @param longitude the longitude of the location to reverse geocode.
     * @return ReverseGeocodeResponse containing the latitude, longitude, and human-readable address.
     * @throws CustomException if an error occurs during the reverse geocoding process.
     */
    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        try {
            URL url = new URL("http://api.positionstack.com/v1/reverse?access_key=" + positionStackApiKey + "&query=" + latitude + "," + longitude);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode firstResult = root.path("data").path(0);
            String formattedAddress = firstResult.path("label").asText();

            return new ReverseGeocodeResponse(latitude, longitude, formattedAddress);
        } catch (IOException e) {
            logger.error("Error occurred during reverse geocoding", e);
            throw new CustomException("Reverse Geocoding failed: " + e.getMessage());
        }
    }

    /**
     * Sends an HTTP GET request to the specified URL and returns the response as a string.
     *
     * @param url the URL to which the GET request is sent.
     * @return the response from the server as a String.
     * @throws IOException if an I/O error occurs.
     */
    private String sendRequest(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } finally {
            con.disconnect();
        }
    }
}
