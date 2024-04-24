FROM openjdk:17-jdk
COPY target/jobarena.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jobarena.jar"]

