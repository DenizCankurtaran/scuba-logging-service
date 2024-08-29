FROM gradle:8.10.0-jdk21 AS build
COPY --chown=gradlew:gradlew . /src
WORKDIR /src
RUN ./gradlew build

FROM eclipse-temurin:21
EXPOSE 8080
COPY --from=build /src/build/libs/scuba-0.0.1-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/scuba-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar","/app/scuba-0.0.1-SNAPSHOT.jar"]