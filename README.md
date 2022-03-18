# Spring Boot REST API Project

Homework for Data Engineer position in Techcombank

## How to Run

* Clone this repository
* Build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by this command:
```
        java -jar target/restapi-0.0.1-SNAPSHOT.jar
```

## About the Service

* Testing using MockMVC 
* All APIs are "self-documented" by Swagger
* Two endpoints you can call :


```
http://localhost:8080/api/pools/
http://localhost:8080/api/pools/percentile
```

### To view Swagger 2 API docs

Run the server and browse to 
```
http://localhost:8080/swagger-ui/index.html
```

