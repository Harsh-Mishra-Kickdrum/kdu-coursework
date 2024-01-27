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

@Component
public class ExternalAPIUtility {

    private final Logger logger = LoggerFactory.getLogger(ExternalAPIUtility.class);
    private String geoapifyApiKey;

    @Value("${geoapify.api.key}")
    public void setGeoapifyApiKey(String apiKey) {
        this.geoapifyApiKey = apiKey;
    }

    public GeocodeResponse getGeocode(String address) {
        try {
            String encodedAddress = java.net.URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            URL url = new URL("https://api.geoapify.com/v1/geocode/search?text=" + encodedAddress + "&apiKey=" + geoapifyApiKey);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            if (root.path("features").size() == 0) {
                throw new CustomException("No results found for the given address.");
            }

            JsonNode firstFeature = root.path("features").path(0);
            double latitude = firstFeature.path("geometry").path("coordinates").path(1).asDouble();
            double longitude = firstFeature.path("geometry").path("coordinates").path(0).asDouble();
            String formattedAddress = firstFeature.path("properties").path("formatted").asText();

            return new GeocodeResponse(formattedAddress, latitude, longitude);
        } catch (IOException e) {
            logger.error("Error occurred during geocoding: {}", e.getMessage());
            throw new CustomException("Geocoding failed: " + e.getMessage());
        }
    }

    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        try {
            URL url = new URL("https://api.geoapify.com/v1/geocode/reverse?lat=" + latitude + "&lon=" + longitude + "&apiKey=" + geoapifyApiKey);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            if (root.path("features").size() == 0) {
                throw new CustomException("No results found for the given coordinates.");
            }

            JsonNode firstFeature = root.path("features").path(0);
            String formattedAddress = firstFeature.path("properties").path("formatted").asText();

            return new ReverseGeocodeResponse(latitude, longitude, formattedAddress);
        } catch (IOException e) {
            logger.error("Error occurred during reverse geocoding: {}", e.getMessage());
            throw new CustomException("Reverse Geocoding failed: " + e.getMessage());
        }
    }

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
