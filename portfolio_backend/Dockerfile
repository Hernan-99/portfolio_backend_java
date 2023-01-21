FROM amazoncorreto:11-alpine-jdk
MAINTAINER hernanSanchez
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} hsPortfolio.jar
ENTRYPOINT ["java ", "-jar", "/hsPortfolio.jar "]