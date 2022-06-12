FROM openjdk:17
EXPOSE 8080
ADD target/autoparts-app.jar autoparts-app.jar
ENTRYPOINT ["java", "-jar","/autoparts-app.jar"]