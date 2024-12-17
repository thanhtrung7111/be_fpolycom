FROM jelastic/maven:3.9.5-openjdk-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/target/fpolycom-0.0.1-SNAPSHOT.jar fpolycom-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","fpolycom-0.0.1-SNAPSHOT.jar"]