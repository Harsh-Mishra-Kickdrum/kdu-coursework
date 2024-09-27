## Spring Security Demo Project

This Spring Boot application demonstrates advanced user authentication and authorization functionalities using Spring Security and JSON Web Tokens (JWT). It includes custom security configurations, user management through a REST API, and JWT-based authentication.

## Table of Contents
1. [Project Overview](#spring-security-demo-project-overview)
2. [Key Features](#key-features)
3. [Implementation Overview](#implementation-overview)
4. [Project Directory Structure](#project-directory-structure)
5. [Setup and Installation](#setup-and-installation)
6. [Running the Project](#running-the-project)
7. [Usage](#usage)
8. [Dependencies](#dependencies)
9. [Contributing](#contributing)
10. [POM File](#pom-file)
11. [Author](#author)

## Spring Security Demo Project Overview

This project is a comprehensive demonstration of integrating Spring Security within a Spring Boot application to achieve robust authentication and authorization mechanisms. It uses JSON Web Tokens (JWT) for securing RESTful endpoints and provides a template for managing user authentication, authorization, and role-based access control.


### Key Features

- **User Authentication**: Implements custom authentication using Spring Security to verify user credentials and generate JWT tokens for authenticated sessions.
- **JWT Token Generation and Validation**: Utilizes JSON Web Tokens to manage user sessions and secure API access. The project includes filters for token generation upon login and token validation for every secured request.
- **Role-Based Access Control**: Demonstrates how to assign roles to users and restrict API access based on user roles, ensuring that resources are accessed only by users with appropriate permissions.
- **Custom User Details Management**: Integrates custom user details service to load user-specific data during the authentication process and support role-based authorization.
- **Exception Handling and Security Configuration**: Provides a global exception handling mechanism and customizes Spring Security configurations to meet specific security requirements, such as custom login and logout behaviors.
- **In-Memory User Management for Demonstration**: Includes an in-memory user DAO for demonstration purposes, showcasing how users can be managed and queried within the application.

### Implementation Overview

- **Security Configuration**: Custom security configurations are set up via `CustomSecurityConfig.java`, which extends `WebSecurityConfigurerAdapter` to customize security behaviors like session management, CORS, and CSRF protection.
- **Authentication Manager**: Utilizes `CustomAuthenticationManager` to authenticate users based on username and password, leveraging the `UserDetailsService` to load user-specific data.
- **UserDetailsService Implementation**: `CustomUserDetailsService` implements the `UserDetailsService` interface, providing the logic to load user details from the in-memory user DAO or a database.
- **JWT Filters**: Includes `TokenGeneratorFilter` and `TokenValidatorFilter` to handle JWT creation upon authentication and validate incoming JWT tokens in API requests, respectively.
- **REST Controllers**: Defines REST endpoints for user authentication (`AuthController`), user management (`UserController`), and administrative actions (`AdminController`), showcasing the application's RESTful API capabilities.
- **Service Layer**: Incorporates services like `UserService` and `UserServiceImpl` for abstracting the business logic associated with user management and authentication processes.
- **Password Encoding**: Uses `PasswordEncoder` in the service layer to ensure secure storage and validation of user passwords.

### Project Directory Structure

```
.
├── src
│ ├── main
│ │ ├── java
│ │ │ ├── com
│ │ │ │ ├── example
│ │ │ │ │ ├── springsecuritydemo
│ │ │ │ │ │ ├── config
│ │ │ │ │ │ │ ├── CustomAuthenticationManager.java
│ │ │ │ │ │ │ ├── CustomSecurityConfig.java
│ │ │ │ │ │ │ └── CustomUserDetailsService.java
│ │ │ │ │ │ ├── controller
│ │ │ │ │ │ │ ├── AdminController.java
│ │ │ │ │ │ │ ├── AuthController.java
│ │ │ │ │ │ │ └── UserController.java
│ │ │ │ │ │ ├── dao
│ │ │ │ │ │ │ ├── InMemoryUserDAO.java
│ │ │ │ │ │ │ └── UserDAO.java
│ │ │ │ │ │ ├── dto
│ │ │ │ │ │ │ ├── ErrorDTO.java
│ │ │ │ │ │ │ └── UserDto.java
│ │ │ │ │ │ ├── entity
│ │ │ │ │ │ │ └── User.java
│ │ │ │ │ │ ├── exception
│ │ │ │ │ │ │ ├── GlobalExceptionHandler.java
│ │ │ │ │ │ │ └── UserNotFoundException.java
│ │ │ │ │ │ ├── filter
│ │ │ │ │ │ │ ├── TokenGeneratorFilter.java
│ │ │ │ │ │ │ └── TokenValidatorFilter.java
│ │ │ │ │ │ ├── service
│ │ │ │ │ │ │ ├── StartUpDataAddition.java
│ │ │ │ │ │ │ ├── UserService.java
│ │ │ │ │ │ │ └── UserServiceImpl.java
│ │ │ │ │ │ └── SpringsecuritydemoApplication.java
│ │ │ │ │ └── resources
│ │ │ │ │ ├── static
│ │ │ │ │ ├── templates
│ │ │ │ │ └── application.properties
├── test
├── target
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── springsecuritydemo.iml
```





### Setup and Installation

1. **Clone the repository:**

```
`git clone https://github.com/username/spring-boot-vehicle-demo.git`
```
```
cd spring-security-demo
```
```
mvn clean install
mvn spring-boot:run

```

### Running the Project

After cloning and building the project as described in the setup section, you can start the application and test the following functionalities:

1. **User Authentication**: Send a POST request to `/api/auth/login` with valid credentials to receive a JWT token.
2. **Access Protected Endpoints**: Use the obtained JWT token as a Bearer token in the Authorization header to access protected resources based on the user's role.


### Usage
Authenticate: To authenticate, send a POST request to /api/auth/login with the username and password. Upon successful authentication, you will receive a JWT token.
Access Protected Endpoints: Use the received JWT token as a Bearer token in the Authorization header of your HTTP request to access protected resources.

### Dependencies
This project uses the following key dependencies:

- Spring Boot for the application framework.
- Spring Security for authentication and authorization.
- JWT for generating and validating JSON Web Tokens.
- H2 Database for in-memory database.
- Lombok for reducing boilerplate code.
- Contributing
- Contributions to this project are welcome! Please fork the repository, make your changes, and submit a pull request.

### POM FILE :
```
	<dependencies>
		<!-- Spring Boot Starter Web for REST full application -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- Spring Boot Starter Security for authentication and authorization -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<!-- Spring Boot DevTools for automatic restarts -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<!-- H2 Database for in-memory database -->
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Lombok for reducing boilerplate code -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<!-- Spring Boot Starter Test for testing support -->
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

		<!-- JSON Web Token Support -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.2</version>
		</dependency>


		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId> <!-- For JSON processing with JWT -->
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>

		<!-- Swagger (SpringFox) for API documentation -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-boot-starter</artifactId>
			<version>3.0.0</version>
		</dependency>

		<!-- Model Mapper for mapping -->
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.0.0</version>
		</dependency>

		<!-- Only as a last resort if you're sure the Servlet API isn't included -->
		<dependency>
			<groupId>jakarta.servlet</groupId>
			<artifactId>jakarta.servlet-api</artifactId>
			<version>4.0.4</version>
			<scope>provided</scope>
		</dependency>


	</dependencies>

```
## Author
### This project was developed by Harsh Mishra as a demonstration of Spring Security capabilities in a Spring Boot application context.