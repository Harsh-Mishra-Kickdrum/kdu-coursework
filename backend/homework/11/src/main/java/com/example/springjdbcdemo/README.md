## Spring JDBC Demo Project

This Spring Boot application demonstrates the usage of Spring JDBC for database operations, focusing on simplicity and the direct use of SQL with Spring's JDBC support. It includes examples of CRUD operations, transaction management, and how to configure a DataSource in a Spring application.

## Table of Contents
1. [Project Overview](#spring-jdbc-demo-project-overview)
2. [Key Features](#key-features)
3. [Implementation Overview](#implementation-overview)
4. [Project Directory Structure](#project-directory-structure)
5. [Setup and Installation](#setup-and-installation)
6. [Running the Project](#running-the-project)
7. [Dependencies](#dependencies)
8. [Author](#author)

## Spring JDBC Demo Project Overview

This project showcases how to integrate Spring JDBC into a Spring Boot application for direct SQL operations. It is designed to provide a clear example of configuring a `DataSource`, performing CRUD operations, and handling transactions within a Spring context.

### Key Features

- **CRUD Operations**: Demonstrates the execution of Create, Read, Update, and Delete operations using Spring JDBC templates.
- **DataSource Configuration**: Shows how to configure a `DataSource` bean in a Spring application for database connectivity.
- **Transaction Management**: Illustrates the use of Spring's declarative transaction management capabilities.
- **Simple and Direct SQL**: Utilizes Spring JDBC for straightforward SQL operations without the need for an ORM framework.
- **Exception Handling**: Implements Spring's best practices for handling database exceptions.

### Implementation Overview

- **DataSource Configuration**: Set up in `AppConfig.java`, demonstrating how to configure a connection to the database.
- **DAO Layer**: Includes examples of Data Access Objects (DAOs) that use `JdbcTemplate` for database operations.
- **Service Layer**: Provides a service layer to encapsulate business logic and transactions, calling upon DAOs to interact with the database.
- **CRUD Operations**: Demonstrated through REST endpoints that interact with the service layer to perform database operations.

### Project Directory Structure
```
src/
├── main/
│   ├── java/
│   │   └── springjdbcdemo/
│   │       ├── config/
│   │       │   └── AppConfig.java
│   │       ├── controller/
│   │       │   └── TenantController.java
│   │       ├── dao/
│   │       │   ├── AllEntitiesDao.java
│   │       │   ├── ShiftDao.java
│   │       │   ├── ShiftTypeDao.java
│   │       │   ├── ShiftUserDao.java
│   │       │   └── UserDao.java
│   │       ├── dto/
│   │       │   ├── AllEntitiesDTO.java
│   │       │   ├── ShiftDto.java
│   │       │   ├── ShiftTypeDto.java
│   │       │   ├── ShiftUserDto.java
│   │       │   ├── TenantDto.java
│   │       │   └── UserDto.java
│   │       ├── exception/
│   │       ├── mapper/
│   │       ├── model/
│   │       ├── service/
│   │       │   ├── AllEntitiesService.java
│   │       │   ├── ShiftService.java
│   │       │   ├── ShiftTypeService.java
│   │       │   ├── ShiftUserService.java
│   │       │   └── UserService.java
│   │       └── JdbcdemoprojectApplication.java
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```


### Setup and Installation

1. **Clone the repository:**

```bash
git clone https://github.com/username/spring-jdbc-demo.git
cd spring-jdbc-demo
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
After completing the setup, you can start the application and interact with it through REST endpoints to perform CRUD operations on the  entity.

### Dependencies
This project uses the following key dependencies:

- Spring Boot for the application framework.
- Spring JDBC for database operations.
- H2 Database for an in-memory database.
- Spring Boot Starter Web for RESTful application development.
- Lombok for reducing boilerplate code.

```
<!-- Project Dependencies -->
	<dependencies>
		<!-- Spring Boot Data JDBC Starter -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>

		<!-- Spring Boot Web Starter -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- Spring Boot Validation Starter -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<!-- Spring Boot DevTools for live reloading and development -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<!-- PostgreSQL Driver -->
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Lombok for boilerplate code reduction -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<!-- Spring Boot Test Starter -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

## Author
### This project was developed by   `Harsh Mishra` as a demonstration of JDBC capabilities in a Spring Boot application context.