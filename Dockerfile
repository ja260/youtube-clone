# ---- Build stage (Maven + Corretto) ----
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app

# Copy the Spring Boot project (inside youtubeclone/)
COPY youtubeclone/pom.xml .
COPY youtubeclone/.mvn .mvn
COPY youtubeclone/mvnw mvnw
COPY youtubeclone/src src
COPY youtubeclone/mvnw.cmd mvnw.cmd
COPY youtubeclone/src/main/resources src/main/resources

# Build
RUN mvn -DskipTests package

# ---- Run stage ----
FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
