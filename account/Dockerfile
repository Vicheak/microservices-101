# Start with base image containing java runtime
FROM openjdk:17-jdk-alpine AS build

# Information of owner
MAINTAINER vicheakbank

# Add the application's jar to the container
COPY target/account-1.0.0.jar account-1.0.0.jar

# Execute application
ENTRYPOINT ["java", "-jar", "/account-1.0.0.jar"] 