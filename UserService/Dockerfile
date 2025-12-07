FROM ubuntu:latest
LABEL authors="K KEVIN GLADSON"

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    && apt-get clean

WORKDIR /app

COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5020

CMD ["java", "-jar", "app.jar"]
