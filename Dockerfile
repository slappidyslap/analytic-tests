FROM gradle:8.13.0-jdk21 AS gradle
COPY . .
RUN gradle bootJar

FROM eclipse-temurin:21-jdk-ubi9-minimal
COPY --from=gradle build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar"]