FROM openjdk:8

EXPOSE 8080
ADD /target/bank-0.0.1-SNAPSHOT.jar  bank.jar
ENTRYPOINT ["java","-jar","bank.jar"]
