# A CRUD application made with the Java Spring framework

The [Spring](https://spring.io/) Framework is an open-source application framework that provides infrastructure support for developing Java applications. It is one of the most popular Java Enterprise Edition (Java EE) frameworks. The purpose behind developing this basic CRUD-application was to learn the basics of the framework and get used to how things work in order to setup for more complex projects down the line.

## Installation

To be able to run the project both [Maven](https://maven.apache.org/what-is-maven.html) and [Java 11](https://jdk.java.net/java-se-ri/11) need to be installed on your computer, so make sure they are.

## Setting up and running the application

Clone the repo and cd into it.
```bash
> git clone <url>
> cd Congregentur
```

In order for the application to run, you will need to make a small configuration to the `application.properties` file located in `.\src\main\resources`. I am assuming that your PostgreSQL port is the default `5432`, change if you use something else. Change these three lines to have appropriate values:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/<postgreSQL_name_here>
spring.datasource.username=<postgreSQL_username_here>
spring.datasource.password=<postgreSQL_password_here>
```
For example if your PostgreSQL database is named `myDatabase` and 
your username is `myUsername` and your password is `myPassword`, you would change them to:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/myDatabase
spring.datasource.username=myUsername
spring.datasource.password=myPassword
```

After having configured the above, running the application is simply a matter of running the following command using Maven:
```bash
> mvn spring-boot:run
```

## Usage
The API supports the basic *create*, *read*, *update* and *remove* operations. To see the web page, simply enter the url `localhost:8080` in your browser.

![home-page](https://user-images.githubusercontent.com/85518265/147742352-2cb0fb6e-cda9-40f0-bbf9-3e476112f0ea.png)
