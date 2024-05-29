FROM eclipse-temurin:21 AS BUILDER

WORKDIR /app

COPY .mvn .mvn
COPY mvnw mvnw
COPY pom.xml pom.xml

RUN chmod +x mvnw && \
    ./mvnw -f pom.xml dependency:resolve-plugins dependency:resolve dependency:go-offline -B

COPY src src

RUN ./mvnw clean package '-Dtest=!**.infrastructure.**'

FROM eclipse-temurin:21-alpine AS RUNNER

WORKDIR /app

COPY --from=BUILDER /app/target/api-0.0.1-SNAPSHOT.jar api.jar

CMD ["java", "-jar", "api.jar"]