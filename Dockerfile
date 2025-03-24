FROM eclipse-temurin:21-jdk-ubi9-minimal
COPY . .
EXPOSE 80
ENTRYPOINT ["./gradlew","bootRun"]
