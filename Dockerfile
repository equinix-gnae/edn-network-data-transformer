FROM openjdk:8-jdk
ADD target/network-data-transformer-1.0-SNAPSHOT.jar /app/network-data-transformer.jar
WORKDIR /app
CMD ["java","-jar","-Dspring.profiles.active=${ENV_VAR}","network-data-transformer.jar"]
