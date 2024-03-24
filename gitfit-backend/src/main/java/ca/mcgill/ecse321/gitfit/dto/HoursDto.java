package ca.mcgill.ecse321.gitfit.dto;

import java.sql.Time;
import java.time.LocalTime;

public class HoursDto {
    private LocalTime openingTime;
    private LocalTime closingTime;

    public HoursDto(Time openingTime, Time closingTime) {
        this.openingTime = openingTime.toLocalTime();
        this.closingTime = closingTime.toLocalTime();
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
}
