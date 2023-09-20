FROM openjdk:17-alpin
WORKDIR /app
COPY ./target/person-api.jar  /app/person-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "person-api.jar"]
