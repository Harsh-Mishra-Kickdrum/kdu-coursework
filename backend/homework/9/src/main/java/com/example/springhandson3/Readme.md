## SpringHandsOn4 Project

This project is a Spring-based application that implements CRUD APIs for managing vehicles 
in a factory's inventory. The application allows users to add, get, update, and delete vehicles,
as well as retrieve the most and least expensive vehicles.

## Table of Contents
- [Directory Structure](#directory-structure)
- [Dependencies](#dependencies)
- [Build Instructions](#build-instructions)
- [API Paths](#api-paths)
- [Implementation Details](#implementation-details)
- [Testing](#testing)
- [Author](#author)


## Directory Structure
```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── controller
│   │   │           │   └── VehicleController.java
│   │   │           ├── dto
│   │   │           │   ├── request
│   │   │           │   │   ├── AddVehicleRequest.java
│   │   │           │   │   └── UpdateVehicleRequest.java
│   │   │           │   └── response
│   │   │           │       └── VehicleResponse.java
│   │   │           ├── entity
│   │   │           │   └── Vehicle.java
│   │   │           ├── exception
│   │   │           │   ├── customexceptions
│   │   │           │   │   ├── BadRequestException.java
│   │   │           │   │   └── ResourceNotFoundException.java
│   │   │           │   └── handlers
│   │   │           │       ├── BadRequestExceptionHandler.java
│   │   │           │       ├── GlobalExceptionHandler.java
│   │   │           │       └── ResourceNotFoundExceptionHandler.java
│   │   │           │       └── ErrorResponse.java
│   │   │           ├── repository
│   │   │           │   └── VehicleRepository.java
│   │   │           └── services
│   │   │               ├── VehicleService.java
│   │   │               └── VehicleServiceImpl.java
│   ├── resources
│   │   ├── static
│   │   ├── templates
│   │   ├── application.properties
│   │   ├── application-dev.properties
│   │   ├── application-prod.properties
│   │   └── logback.xml
├── target
├── pom.xml
└── Readme.md

```

## Dependencies

- Spring Framework 6.1.3
- Lombok (for code simplification)

## Build Instructions

To build this project, use the provided Maven `pom.xml` file. Ensure you have Maven installed and run:

```
mvn clean install
```
#### For development:
```
mvn clean install
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

#### For production
```
mvn clean install
mvn spring-boot:run -Dspring-boot.run.profiles=prod

```


## API Paths

- **Add Vehicle**
    - Endpoint: `POST /vehicles`
    - Request Body: `AddVehicleRequest`
    - Response: `VehicleResponse`

- **Get Vehicle by ID**
    - Endpoint: `GET /vehicles/{id}`
    - Path Variable: `id`
    - Response: `VehicleResponse`

- **Update Vehicle by ID**
    - Endpoint: `PUT /vehicles/{id}`
    - Path Variable: `id`
    - Request Body: `UpdateVehicleRequest`
    - Response: `VehicleResponse`

- **Delete Vehicle by ID**
    - Endpoint: `DELETE /vehicles/{id}`
    - Path Variable: `id`
    - Response: No content (204)

- **Get All Vehicles**
    - Endpoint: `GET /vehicles`
    - Response: List of `VehicleResponse`

- **Get Most Expensive Vehicle**
    - Endpoint: `GET /vehicles/most-expensive`
    - Response: `Vehicle`

- **Get the Least Expensive Vehicle**
    - Endpoint: `GET /vehicles/least-expensive`
    - Response: `Vehicle`

## Implementation Details

- The project follows a standard MVC architecture with separate packages for controller, dto, entity, repository, and services.
- Lombok is used to reduce boilerplate code in entity and DTO classes.
- CRUD operations are implemented in the `VehicleRepository` class using an in-memory `ConcurrentHashMap`.
- Service methods in `VehicleService` and `VehicleServiceImpl` provide business logic for the controller.
- Exception handling is implemented using custom exceptions and global exception handlers.

## Testing
- Postman can be used to test the various API endpoints mentioned above.

## Author
**Harsh Mishra**
