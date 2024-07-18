# Mark van Wyk Code Ev

# Instructions

This is a Spring Boot Java application that is managed by Gradle. The Gradle build configuration file is in `build.gradle` and the app properties are found in `/src/main/resources/application.properties` 

To start the application, use `./gradlew bootrun`

The application uses an in-memory database. The database can be accessed by:

1. Starting the application
2. Visiting https://localhost:8080/h2 (assuming port 8080 was available. See console output to confirm)
3. Logging in with:

    | Parameter    | Value              |
    |--------------|--------------------|
    | JDBC url     | jdbc:h2:mem:testdb |
    | Driver Class | org.h2.driver      |
    | JDBC URL     | jdbc:h2:mem:testdb |
    | user         | sa                 |
    | password     | *&lt;blank&gt;*    |

### Known Issues:

1. The Market Data Feed Generator does not keep the volatility swing relative the average. The swing is based on the last value.
2. 
