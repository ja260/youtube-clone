# ---- Build stage (JDK only) ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy project
COPY . .

# Make mvnw executable (linux)
RUN chmod +x mvnw

# Build using Maven Wrapper (more stable)
RUN ./mvnw -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
