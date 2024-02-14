## Description
This project is a demo for Spring Cloud, showcasing its set of features that make microservice development easier 
and more manageable. It aims to simulate a real-world application scenario, enabling users to better understand 
the principles of Spring Cloud and how it eases the challenges of building complex enterprise systems.

Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems. 
In this project, you will find examples of how to use these tools practically. The project 
encapsulates multiple sub-projects each demonstrating a unique feature of Spring Cloud - including distributed configuration, 
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
### demo web application
[Example Image](https://github.com/lacino40/spring-cloud-meetup/blob/main/readme/cloud.png)
