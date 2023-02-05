FROM alpine:latest 

RUN mkdir /app

COPY loggerApp /app

COPY .env /.env

CMD  [ "/app/loggerApp" ]