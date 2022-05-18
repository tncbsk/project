FROM openjdk:11
VOLUME /main-app
COPY target/readingIsGood-0.0.1-SNAPSHOT.jar readingisgood.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "readingisgood.jar"]