FROM eclipse-temurin:21-jdk-ubi9-minimal
COPY . .
EXPOSE 80
RUN ./gradlew bootJar
RUN tree
RUN java -jar ./build/libs/*.jar

