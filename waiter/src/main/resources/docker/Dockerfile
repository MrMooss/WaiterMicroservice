FROM openjdk:17

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

RUN mkdir -p /opt
ADD maven/opt/orderTable.jar /opt/orderTable.jar


EXPOSE 8080

ENTRYPOINT java -jar /opt/orderTable.jar -Djava.net.preferIPv4Stack=true