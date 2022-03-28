FROM openjdk:19-jdk-slim

WORKDIR /app

ARG  JAR_FILE

COPY target/${JAR_FILE} /app/alga-food-api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "alga-food-api.jar"]