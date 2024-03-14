package ca.mcgill.ecse321.gitfit.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountCreationDto {
    @NotNull(message = "Username cannot be null")
    @Size(min = 5, message = "Username must be at least 5 characters long")
    private String username;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = ".+@.+\\.com$", message = "Email must end with @ (domain) .com")
    private String email;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String LastName;
}