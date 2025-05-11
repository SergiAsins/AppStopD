FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/backend_stopd-0.0.1.jar
COPY ${JAR_FILE} app_stopd.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_stopd.jar"]
