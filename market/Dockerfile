# BUILD STAGE
FROM maven:3.8.3-openjdk-16 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

# RUN STAGE
FROM adoptopenjdk/openjdk16:alpine-jre

WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/market-0.0.1-SNAPSHOT.jar /opt/app/

ENV spring_profiles_active=docker

ENTRYPOINT ["java","-jar", "market-0.0.1-SNAPSHOT.jar"]