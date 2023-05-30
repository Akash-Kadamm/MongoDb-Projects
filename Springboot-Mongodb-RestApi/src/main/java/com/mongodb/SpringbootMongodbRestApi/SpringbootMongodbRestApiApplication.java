package com.mongodb.SpringbootMongodbRestApi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring boot Rest API",
                version = "2.0.0",
                description = "Employee management system",
                contact = @Contact(
                        email = "akashkad@gmail.com",
                        name = "Akash Kadam",
                        url = "http://localhost:8080/api/v1"
                ),
                license = @License(
                        name = "Apache License, Version 2.0",
                        url = "http://www.apache.org/licenses/"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "External",
                url = "http://www.apache.org"
        )
)
public class SpringbootMongodbRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbRestApiApplication.class, args);
    }

}
