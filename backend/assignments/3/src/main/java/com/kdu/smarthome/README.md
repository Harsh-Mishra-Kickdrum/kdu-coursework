# Smart Home Management System Documentation

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Swagger API Documentation](#swagger-api-documentation)

## Introduction

The Smart Home Management System developed by Kickdrum is designed to revolutionize the way users interact with their home automation devices. This comprehensive system enables users to manage multiple houses, rooms, and devices seamlessly, providing a user-friendly interface for the ultimate smart home experience.

## Features

- **Multiple Houses Management**: Users can create and manage several houses, each with its unique settings and devices.
- **Role-Based Access Control**: Assign roles with specific permissions to users, ensuring security and proper access management.
- **Device Management**: Easily add, configure, and control various smart devices within each house.
- **Room Organization**: Organize devices by rooms for efficient management and control.
- **Global House View**: Access a global dashboard to view and manage all connected houses.
- **Insights and Analytics**: Gain valuable insights into device usage patterns and operational statistics.
- **Address and Inventory Management**: Keep track of house addresses and maintain a comprehensive inventory of all devices.

## Technology Stack

- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **Frontend**: React (optional mention for a complete stack view)
- **Security**: JWT for secure authentication
- **API Documentation**: Swagger
- **Testing**: JUnit, Mockito for backend testing; Cypress for end-to-end testing (optional)

## Getting Started

To kickstart development, follow these steps:

1. Clone the repository to your local machine.
2. Set up a PostgreSQL database and configure the application.properties file with your database credentials.
3. Run the application locally.
4. Access and test the APIs using the provided base URL "/api/v1/".

### Prerequisites

- Java JDK 11+
- Maven
- PostgreSQL
- Docker (optional for containerization)

### Installation

1. Clone the repository:
    ```
    git clone <repository-url>
    ```
2. Set up the PostgreSQL database:
    ```
    - Create a new database named `smarthome`
    - Configure the database credentials in `application.properties`
    ```
3. Build the project with Maven:
    ```
    mvn clean install
    ```
4. Run the Spring Boot application:
    ```
    mvn spring-boot:run
    ```

## API Endpoints

The system provides a comprehensive set of RESTful APIs to manage the smart home ecosystem:

- `POST /api/v1/auth/register`: Register a new user.
- `POST /api/v1/house`: Add a new house.
- `POST /api/v1/house/{houseId}/add-user`: Add a user to a house.
- `GET /api/v1/house/`: Retrieve a list of houses.
- `PUT /api/v1/house`: Update the address of a house.
- `GET /api/v1/house/{houseId}`: Retrieve all rooms and devices associated with a specific house.
- `POST /api/v1/room`: Add rooms to a house.
- `GET /api/v1/inventory`: Retrieve the list of items in the inventory.
- `POST /api/v1/inventory`: Add an item to the inventory.
- `POST /api/v1/device/register`: Register a new device.
- `POST /api/v1/device/add`: Add a device to a house.

## Project Structure

The project is organized into several key directories for clarity and maintainability:

```
SmartHomeManagementSystem/
├── .idea/                                     # IDE specific settings
├── .mvn/                                      # Maven wrapper files
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── smarthome/
│   │   │       ├── bootloader/
│   │   │       │   └── SmartHomeApplication.java
│   │   │       ├── configuration/
│   │   │       │   ├── JwtAuthorizationFilter.java
│   │   │       │   └── SecurityConfiguration.java
│   │   │       ├── constants/
│   │   │       │   └── Constants.java
│   │   │       ├── controller/
│   │   │       │   ├── AuthenticationController.java
│   │   │       │   ├── DeviceController.java
│   │   │       │   ├── HouseController.java
│   │   │       │   ├── InventoryController.java
│   │   │       │   └── RoomController.java
│   │   │       ├── dto/
│   │   │       │   ├── request/
│   │   │       │   │   ├── AddDeviceRequest.java
│   │   │       │   │   ├── AddHouseRequest.java
│   │   │       │   │   ├── AddHouseUserRequest.java
│   │   │       │   │   ├── AddInventoryRequest.java
│   │   │       │   │   ├── AddRoomRequest.java
│   │   │       │   │   ├── DeviceRegisterRequest.java
│   │   │       │   │   ├── LoginRequest.java
│   │   │       │   │   ├── MoveDeviceRequest.java
│   │   │       │   │   └── UpdateAddressRequest.java
│   │   │       │   ├── response/
│   │   │       │   │   ├── AddDeviceResponse.java
│   │   │       │   │   ├── AddHouseResponse.java
│   │   │       │   │   ├── AddHouseUserResponse.java
│   │   │       │   │   ├── AddInventoryResponse.java
│   │   │       │   │   ├── AddRoomResponse.java
│   │   │       │   │   ├── DeviceRegisterResponse.java
│   │   │       │   │   ├── ErrorDTO.java
│   │   │       │   │   ├── ListAllDetailsResponse.java
│   │   │       │   │   ├── ListHouseResponse.java
│   │   │       │   │   ├── ListInventoryResponse.java
│   │   │       │   │   ├── LoginResponse.java
│   │   │       │   │   ├── UpdateAddressResponse.java
│   │   │       │   │   └── UserRegisterResponse.java
│   │   │       ├── exception/
│   │   │       │   ├── custom/
│   │   │       │   │   └── ResourceNotFoundException.java
│   │   │       │   └── global/
│   │   │       │       └── GlobalExceptionHandler.java
│   │   │       ├── model/
│   │   │       │   ├── DeviceInventoryEntity.java
│   │   │       │   ├── DevicesEntity.java
│   │   │       │   ├── HouseEntity.java
│   │   │       │   ├── RoomEntity.java
│   │   │       │   └── UserEntity.java
│   │   │       ├── repository/
│   │   │       │   ├── DeviceRepository.java
│   │   │       │   ├── HouseRepository.java
│   │   │       │   ├── InventoryRepository.java
│   │   │       │   ├── RoomRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       ├── service/
│   │   │       │   ├── AuthenticationService.java
│   │   │       │   ├── CustomUserDetailService.java
│   │   │       │   ├── DeviceService.java
│   │   │       │   ├── HouseService.java
│   │   │       │   ├── InventoryService.java
│   │   │       │   └── RoomService.java
│   │   │       └── utility/
│   │   │           ├── JwtUtility.java
│   │   │           └── Mapper.java
│   │   ├── resources/
│   │   │   ├── application.properties        # Main application properties
│   │   │   └── application-test.properties   # Properties for testing
│   ├── test/
│   │   ├── java/
│   │   │   └── smarthome/
│   │   │       ├── configuration/
│   │   │       ├── controller/
│   │   │       ├── DTO/
│   │   │       ├── utility/
│   │   │       └── SmartHomeApplicationTests.java
│   └── README.md
├── target/                                   # Compiled source code
├── .gitignore
├── Java Mini Project (1).pdf                 # Project documentation or guidelines
├── mvnw                                      # Maven wrapper executable for Unix
├── mvnw.cmd                                  # Maven wrapper executable for Windows
├── pom.xml                                   # Project Object Model for Maven
├── README.md
└── smartHome.iml                             # Project file for IntelliJ IDEA

```

## Dependencies

The project leverages a number of dependencies for functionality and ease of development:

- Spring Boot Starter Web for RESTful service creation
- Spring Boot Starter Data JPA for database interaction
- Spring Boot Starter Security for authentication and authorization
- PostgreSQL Driver for database connectivity
- JWT for secure token generation and validation
- Lombok for boilerplate code reduction
- Swagger for API documentation and testing interface


```xml
<!-- Dependencies section -->
    <dependencies>
        <!-- Spring Boot Starter Web for building web and RESTful applications -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA for data access layer -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Boot Starter Security for authentication and authorization -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- PostgreSQL JDBC Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok for reducing boilerplate code -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Spring Boot Starter Test for testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Spring Security Test for security testing -->
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Jakarta Persistence API -->
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.0.0</version>
        </dependency>


        <!-- JWT support for Spring Security -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.3</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.3</version>
        </dependency>

        <!-- H2 Database for in-memory database testing -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Jackson Databind for JSON processing -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

        <!-- JUnit for testing -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- SLF4J with Logback for logging -->
        <!-- Adding SLF4J and Logback as it is commonly used for logging in Spring Boot applications -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Validation API for bean validation -->
        <!-- Useful for validating input data in your application -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>


        <!-- Jakarta Bean Validation API -->
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
            <version>3.0.0</version>
        </dependency>

        <!-- Hibernate Validator - Jakarta Bean Validation implementation -->
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>7.0.1.Final</version>
        </dependency>

        <!-- javax.xml.bind:jaxb-api for Java XML Binding -->
        <!-- This dependency was replaced by Jakarta XML Binding in newer Java versions,
             but adding it in case you need it for backward compatibility or specific use cases -->
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version> <!-- Use the latest version available -->
        </dependency>


        <!-- Open-Api Swagger for API Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.2.0</version>
        </dependency>

        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>5.0.0</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>
    </dependencies>

```

## Swagger API Documentation
Swagger is integrated into the project to offer a clear and interactive API documentation. Once
the application is running, access the Swagger UI to explore the available RESTful APIs and their 
specifications:

```
http://localhost:8080/swagger-ui.html

```

## Author
### **HARSH MISHRA**