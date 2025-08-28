# Étape 1 : Construire l'application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests -e

# Étape 2 : Exécuter l'application
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/exam-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
CMD ["java", "-jar", "app.jar"]