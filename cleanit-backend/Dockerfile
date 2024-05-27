FROM openjdk:21-jdk-slim as build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml first to leverage Docker cache
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Download the dependencies based on the pom.xml file
RUN ./mvnw dependency:go-offline

# Copy the rest of the project files
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

FROM openjdk:21-jdk-slim as runner
COPY target/*.jar app.jar
COPY --from=build /app/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]