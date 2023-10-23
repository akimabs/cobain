FROM alpine:latest

RUN apk update && apk add openjdk11-jre

WORKDIR /app

COPY target/cobain-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
