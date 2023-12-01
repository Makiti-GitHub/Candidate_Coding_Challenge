FROM openjdk:17
EXPOSE 8100
ADD target/Licences-0.0.1-SNAPSHOT.jar Licences-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Licences-0.0.1-SNAPSHOT.jar"]
