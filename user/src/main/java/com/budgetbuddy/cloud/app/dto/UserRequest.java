package com.budgetbuddy.cloud.app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty(message = "userName is required and should not be empty")
    private String userName;

    @Email(message = "Email should be in valid format")
    @NotEmpty(message = "Email should not be empty")
    private String email;

}
