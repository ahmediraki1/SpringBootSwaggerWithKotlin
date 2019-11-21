FROM java:8-jdk-alpine

COPY ./target/swagger-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

EXPOSE 8080

RUN sh -c 'touch swagger-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","swagger-0.0.1-SNAPSHOT.jar"]