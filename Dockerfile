FROM openjdk:8u212-b04-jdk-slim-stretch

ADD /target/lotteryapi-0.0.1-SNAPSHOT.jar lotteryapi-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "lotteryapi-0.0.1-SNAPSHOT.jar"]