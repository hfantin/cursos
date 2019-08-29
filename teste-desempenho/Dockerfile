FROM adoptopenjdk/openjdk8-openj9:alpine-slim
#WORKDIR /opt/app
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
WORKDIR /opt/app
COPY build/libs/teste-desempenho-0.0.4-SNAPSHOT.jar app.jar
ADD run.sh run.sh
# RUN chmod +x run.sh
CMD ./run.sh
