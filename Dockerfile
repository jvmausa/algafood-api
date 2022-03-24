FROM openjdk:19-jdk-slim

WORKDIR /app

COPY target/*.jar /app/alga-food-api.jar

EXPOSE 8080

CMD ["java", "-jar", "alga-food-api.jar"]