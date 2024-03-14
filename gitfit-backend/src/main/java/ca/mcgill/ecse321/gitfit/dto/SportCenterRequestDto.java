package ca.mcgill.ecse321.gitfit.dto;

import java.sql.Time;

public class SportCenterRequestDto {
    private String name;
    private int maxCapacity;
    private Time openingTime;
    private Time closingTime;

    public SportCenterRequestDto(String name, int maxCapacity, Time openingTime, Time closingTime) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public Time getClosingTime() {
        return closingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }
}