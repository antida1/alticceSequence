FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
COPY out/artifacts/sequence_jar/sequence.jar sequence.jar
ENTRYPOINT ["java","-jar","/sequence.jar"]
