# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy everything
COPY . .

# Build jar (skip tests to be fast)
RUN mvn -q -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Render provides PORT env var. Spring should listen on it.
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
