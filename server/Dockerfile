FROM openjdk:latest

WORKDIR /server
COPY ./target/server-0.0.1-SNAPSHOT.jar /server

EXPOSE 8761



CMD ["java", "-jar", "server-0.0.1-SNAPSHOT.jar"]