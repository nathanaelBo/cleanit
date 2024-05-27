FROM maven:3.9.6-eclipse-temurin-21-alpine as build

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml first to leverage Docker cache
COPY pom.xml .

# Download the dependencies based on the pom.xml file
RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY src ./src

# Package the application
RUN mvn package -DskipTests

FROM openjdk:21-jdk-slim as runner
COPY --from=build /app/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]