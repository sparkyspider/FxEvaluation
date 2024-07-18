# Mark van Wyk Code Evaluation

### Instructions

This is a Spring Boot Java application that is managed by Gradle. The Gradle build configuration file is in `build.gradle` and the app properties are found in `/src/main/resources/application.properties` 

To start the application, use `./gradlew bootRun`

### Accessing the Database
The application uses an in-memory database. The database can be accessed by:

1. Starting the application (which will fire up a database and create a web interface for it)
2. Visiting https://localhost:8080/h2 (assuming port 8080 was available. See console output to confirm)
3. Logging in with:

    | Parameter    | Value              |
    |--------------|--------------------|
    | JDBC url     | jdbc:h2:mem:testdb |
    | Driver Class | org.h2.driver      |
    | JDBC URL     | jdbc:h2:mem:testdb |
    | user         | sa                 |
    | password     | *&lt;blank&gt;*    |

### Making sense of the application

At this point, the application does the following:
1. Starts generating rates at random intervals and popping them into the MARKET_DATA table.
2. (allow the app some time to generate MARKET_DATA)
3. Hit the /historic url
4. Assess the opportunities in the OPPORTUNITY table
5. You can also run the tests

### Running the tests

1. The tests are in /src/test/java/co.fxflow.evaluation.ArbitrageDetector
2. ./gradlew test (let me know if it doesn't output anything)

### Known Issues:

1. The Market Data Feed Generator does not keep the volatility swing relative the average. The swing is based on the last value.
2. Only triangles are calculated at the moment, but the recursive function is designed to be easily updated. 
