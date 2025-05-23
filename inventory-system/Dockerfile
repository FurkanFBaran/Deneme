FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/inventory-system-0.0.1-SNAPSHOT.jar inventory-system.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "inventory-system.jar"]