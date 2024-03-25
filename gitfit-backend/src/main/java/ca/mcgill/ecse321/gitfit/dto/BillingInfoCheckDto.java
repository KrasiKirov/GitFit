package ca.mcgill.ecse321.gitfit.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BillingInfoCheckDto {
    @NotNull(message = "Country cannot be Null")
    private String country;

    @NotNull(message = "State cannot be Null")
    private String state;

    @NotNull(message = "Postal code cannot be Null")
    @Pattern(regexp = "^[A-Za-z0-9 ]{5,10}$", message = "Postal code must be between 5 and 10 alphanumeric characters")
    private String postalCode;

    @NotNull(message = "Card number cannot be Null")
    @Pattern(regexp = "^[0-9]{16}$", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotNull(message = "Address cannot be Null")
    private String address;

    public BillingInfoCheckDto(String country, String state, String postalCode, String cardNumber, String address) {
        this.country = country;
        this.state = state;
        this.postalCode = postalCode;
        this.cardNumber = cardNumber;
        this.address = address;
    }
}