FROM openjdk:8-jdk-alpine
EXPOSE 5500
ADD build/libs/diplom_cloud_service-0.0.1-SNAPSHOT.jar cloudservice.jar
ENTRYPOINT ["java", "-jar", "cloudservice.jar"]