FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/InvokeJsonPlaceholderRestSvc-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY application-dev.yaml application-dev.yaml
EXPOSE 8090
CMD java -Dspring.profiles.active=dev -Dserver.port=8090 -jar app.jar

