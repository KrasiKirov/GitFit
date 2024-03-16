package ca.mcgill.ecse321.gitfit.dto;

import java.sql.Date;

import ca.mcgill.ecse321.gitfit.model.Session;

public class RegistrationResponseDto {
    private int id;
    private Date date;
    private Session session;
    private CustomerAccountDto customer;

    public RegistrationResponseDto() {
    }

    public RegistrationResponseDto(int id, Date date, Session session, CustomerAccountDto customer) {
        this.id = id;
        this.date = date;
        this.session = session;
        this.customer = customer;
    }

    public int getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public Session getSession() {
        return this.session;
    }

    public CustomerAccountDto getCustomer() {
        return this.customer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setCustomer(CustomerAccountDto customer) {
        this.customer = customer;
    }

}
