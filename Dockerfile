FROM eclipse-temurin:11.0.18_10-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM eclipse-temurin:11

#RUN mkdir -p logs/archived/
#VOLUME logs/archived/

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENV USERNAME "postgres"
ENV PASSWORD "mysecretpassword"
ENV URL "jdbc:postgresql://localhost:5432/postgres"
ENV SERVER_URI "http://localhost:8888"
ENV SPRING_PROFILE "local"

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.swagdiscovery.SwagArtistApplicationKt"]