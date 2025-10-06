FROM openjdk:21-jdk-slim

# Создаём пользователя для безопасности
RUN groupadd -r spring && useradd -r -g spring spring
USER spring

WORKDIR /app

# Копируем JAR-файл
COPY target/Language-0.0.1-SNAPSHOT.jar app.jar

# Переменные окружения
ENV SPRING_PROFILES_ACTIVE=prod
ENV ADMIN_PASSWORD=""

# Открываем порт
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]