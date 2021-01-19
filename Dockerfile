FROM openjdk:15
ADD target/docker-lessonservice.jar docker-lessonservice.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-lessonservice.jar"]
