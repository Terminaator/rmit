FROM eclipse-temurin:21 AS BUILDER
RUN apt-get update && apt-get install -y dos2unix

WORKDIR /app

COPY ./.mvn ./.mvn
COPY ./mvnw ./mvnw
COPY ./pom.xml ./pom.xml

 # Converting the mvnw line endings during build (if you don’t change line endings of the mvnw file)
RUN dos2unix ./mvnw
RUN dos2unix ./.mvn/wrapper/maven-wrapper.properties

RUN chmod +x ./mvnw && \
    ./mvnw -f pom.xml dependency:resolve-plugins dependency:resolve dependency:go-offline -B

COPY src src

RUN ./mvnw clean package '-Dtest=!**.infrastructure.**'

FROM eclipse-temurin:21-alpine AS RUNNER

WORKDIR /app

COPY --from=BUILDER /app/target/api-0.0.1-SNAPSHOT.jar api.jar

CMD ["java", "-jar", "api.jar"]