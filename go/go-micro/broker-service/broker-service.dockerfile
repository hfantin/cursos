FROM alpine:latest 

RUN mkdir /app

COPY brokerApp /app

COPY .env /.env

CMD  [ "/app/brokerApp" ]