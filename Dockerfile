#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /target/spring-boot-firebase.jar spring-boot-firebase.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-firebase.jar"]