FROM gradle:8.13.0-jdk21-ubi-minimal AS gradle
COPY . .
RUN gradle bootJar
RUN if ! command -v tree >/dev/null 2>&1; then microdnf install -y tree; fi
RUN tree

FROM eclipse-temurin:21-jdk-ubi9-minimal
RUN if ! command -v tree >/dev/null 2>&1; then microdnf install -y tree; fi
RUN tree
COPY --from=gradle build/libs/*.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","app.jar"]