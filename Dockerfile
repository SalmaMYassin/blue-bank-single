FROM openjdk:17-alpine
COPY target/bank-0.0.1-SNAPSHOT.jar bank-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/bank-0.0.1-SNAPSHOT.jar"]