FROM openjdk:20-jdk-slim

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring

WORKDIR /app
COPY target/*.jar app.jar

EXPOSE 8080

# Простой ENTRYPOINT без параметров
ENTRYPOINT ["java", "-jar", "/app/app.jar"]