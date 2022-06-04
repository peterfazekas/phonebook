# Phonebook
This is a demo application for how to dockerize application.

This is a Spring Boot based REST application written in Kotlin - a phone register stores person's first name, last name, and phone number.

## Modules

The application has 4 modules:
- `phonebook-dao`: defines the Data Access Layer for the database.
- `phonebook-service`: defines the business logic. A bridge between DAO and the WEB module.
- `phonebook-web`: contains the REST controllers.
- `phonebook-application`: contains the main method (the entry point for the application).

## Branches
The repo has 3 branches:
- `master`: contains the base phonebook application without dockerization. The application uses H2 database.
- `dockerize-1-h2`: creates a single docker image for the application. The application uses H2 database. 
- `dockerize-2-mysql`:  creates docker images for multiple services. The application uses MySQL database and persist the database.

## How to build the project

You can use maven command:
```text
mvn clean install
```

## Run the application

There are several ways to run the application
1. With IDE: you can use IDE build in run support. Find the `com.demo.phonebook.PhonebookApplication` class and run the `main` method with IDE.
2. Run with JDK: after bou built the application with maven, you cen run it with JDK:
```text
java -jar .\phonebook-application\target\phonebook-exec.jar
```

## Use the application
After you started the application you can find the `swagger-ui` in your favorite browser to reach REST endpoints:
```text
http://localhost:8080/swagger-ui.html
```

---
Switch to branch `dockerize-1-h2` or `dockerize-2-mysql` for updated README