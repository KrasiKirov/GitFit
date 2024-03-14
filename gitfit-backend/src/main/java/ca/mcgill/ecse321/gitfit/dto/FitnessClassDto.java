package ca.mcgill.ecse321.gitfit.dto;

public class FitnessClassDto {
    private String name;
    private String description;
    private boolean isApproved;

    public FitnessClassDto() {
    }

    public FitnessClassDto(String name, String description, boolean isApproved) {
        this.name = name;
        this.description = description;
        this.isApproved = isApproved;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsApproved() {
        return isApproved;
    }
}
