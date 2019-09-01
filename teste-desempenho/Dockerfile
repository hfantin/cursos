#FROM adoptopenjdk/openjdk8-openj9:alpine-slim
FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY run-open9.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
