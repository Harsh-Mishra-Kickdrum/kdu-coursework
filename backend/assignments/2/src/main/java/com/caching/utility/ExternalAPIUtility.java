package com.caching.utility;

import com.caching.dto.GeocodeResponse;
import com.caching.dto.ReverseGeocodeResponse;
import com.caching.exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private  static final  String POSITIONSTACKAPIKEY = "ac525714ee152e4b41df5a1a4af2ee16";

    public GeocodeResponse getGeocode(String address) {
        try {
            String encodedAddress = java.net.URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            URL url = new URL("http://api.positionstack.com/v1/forward?access_key=" + POSITIONSTACKAPIKEY + "&query=" + encodedAddress);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode firstResult = root.path("data").path(0);
            double latitude = firstResult.path("latitude").asDouble();
            double longitude = firstResult.path("longitude").asDouble();
            String formattedAddress = firstResult.path("label").asText();

            return new GeocodeResponse(formattedAddress, latitude, longitude);
        } catch (IOException e) {
            logger.error("Error occurred during geocoding");
            throw new CustomException("Geocoding failed: " + e.getMessage());
        }
    }

    public ReverseGeocodeResponse getReverseGeocode(double latitude, double longitude) {
        try {
            URL url = new URL("http://api.positionstack.com/v1/reverse?access_key=" + POSITIONSTACKAPIKEY + "&query=" + latitude + "," + longitude);
            String response = sendRequest(url);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode firstResult = root.path("data").path(0);
            String formattedAddress = firstResult.path("label").asText();

            return new ReverseGeocodeResponse(latitude, longitude, formattedAddress);
        } catch (IOException e) {
            logger.error("Error occurred during reverse geocoding");
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
