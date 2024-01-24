## SpringHandsOn2 Project
This project is a Spring-based application that demonstrates the generation of vehicles 
with different components such as tyres and speakers. The project includes configuration,
logging, and services to manage the vehicle inventory of different factory.

## Directory Structure
- .
- ├── src
- │   ├── main
- │   │   ├── java
- │   │   │   ├── com
- │   │   │   │   ├── example
- │   │   │   │   │   ├── config
- │   │   │   │   │   │   ├── AppConfig.java
- │   │   │   │   │   │   └── Application.java
- │   │   │   │   │   ├── interfaces
- │   │   │   │   │   │   └── VehicleService.java
- │   │   │   │   │   ├── inventory
- │   │   │   │   │   │   └── VehicleInventory.java
- │   │   │   │   │   ├── logging
- │   │   │   │   │   │   └── Logging.java
- │   │   │   │   │   ├── model
- │   │   │   │   │   │   ├── Speaker.java
- │   │   │   │   │   │   ├── Tyre.java
- │   │   │   │   │   │   └── Vehicle.java
- │   │   │   │   │   ├── service
- │   │   │   │   │   │   ├── TyreService.java
- │   │   │   │   │   │   ├── SpeakerService.java
- │   │   │   │   │   │   └── VehicleServiceImpl.java
- │   │   │   │   │   └── resources
- │   │   │   │   │       └── application.properties
- ├── target
- ├── pom.xml


## Dependencies
- Spring Framework 6.1.3
- Logback Core 1.3.5
- Logback Classic 1.3.5
- Jakarta Annotation API 2.1.1
- javax.annotation API 1.3.2


## POM.xml Dependencies

```
<?xml version="1.0" encoding="UTF-8"?>
 <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- ... other POM configurations ... -->
    <dependencies>
        <!-- Spring Boot Starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Spring Boot Starter Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Logback Core -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.3.5</version>
        </dependency>

        <!-- Logback Classic -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.3.5</version>
        </dependency>

        <!-- Jakarta Annotation API -->
        <dependency>
            <groupId>jakarta.annotation</groupId>
            <artifactId>jakarta.annotation-api</artifactId>
            <version>2.1.1</version>
        </dependency>

        <!-- javax.annotation API -->
        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>javax.annotation-api</artifactId>
            <version>1.3.2</version>
        </dependency>

    </dependencies>
    <!-- ... other POM configurations ... -->
</project>

```

## Building and Running the Project

1. Ensure you have Java and Maven installed.
2. Clone the repository: `git clone https://github.com/your-username/spring-boot-vehicle-demo.git`
3. Navigate to the project directory: `cd spring-boot-vehicle-demo`
4. Build the project: `mvn clean install`
5. Run the application: `mvn spring-boot:run`


This will compile the project and generate the target directory containing the compiled classes and JAR file.


## Author
- Harsh Mishra