# Use official Maven image to build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
ENV MAVEN_OPTS="-Xmx350m"
RUN mvn clean package -DskipTests

# Use lightweight JRE image with memory caps for Render 512MB RAM
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/taskmanager-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx350m", "-Xms256m", "-XX:+UseSerialGC", "-jar", "app.jar"]
