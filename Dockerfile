FROM amazoncorreto:11-alpine-jdk
MAINTAINER hernanSanchez
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} portfolioback-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java ", "-jar", "/hsPortfolio.jar "]