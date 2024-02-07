## Spring JPA Demo Project

This Spring Boot application demonstrates the use of Spring Data JPA for database operations, emphasizing simplicity and the power of JPA for ORM (Object-Relational Mapping). It includes examples of CRUD operations, relationship management, and how to configure entities in a Spring application.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Key Features](#key-features)
3. [Implementation Overview](#implementation-overview)
4. [Project Directory Structure](#project-directory-structure)
5. [Setup and Installation](#setup-and-installation)
6. [Running the Project](#running-the-project)
7. [Dependencies](#dependencies)
8. [Author](#author)

## Project Overview

This project showcases how to integrate Spring Data JPA into a Spring Boot application to abstract away the boilerplate code associated with database operations. It is designed to provide clear examples of entity configuration, performing CRUD operations, and managing entity relationships within a Spring context.

### Key Features

- **CRUD Operations**: Demonstrates the creation, reading, updating, and deletion of entities using Spring Data JPA repositories.
- **Entity Relationship Management**: Shows how to define and manage one-to-many, many-to-one, and many-to-many relationships between entities.
- **Repository Abstraction**: Utilizes Spring Data JPA repositories to abstract the complexity of database interactions.
- **Exception Handling**: Implements best practices for handling exceptions, particularly those related to database operations.
- **Query Methods**: Illustrates the use of JPA query methods for custom database queries.

### Implementation Overview

- **Entity Configuration**: Entities are set up with annotations to map them to database tables.
- **Repository Layer**: Includes examples of Spring Data JPA repositories for abstracting CRUD operations.
- **Service Layer**: Provides a service layer to encapsulate business logic, leveraging repositories for database interaction.
- **RESTful API**: Demonstrated through REST endpoints that interact with the service layer to perform entity operations.

### Project Directory Structure

```
spring-jpa-demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── springjpademo/
│   │   │       ├── bootloader/
│   │   │       │   └── SpringjpademoApplication.java
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       │   ├── ShiftController.java
│   │   │       │   ├── ShiftTypeController.java
│   │   │       │   ├── ShiftUserController.java
│   │   │       │   └── UserController.java
│   │   │       ├── dto/
│   │   │       │   ├── AllEntitiesDTO.java
│   │   │       │   ├── ShiftDto.java
│   │   │       │   ├── ShiftTypeDto.java
│   │   │       │   ├── ShiftUserDto.java
│   │   │       │   ├── TenantDto.java
│   │   │       │   └── UserDto.java
│   │   │       ├── entity/
│   │   │       │   ├── Shift.java
│   │   │       │   ├── ShiftType.java
│   │   │       │   ├── ShiftUser.java
│   │   │       │   ├── Tenant.java
│   │   │       │   └── User.java
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── UserNotFoundException.java
│   │   │       ├── repository/
│   │   │       │   ├── ShiftRepository.java
│   │   │       │   ├── ShiftTypeRepository.java
│   │   │       │   ├── ShiftUserRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       └── service/
│   │   │           ├── ShiftService.java
│   │   │           ├── ShiftTypeService.java
│   │   │           ├── ShiftUserService.java
│   │   │           └── UserService.java
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   ├── templates/
│   │   │   └── application.properties
│   │   └── test/
│   │       └── java/
│   │           └── springjpademo/  
├── README.md
├── .gitignore  
└── pom.xml  
```


### Setup and Installation

1. **Clone the repository:**

```bash
git clone https://github.com/harsh-mishra-kickdrum/spring-jpa-demo.git
cd spring-jpa-demo
```

1. Build the project:
```
mvn clean install
```
2. Run the application:
```
mvn spring-boot:run
```

### Running the Project
After completing the setup, you can start the application and interact with it through REST endpoints to perform CRUD operations on entities on postman.

### Dependencies
This project uses the following key dependencies:

1. Spring Boot for the application framework.
2. Spring Data JPA for ORM-based database operations.
3. H2 Database for an in-memory database, suitable for development and testing.
4. Spring Boot Starter Web for developing RESTful APIs.
5. Lombok for reducing boilerplate code.

```xml

	<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

	</dependencies>
```

### Author
- This project was developed by `Harsh Mishra` as a demonstration of JPA capabilities in a Spring Boot application context.