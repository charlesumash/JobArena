FROM openjdk:17-jdk
COPY target/jobarena-app.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jobarena-app.jar"]

