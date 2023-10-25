FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/cobain-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000

CMD ["java", "-jar", "app.jar"]
