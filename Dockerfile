FROM gradle:8.10.0-jdk21 AS build
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew .
ARG GRADLE_OPTS="-XX:MaxMetaspaceSize=384m -XX:+HeapDumpOnOutOfMemoryError -Xms256m -Xmx512m -Dfile.encoding=UTF-8 -Duser.country=DE -Duser.language=en -Duser.variant"
RUN ./gradlew clean build >/dev/null 2>&1 || true

COPY . .
RUN ./gradlew build

FROM eclipse-temurin:21
EXPOSE 8080
COPY --from=build /app/build/libs/scuba-0.0.1-SNAPSHOT.jar /app/
RUN bash -c 'touch /app/scuba-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar","/app/scuba-0.0.1-SNAPSHOT.jar", "-Dspring.cloud.kubernetes.secrets.paths=/etc/secrets"]
