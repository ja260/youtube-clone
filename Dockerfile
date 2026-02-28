# ---- Build stage ----
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app

# Copy entire project folder
COPY youtubeclone/ .

# Build jar
RUN mvn clean package -DskipTests

# ---- Run stage ----
FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
