# Java JRE version (match with pom.xml Java target version compatibility)
FROM openjdk:21

WORKDIR /app
COPY target/bot.jar /app/

ENTRYPOINT ["java", "-jar", "/app/bot.jar"]
