FROM adoptopenjdk/openjdk11:jre-11.0.15_10-alpine

COPY phonebook-exec.jar phonebook.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "phonebook.jar"]