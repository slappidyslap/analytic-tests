FROM gradle:8.13.0-jdk21-ubi-minimal AS gradle
COPY . .
RUN gradle bootJar
RUN ls

FROM eclipse-temurin:21-jdk-ubi9-minimal
RUN ls
COPY --from=gradle build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar"]