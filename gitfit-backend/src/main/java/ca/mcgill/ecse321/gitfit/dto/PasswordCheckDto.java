package ca.mcgill.ecse321.gitfit.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PasswordCheckDto {
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message = "Password must contain at least one digit, one lowercase letter, and one uppercase letter")
    private String password;

    public PasswordCheckDto(String password) {
        this.password = password;
    }
}