FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/Backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_stopd.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_stopd.jar"]
