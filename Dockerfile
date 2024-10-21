FROM maven:3.8.6-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM registry.access.redhat.com/ubi8/openjdk-11:1.20-2.1721172731
WORKDIR /app
COPY . .
COPY --from=build /app/target/api-0.0.1.jar app.jar

EXPOSE 8080:8080

ENTRYPOINT ["java", "-jar", "target/api-0.0.1.jar"]