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
 */
@Component
public class ExternalAPIUtility {

    private final Logger logger = LoggerFactory.getLogger(ExternalAPIUtility.class);

    private static final String ERROR = "Error";
    @Value("${positionstack.api.key}")
    private String positionStackApiKey;

    /**
     * Retrieves geocode information for a given address using the PositionStack API.
     *
     * @param address The address to geocode.
     * @return A GeocodeResponse object containing the geocode information.
     * @throws CustomException if there is an error during the API call.
     */
    public GeocodeResponse getGeocode(String address) {
        try {
            String encodedAddress = java.net.URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            URL url = new URL("http://api.positionstack.com/v1/forward?access_key=" + positionStackApiKey + "&query=" + encodedAddress);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            if (root.has(ERROR)) {
                throw new CustomException("API Error: " + root.path(ERROR).toString());
            }
            JsonNode firstResult = root.path("data").path(0);
            if (firstResult.isMissingNode()) {
                throw new CustomException("No results found");
            }
            double latitude = firstResult.path("latitude").asDouble();
            double longitude = firstResult.path("longitude").asDouble();
            String formattedAddress = firstResult.path("label").asText();

            return new GeocodeResponse(formattedAddress, latitude, longitude);
        } catch (IOException e) {
            logger.error("Error occurred during geocoding:");
            throw new CustomException("Geocoding failed: " + e.getMessage());
        }
    }

    /**
     * Retrieves reverse geocode information for given latitude and longitude using the PositionStack API.
     *
     * @param latitude  The latitude for the reverse geocode.
     * @param longitude The longitude for the reverse geocode.
     * @return A ReverseGeocodeResponse object containing the reverse geocode information.
     * @throws CustomException if there is an error during the API call.
     */
    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        try {
            URL url = new URL("http://api.positionstack.com/v1/reverse?access_key=" + positionStackApiKey + "&query=" + latitude + "," + longitude);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            if (root.has("error")) {
                throw new CustomException("API Error: " + root.path("error").toString());
            }
            JsonNode firstResult = root.path("data").path(0);
            if (firstResult.isMissingNode()) {
                throw new CustomException("No results found");
            }
            String formattedAddress = firstResult.path("label").asText();

            return new ReverseGeocodeResponse(latitude, longitude, formattedAddress);
        } catch (IOException e) {
            logger.error("Error occurred during reverse geocoding:");
            throw new CustomException("Reverse Geocoding failed: " + e.getMessage());
        }
    }

    /**
     * Sends an HTTP GET request to the specified URL and returns the response.
     *
     * @param url The URL to send the request to.
     * @return A String containing the response from the URL.
     * @throws IOException if there is an error in sending the request.
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
