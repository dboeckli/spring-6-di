# Spring 6 Application

## Overview
This project is a Spring Boot 3.4.1 web application.

## Prerequisites
- Java 21
- Maven 3.x

## Development
This project uses Spring Boot 3.4.1 and Java 21. It's configured with various plugins and dependencies to support:
- Actuator for monitoring and managing the application
- Logbook for HTTP request and response logging
- Docker image building and publishing
- Code coverage with JaCoCo
- Git commit information

## Building
- To build the project: `mvn clean install`
- To run tests: `mvn test`
- To build a Docker image: `mvn clean install` is creating a local image 

## CI/CD
The project includes a CI/CD profile for GitHub Actions. When activated, it enables Docker image publishing to GitHub Packages and Docker Hub.

## Logging
The application uses Logbook and Logstash for enhanced logging capabilities.
