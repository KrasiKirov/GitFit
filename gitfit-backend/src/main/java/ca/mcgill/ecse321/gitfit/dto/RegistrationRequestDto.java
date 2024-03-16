package ca.mcgill.ecse321.gitfit.dto;

import java.sql.Date;

public class RegistrationRequestDto {
    private Date date;
    private int sessionId;
    private String customerName;

    RegistrationRequestDto() {
    }

    RegistrationRequestDto(Date date, int sessionId, String customerName) {
        this.date = date;
        this.sessionId = sessionId;
        this.customerName = customerName;
    }

    public Date getDate() {
        return this.date;
    }

    public int getSessionId() {
        return this.sessionId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
