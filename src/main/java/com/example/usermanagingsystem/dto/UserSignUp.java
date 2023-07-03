package com.example.usermanagingsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUp {

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank(message = "email should not be blank")
    @Email
    private String email;

    @NotNull(message = "password property must be present")
    @Size(min=12)
    private String password;
}
