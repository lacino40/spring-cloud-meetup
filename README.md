## Description
This project is a demo for Spring Cloud, showcasing its set of features that make microservice development easier 
and more manageable. It aims to simulate a real-world application scenario, enabling users to better understand 
the principles of Spring Cloud and how it eases the challenges of building complex enterprise systems.

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems. 
In this project, you will find examples of how to use these tools practically. The project 
encapsulates multiple subprojects each demonstrating a unique feature of Spring Cloud - including distributed configuration, 
service discovery, circuit breakers, routing, micro-proxy and more.

Regardless of your experience level with Spring Cloud, this project serves as an education resource to learn and understand 
how to design applications using cloud-native technologies.

## Modules
### config-server
The `config-server` module, as the name suggests, handles features of the Spring cloud configuration server. It can manage 
all the application-related configuration properties in a decentralized manner. This server is backed by a version control 
system, which aids in maintaining and auditing the configuration changes. 

It grants dynamic refresh scope, meaning that we can dynamically update the application configuration without 
reloading/restarting the whole application. 

### configuration-store
The `configuration-store` module handles the storage of all configuration properties 
which are centralized and managed by the Config-Server.

### eureka-server
The `eureka-server` module is a service registry for our microservice ecosystem. It uses Netflix's Eureka for 
providing the Server-Client setup for all microservices to register themselves as clients, and the Eureka-Server 
maintains a registry of these clients

### gateway-service
The `gateway-service` module acts as the entry point to our system in the context of microservices architecture. It runs 
on the edge of the network to handle client requests

### location-service
The `gateway-service` module provides an endpoint to fetch list of locations/cities for purposes of demo application. 

### weather-service
The `gateway-service` module provides an endpoint to get detail weather data for selected location/city for purposes of 
demo application. Also, it requests remote service called open-weather-map to retrieve live weather data.

### web-service
The `web-service` module provides web application to communicate with different microservices through the API 
to fetch, process, and present data to the users. This module leverages the benefit of Spring MVC and Thymeleaf view 
engine for rendering dynamic web pages

### zipkin-server 
The `zipkin-server` module provides a distributed tracing system. It helps gather timing data needed to troubleshoot 
latency problems in service architectures.



## Setup and Usage
### Prerequisites

Before you begin, ensure you have met the following requirements:

- `Java Development Kit (JDK) version 21`
- `Zipkin version 3.0.5 or later`. Download the Zipkin jar file and copy it into the `zipkin-server/lib` folder

Project uses `Spring Boot version 3.2.2` and `Spring Cloud Dependencies version 2023.0.0`

### Demo web application
Web shows simple page with selection of cities to show weather forecast. Result is represented as card with live weather data.
- `Location Selection` - dropdown menu allowing users to select a location for which corresponding weather information will be displayed
- `Weather Display` - section for displaying weather information fetched from the backend services. It displays the name, 
   time, weather icon, temperature, and "feels like" temperature. There's also a marker for indicating if the weather data is mocked.

The application can be accessed locally at `http://localhost:8080/forecast`

![screen-shot](/web-service/src/main/resources/static/img/readme/screen-1.png)


> **NOTE:** To ensure the proper experience using this demo, it is **highly recommended** to run modules in the order they are presented bellow

### Run Zipkin server
Open `zipkin-server` module.

- execute the `run.bat` script. This should start your Zipkin server packed as jar file under **/lib** folder.
- access at `http://localhost:9411`

### Run Discovery server
Open `eureka-server` module.

- build the project: `mvn clean install`
- run the server `java -jar target/eureka-server-1.0-SNAPSHOT.jar`
- access at `http://localhost:8761`

### Run Configuration server
Open `config-server` module.

- build the project: `mvn clean install`
- run the server `java -jar target/config-server-1.0-SNAPSHOT.jar`
- access at `http://localhost:8888/<config-name-under-configuration-store>/default`
  
  example `http://localhost:8888/location-service/default`

### Run Location service
Open `location-service` module.

- build the project: `mvn clean install`
- run the service `java -jar target/location-service-1.0-SNAPSHOT.jar`
- access at `http://localhost:8082/location`

The list of locations is flexible and can be altered. Navigate to the `configuration-storage` folder in the project's 
root directory and open file `location-service.yml`. Modify the items under the `locations` key in the YAML file, where
1. `id` unique identifier used to distinguish between different items in this list. Importantly, the 
   id is also used for ordering the locations. When the list of locations is retrieved, it's sorted in ascending 
   order based on the id.
2. `name` name of the location
3. `query` string value used when searching for data related to the specified location. 
   For instance, 'Dublin,ie' could be used as a search query in an API fetching data about Dublin in Ireland. 
   The 'ie' part is a country code representing Ireland.

### Run Weather service
Open `weather-service` module.

- build the project: `mvn clean install`
- run the service `java -jar target/weather-service-1.0-SNAPSHOT.jar -Dservice.weather.open-weather.api-key=<open-weather-api-key>`
- access at `http://localhost:8086/weather?query=<location-name>,<location-country-code>`

  example `http://localhost:8086/weather?query=Dublin,ie` 

where

`service.weather.open-weather.api-key` - is a key that stores the unique API key required to access data from the 
OpenWeatherMap API.

To generate and obtain this API key:
1. Visit the OpenWeatherMap website at https://openweathermap.org/
2. Create an account or log in if you already have an account
3. On successful login, navigate to the `My API Keys` menu in the account settings. 
4. You will see your unique API Key, or you can generate a new one if you prefer. This key is alphanumeric.

However, if `service.weather.open-weather.api-key` is not provided or invalid, the weather service has fail-safe 
mechanism implemented. It will fall back to use mock data for weather information. This mock data is stored in a file 
named `open-weather-mock.json`, which is part of application's resources. This file contains pre-set, static weather 
data.

### Run Gateway service
Open `gateway-service` module.

- build the project: `mvn clean install`
- run the service `java -jar target/gatyeway-service-1.0-SNAPSHOT.jar`
- access at `http://localhost:8086/<endpoit-name>`

examples `http://localhost:8086/weather?query=Dublin,ie` or `http://localhost:8086/location`

### Run Web service
Open `wev-service` module.

- build the project: `mvn clean install`
- run the service `java -jar target/web-service-1.0-SNAPSHOT.jar -Dservice.web.config=<configuration-type>`
- access at `http://localhost:8080/forecast`

where

`service.web.config` -  is used to specify the configuration mode of web service. There are three valid types:

1. `basic` this is a basic mode that uses MVC approach 
2. `feign` in this mode, HTTP requests are handled by Spring's Feign library, thereby enhancing the process of HTTP-based 
    microservices communication
3. `gateway` In this mode, service accesses an API Gateway

If `service.web.config` option is not clearly defined, or if the set value doesn't correlate with any of the recognized modes, 
the system automatically falls back to the `basic` mode.

### Links

#### Spring Cloud Family
https://spring.io/projects/spring-cloud

#### Zipkin 
https://zipkin.io/

#### Open Weather
https://openweathermap.org/
