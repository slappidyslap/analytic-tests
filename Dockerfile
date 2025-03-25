FROM gradle:8.13.0-jdk21-ubi-minimal AS gradle
COPY . /analytics-tests
WORKDIR /analytics-tests
RUN gradle bootJar

FROM eclipse-temurin:21-jdk-ubi9-minimal
COPY --from=gradle /analytics-tests/build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=stage"]