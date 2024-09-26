# Geocoding and Decoding Project

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Folder Structure](#folder-structure)
- [Dependencies](#dependencies)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Working Flow of the Code](#working-flow-of-the-code)
- [API Endpoints](#api-endpoints)
- [Error Handling](#error-handling)
- [Caching Strategy](#caching-strategy)
- [Author](#author)

## Introduction
The Geocoding and Decoding Project is a Java-based application developed for efficient geocoding and reverse geocoding operations. The application interfaces with the Geoapify API and implements caching mechanisms to optimize performance.

## Features
- Geocoding: Convert addresses into geographic coordinates.
- Reverse Geocoding: Convert geographic coordinates into human-readable addresses.
- Efficient caching to reduce API calls and improve response times.

## Technologies
- Java 11
- Spring Boot 2.7.8
- Maven
- Caffeine Cache

## Folder Structure

```
geocoding_application/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ com/
│  │  │  │  ├─ caching/
│  │  │  │  │  ├─ config/
│  │  │  │  │  │  └─ CacheConfig.java
│  │  │  │  │  ├─ controller/
│  │  │  │  │  │  └─ GeocodingController.java
│  │  │  │  │  ├─ dto/
│  │  │  │  │  │  ├─ GeocodeResponse.java
│  │  │  │  │  │  └─ ReverseGeocodeResponse.java
│  │  │  │  │  ├─ exception/
│  │  │  │  │  │  ├─ CustomException.java
│  │  │  │  │  │  └─ GlobalExceptionHandler.java
│  │  │  │  │  ├─ model/
│  │  │  │  │  │  ├─ GeocodeData.java
│  │  │  │  │  │  └─ LatLongKey.java
│  │  │  │  │  ├─ repository/
│  │  │  │  │  │  ├─ GeocodingCacheRepository.java
│  │  │  │  │  │  └─ GeocodingCacheRepositoryInterface.java
│  │  │  │  │  ├─ service/
│  │  │  │  │  │  ├─ GeocodingService.java
│  │  │  │  │  │  └─ GeocodingServiceImpl.java
│  │  │  │  │  └─ utility/
│  │  │  │  │     └─ ExternalAPIUtility.java
│  │  │  ├─ resources/
│  │  │  │  ├─ static/
│  │  │  │  ├─ templates/
│  │  │  │  └─ application.properties
│  ├─ test/
├─ target/
├─ .mvn/
├─ .gitignore
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
└─ README.md


```

The `src` directory contains the source code, organized into various packages. The `resources` directory holds configuration and static resources.

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Cache
- Caffeine cache for in-memory caching
- Spring Boot Starter Test
- Lombok
- org.json for JSON processing
- Apache Commons Lang3
- Spring Boot Starter Validation
- Mockito for testing
- Jackson Databind for JSON processing

## Setup and Installation
To set up and run the Geocoding and Decoding Project, follow these steps:

1. **Clone the Repository:**  
   Clone the project to your local machine using the following command:

```
git clone [https://github.com/Harsh-Mishra-Kickdrum/kdu-coursework/tree/Assignment-2/backend/assignments/2]
```


2. **Configure Properties:**  
   Navigate to `src/main/resources` and update the `application.properties` file with the necessary configurations, including your Geoapify API key.

3. **Build the Project:**  
   Run the following command in the project root directory to build the project using Maven:
```
./mvnw clean install
 ```

4. **Run the Application:**  
   Once the build is successful, start the application with:
```
./mvnw spring-boot:run
```

5. **Verify Installation:**  
   The application should now be running on `http://localhost:8080`. You can verify this by accessing the health check endpoint or the main application URL.

## Usage
To use the Geocoding and Decoding Project, make HTTP requests to the application's endpoints. Below are some example usages:

- **Geocoding Endpoint:**  
  Convert an address to latitude and longitude.
``` 
GET /geocode?address=[ADDRESS] 
```


- **Reverse Geocoding Endpoint:**  
  Convert latitude and longitude to an address.

```
GET /reverse-geocode?lat=[LATITUDE]&lon=[LONGITUDE]

```

## Working Flow of the Code
The Geocoding and Decoding Project operates through a series of steps, as outlined below:

1. **API Request Reception:**  
   The application receives a geocoding or reverse geocoding request through one of its REST endpoints (`/geocode` or `/reverse-geocode`).

2. **Request Validation:**  
   The request parameters (address for geocoding or latitude and longitude for reverse geocoding) are validated. If the validation fails, an error response is returned.

3. **Caching Check:**  
   Before processing the request, the application checks its cache (implemented using Caffeine) to see if the requested data is already available. If a cache hit occurs, the cached data is returned immediately, skipping further processing steps.

4. **External API Call:**  
   If the requested data is not in the cache, the application makes an external API call to the Geoapify service. The service requires an API key, which is configured in `application.properties`.

5. **Data Processing:**  
   The response from Geoapify is processed. For geocoding, this involves extracting latitude and longitude; for reverse geocoding, it involves extracting the address.

6. **Caching the Response:**  
   The processed response is stored in the cache for future requests, improving response times and reducing external API calls.

7. **Response to Client:**  
   The final response, containing the geocoding or reverse geocoding data, is sent back to the client.

8. **Error Handling:**  
   Throughout this process, any exceptions or errors are caught by a global exception handler, which returns a standardized error response to the client.

## API Endpoints
The Geocoding and Decoding Project provides the following API endpoints:

- **Geocode:** `/geocode?address=[ADDRESS]`  
  Converts a given address to geographic coordinates.

- **Reverse Geocode:** `/reverse-geocode?lat=[LATITUDE]&lon=[LONGITUDE]`  
  Converts geographic coordinates to a human-readable address.

## Error Handling
The application employs a global exception handling strategy to manage errors gracefully. Key points include:

- **Custom Exception Classes:**  
  The application defines custom exception classes for specific error scenarios.

- **Global Exception Handler:**  
  A global exception handler captures exceptions and formats them into a standardized response structure, including appropriate HTTP status codes.

- **Validation Errors:**  
  Input validation errors are caught and returned to the user with informative messages.

## Caching Strategy
The project implements an efficient caching strategy to optimize API usage and response times:

- **Caffeine Cache:**  
  An in-memory caching solution, Caffeine, is used to store recent geocoding and reverse geocoding results.

- **Cache Configuration:**  
  Cache size and expiration are configurable in `application.properties`.

- **Cache Eviction:**  
  Old entries are automatically evicted based on the defined caching policy, ensuring fresh data and efficient memory usage.

## Author

### ** Harsh Mishra **


