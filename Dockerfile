FROM maven:3-openjdk-17-slim AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/fpolycom-0.0.1-SNAPSHOT.jar fpolycom-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","fpolycom-0.0.1-SNAPSHOT.jar"]