FROM alpine:latest 

RUN mkdir /app

COPY authApp /app

COPY .env /.env

CMD  [ "/app/authApp" ]