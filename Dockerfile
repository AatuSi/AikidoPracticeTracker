FROM openjdk:17
WORKDIR /app
COPY target/aikido.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]