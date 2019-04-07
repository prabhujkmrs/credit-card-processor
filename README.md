# credit card processor
It is a simple full stack application for a credit card provider that allows to add new credit card accounts and view them as list.
The application exposes two HTTP REST endpoints as shown below

- **POST** create new card for a given name, number and limit
- **GET**  retrieve all cards from the system 

## Validation
- Input validation for mandatory fields
- Credit card numbers checked for compatibility with Luhn 10

## UI
User interface is build using thymeleaf template engine. It will show error message on the form for unsuccessful validations.

## Tools used
```
Thymeleaf template engine.
Java8
spring
maven

```

## Run the below command to build:

```
mvn clean install

```

## Testing
To run the unit tests, execute:

```
mvn verify

```

## To run
To run the application, execute:

java -jar ./target/credit-card-processor-1.0.jar

Then open a browser and enter the url : http://localhost:8080/cards
