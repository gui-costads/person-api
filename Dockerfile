FROM openjdk:17
ADD ./person-api-spring.jar person-api-spring.jar
ENTRYPOINT ["java", "-jar", "person-api-spring.jar"]