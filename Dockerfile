FROM eclipse-temurin:17-jre
LABEL authors="CHRISTIAN CUBAS J"
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--server.address=0.0.0.0"]