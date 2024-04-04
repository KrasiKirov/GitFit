package ca.mcgill.ecse321.gitfit.dto;

public class AccountLoginResponseDto {
    boolean success;

    public AccountLoginResponseDto(boolean status) {
        this.success = status;
    }

    public boolean getSuccess() {
        return this.success;
    }

}