FROM openjdk:17-jdk-alpine

COPY target/Matches-0.0.1-SNAPSHOT.jar matches_app_docker.jar

ENTRYPOINT [ "java", "-jar", "matches_app_docker.jar" ]