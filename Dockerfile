FROM eclipse-temurin:21-jdk-alpine

ARG JAR_FILE=build/libs/events-gateway*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

COPY testing/tls.crt /usr/local/share/ca-certificates/custom-ca.crt

RUN apk add --no-cache curl ca-certificates && \
    update-ca-certificates && \
    keytool -importcert -noprompt \
            -alias custom-ca \
            -file /usr/local/share/ca-certificates/custom-ca.crt \
            -keystore $JAVA_HOME/lib/security/cacerts \
            -storepass changeit

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
