FROM openjdk:17
LABEL authors="MSIN"
WORKDIR app
COPY target/*.jar app/app.jar
COPY resources ./resources
ENTRYPOINT ["java", "-jar", "app/app.jar", "--spring.profiles.active=prod"]