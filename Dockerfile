FROM amazoncorretto:11-alpine-jdk
MAINTAINER hernanSanchez
COPY target/portfolioback-0.0.1-SNAPSHOT.jar hsPortfolio.jar
ENTRYPOINT ["java ", "-jar", "/hsPortfolio.jar "]