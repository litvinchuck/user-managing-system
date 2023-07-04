# User Managing System

## Installation

### Prerequisites 

* JDK (>= 17)
* PostgreSQL

### Process

1. Clone this repository to your local machine
2. Edit project configuration
3. Build the project
4. Run the project 

## Editing Project Configuration

All the project configuration is stored in the `src/main/resources/application.properties` file.

It will look something like this:

```properties
server.port=
server.error.include-message=always

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=create-drop

# JPA Configuration
spring.jpa.show-sql=true

# PostgresSQL Configuration
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:32768/postgres
spring.datasource.username=postgres
spring.datasource.password=postgrespw
```

You will need to set the server port on which the application will run, postgres server url, 
postgres username and password.

To change the server port, you will have to edit this line in the `src/main/resources/application.properties` file:

```properties
server.port=8881
```

Where you will set the port instead of `8881`. By default, the application runs on port `8080`.

To change postgres configuration you will need to alter these lines:

```properties
spring.datasource.url=jdbc:postgresql://url:port/database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Where you will need to set values instead of these:

* `url` - the url of your postgres server
* `port` - the port of your postgres server
* `your_username` - the username for you postgres server
* `your_password` - the password for you postgres server

## Building Project

To build the project, navigate to the project folder and open terminal there.

If you're using Linux or macOS, run:

```shell
./gradlew build
```

If you're using Windows run:
```shell
.\gradlew build
```

## Running Project

To run the project, navigate to the project folder and open terminal there.

If you're using Linux or macOS, run:
```shell
java -jar "build/libs/user-managing-system -0.0.1-SNAPSHOT.jar"
```

If you're using Windows run:
```shell
java -jar "build\libs\user-managing-system -0.0.1-SNAPSHOT.jar"
```

Wait a couple of seconds and you should be able to connect to the website. By default, the link points [here](http://localhost:8080/).

## Project Structure

```
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── user_managing_system
    │   │               ├── config                                  # Java Configuration Files
    │   │               ├── controllers                             # View Controllers
    │   │               ├── dto                                     # Model DTOs
    │   │               ├── exceptions                              # Custom Exceptions
    │   │               ├── models                                  # ORM Models
    │   │               ├── repositories                            # JPA Repositories
    │   │               ├── services                                # Service Classes
    │   │               └── UserManagingSystemApplication.java      # Main Application Executable
    │   └── resources
    │       ├── application.properties                                    # Application properties file
    │       ├── static
    │       │   └── css                                             # CSS files
    │       └── templates                                                 # Thymeleaf templates folder

```

## Technologies Used

* Spring Boot
* Spring Security
* Spring Validation
* Thymeleaf
* modelmapper
* lombok
* postgresql