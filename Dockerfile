FROM openjdk:19-jdk-slim

WORKDIR /app

ARG  JAR_FILE

COPY target/${JAR_FILE} /app/alga-food-api.jar

EXPOSE 8080

CMD ["java", "-jar", "alga-food-api.jar"]