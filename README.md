# Spring Boot + Spring Data JPA + MySQL example

## Technologies used:
* Spring Boot 3.1.2
* Spring Data JPA (Hibernate 6 is the default JPA implementation)
* MySQL 8
* Java 17
* Maven 3
* JUnit 5
* Spring Test using TestRestTemplate
* Docker, [Testcontainers](https://testcontainers.com/) (for Spring integration tests using a MySQL container)

## How to run it
```

$ git clone https://github.com/sMhamed/tachy-is.git


# Skip test, the Testcontainers takes time
$ ./mvnw clean package -Dmaven.test.skip=true

$ ./mvnw spring-boot:run

```


## DB Schema 

![mydb.png](..%2F..%2Fmydb.png)

