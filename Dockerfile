# --- Stage 1: Build ---
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package


# --- Stage 2: Runtime ---
# Java JRE version (match with pom.xml Java target version compatibility)
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar bot.jar

ENTRYPOINT ["java", "-jar", "bot.jar"]
