package com.caching;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.kdu.caching")
public class GeoCodingImpTest {

    private static Object expectedGeoCodingApiResponse;
    private static Object expectedReverseGeoCodingApiResponse;
    private static String geoCodingTestUrl;
    private static String reverseGeoCodingTestUrl;
    private static String geoapifyApiKey;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CacheManager cacheManager;

    @BeforeAll
    public static void setup(@Value("${geoapify.geocoding-url}") String geoCodingUrl,
                             @Value("${geoapify.reverse-geocoding-url}") String reverseGeoCodingUrl,
                             @Value("${geoapify.api.key}") String apiKey) {
        geoCodingTestUrl = geoCodingUrl;
        reverseGeoCodingTestUrl = reverseGeoCodingUrl;
        geoapifyApiKey = apiKey;

        RestTemplate restTemplate = new RestTemplate();
        expectedGeoCodingApiResponse = restTemplate.getForObject(
                geoCodingTestUrl.replace("{address}", "delhi").replace("{apikey}", geoapifyApiKey),
                Object.class);

        expectedReverseGeoCodingApiResponse = restTemplate.getForObject(
                reverseGeoCodingTestUrl.replace("{latitude}", "37.431155").replace("{longitude}", "-120.781462").replace("{apikey}", geoapifyApiKey),
                Object.class);
    }

    @Test
    @Order(4)
    public void testGetGeoCodeNegative() {
        int resultCode = HttpStatus.OK.value();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                            .param("address", "invalid_address")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            resultCode = result.getResponse().getStatus();
            if (resultCode != HttpStatus.OK.value()) {
                throw new Exception();
            }
        } catch (Exception e) {
            assertTrue(resultCode >= HttpStatus.BAD_REQUEST.value() &&
                            resultCode < HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "HTTP Status Code should be 4xx for client error");

        } finally {
            if (resultCode == HttpStatus.OK.value()) {
                fail("Expected HttpClientErrorException, but got a response with status code: " + resultCode);
            }
        }
    }

    @Test
    @Order(1)
    public void testGetReverseGeoCodeNegative() {
        int resultCode = HttpStatus.OK.value();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                            .param("latitude", "cat")
                            .param("longitude", "-120.781462")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            resultCode = result.getResponse().getStatus();
            if (resultCode != HttpStatus.OK.value()) {
                throw new Exception();
            }
        } catch (Exception e) {
            assertTrue(resultCode >= HttpStatus.BAD_REQUEST.value() &&
                            resultCode < HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "HTTP Status Code should be 4xx for client error");

        } finally {
            if (resultCode == HttpStatus.OK.value()) {
                fail("Expected HttpClientErrorException, but got a response with status code: " + resultCode);
            }
        }
    }

    @Test
    @Order(2)
    public void testGetGeoCode() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", "delhi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultInStringFormat = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Double> actualResponse = objectMapper.readValue(resultInStringFormat, Map.class);

        assertNotNull(actualResponse, "Response body should not be null");
        assertTrue(actualResponse instanceof Map, "Response should be a Map");

        HashMap<?, ?> expectedResponseMap = (HashMap<?, ?>) ((ArrayList<?>) ((HashMap<?, ?>) expectedGeoCodingApiResponse).get("data")).get(0);

        Double expectedLatitude = (Double) expectedResponseMap.get("latitude");
        Double expectedLongitude = (Double) expectedResponseMap.get("longitude");

        Double actualLatitude = actualResponse.get("latitude");
        Double actualLongitude = actualResponse.get("longitude");

        assertEquals(expectedLatitude, actualLatitude, "Latitude should match");
        assertEquals(expectedLongitude, actualLongitude, "Longitude should match");
    }

    @Test
    @Order(3)
    public void testGeoCodingCacheHitWithEndpoint() throws Exception {
        hitGeoCodingCache("delhi");
        hitGeoCodingCache("delhi");

        assertNotNull("Cache 'geocoding' should not be null", cacheManager.getCache("geocoding").toString());
        assertNotNull("Cache entry 'delhi' should not be null", cacheManager.getCache("geocoding").get("delhi").toString());

        cacheManager.getCache("geocoding").clear();
    }

    @Test
    @Order(5)
    public void testGeoCodingCacheMiss() throws Exception {
        hitGeoCodingCache("goa");
        assertNull(cacheManager.getCache("geocoding").get("goa"), "Cache miss unsuccessful: Cache entry must be null");
    }

    @Test
    @Order(6)
    public void testGeoCodingCacheEviction() throws Exception {
        hitGeoCodingCache("goa");
        hitGeoCodingCache("delhi");

        assertNull(cacheManager.getCache("geocoding").get("goa"), "Cache evict unsuccessful - Cache entry must be null");
        assertNotNull(cacheManager.getCache("geocoding").get("delhi"), "Cache evict unsuccessful - Cache entry must be null");
    }

    @AfterEach
    public void tearDown() {
        cacheManager.getCache("geocoding").clear();
        cacheManager.getCache("reverse-geocoding").clear();
    }

    private void hitGeoCodingCache(String address) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
