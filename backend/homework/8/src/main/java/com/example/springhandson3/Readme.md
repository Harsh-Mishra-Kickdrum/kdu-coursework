## SpringHandsOn3 Project

This project is a Spring-based application that implements CRUD APIs for managing vehicles in a factory's inventory. 
The application allows users to add, get, update, and delete vehicles, as well as retrieve the most and least expensive vehicles.

## Directory Structure

- .
- ├── src
- │   ├── main
- │   │   ├── java
- │   │   │   └── com
- │   │   │       └── example
- │   │   │           ├── controller
- │   │   │           │   └── VehicleController.java
- │   │   │           ├── dto
- │   │   │           │   ├── request
- │   │   │           │   │   ├── AddVehicleRequest.java
- │   │   │           │   │   └── UpdateVehicleRequest.java
- │   │   │           │   ├── response
- │   │   │           │   │   └── VehicleResponse.java
- │   │   │           ├── entity
- │   │   │           │   └── Vehicle.java
- │   │   │           ├── repository
- │   │   │           │   └── VehicleRepository.java
- │   │   │           └── services
- │   │   │               ├── VehicleService.java
- │   │   │               └── VehicleServiceImpl.java
- ├── target
- ├── pom.xml


## Dependencies

- Spring Framework 6.1.3
- Lombok (for code simplification)

## Build Instructions

To build this project, use the provided Maven `pom.xml` file. Ensure you have Maven installed and run:

```
mvn clean install
```

## API Paths
- Add Vehicle

- Endpoint: POST /vehicles
- Request Body: AddVehicleRequest
- Response: VehicleResponse
- Get Vehicle by ID

- Endpoint: GET /vehicles/{id}
- Path Variable: id
- Response: VehicleResponse
- Update Vehicle by ID

- Endpoint: PUT /vehicles/{id}
- Path Variable: id
- Request Body: UpdateVehicleRequest
- Response: VehicleResponse
- Delete Vehicle by ID

- Endpoint: DELETE /vehicles/{id}
- Path Variable: id
- Response: No content (204)
- Get All Vehicles

- Endpoint: GET /vehicles
- Response: List of VehicleResponse
- Get Most Expensive Vehicle

- Endpoint: GET /vehicles/most-expensive
- Response: Vehicle
- Get Least Expensive Vehicle

- Endpoint: GET /vehicles/least-expensive
- Response: Vehicle

## Implementation Details

- The project follows a standard MVC architecture with separate packages for controller, dto, entity, repository, and services.
- Lombok is used to reduce boilerplate code in entity classes.
- CRUD operations are implemented in the VehicleRepository class using an in-memory ConcurrentHashMap.
- Service methods in VehicleService and VehicleServiceImpl provide business logic for the controller.

## Testing
- Postman can be used to test the various API endpoints mentioned above.

## AUTHOR
### HARSH MISHRA