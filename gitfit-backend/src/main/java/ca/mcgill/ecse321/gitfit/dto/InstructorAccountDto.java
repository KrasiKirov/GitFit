package ca.mcgill.ecse321.gitfit.dto;

public class InstructorAccountDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public InstructorAccountDto() {
    }

    public InstructorAccountDto(String username, String email, String fistName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = fistName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}