# Start with base image containing java runtime
FROM openjdk:17-jdk-alpine AS build

# Information of owner
MAINTAINER vicheakbank

# Add the application's jar to the container
COPY target/card-1.0.0.jar card-1.0.0.jar

# Execute application
ENTRYPOINT ["java", "-jar", "/card-1.0.0.jar"] 