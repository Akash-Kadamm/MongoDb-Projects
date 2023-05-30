package com.mongodb.SpringbootMongodbRestApi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Employee Model Information")
public class Employee {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    @NotBlank(message = "User First name should not be empty or null")
    @Size(max = 100)
    @Indexed(unique = true)
    @Schema(description = "User first name")
    private String firstName;

    @NotEmpty(message = "User Last name should not be empty or null")
    @Schema(description = "User last name")
    private String lastName;

    @NotBlank(message = "User Email should not be empty or null")
    @Size(max = 100)
    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    @Schema(description = "User email address")
    private String emailId;

}