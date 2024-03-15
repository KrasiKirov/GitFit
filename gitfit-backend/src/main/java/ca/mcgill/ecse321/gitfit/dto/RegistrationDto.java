package ca.mcgill.ecse321.gitfit.dto;

import java.sql.Date;

import ca.mcgill.ecse321.gitfit.model.Session;

public class RegistrationDto {
    private int id;
    private Date date;
    private Session session;
    private CustomerAccountDto customer;
    private SportCenterDto sportCenter;

    public RegistrationDto() {
    }

    public RegistrationDto(int id, Date date, Session session, CustomerAccountDto customer,
            SportCenterDto sportCenter) {
        this.id = id;
        this.date = date;
        this.session = session;
        this.customer = customer;
        this.sportCenter = sportCenter;
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

    public SportCenterDto getSportCenter() {
        return this.sportCenter;
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

    public void setSportCenter(SportCenterDto sportCenter) {
        this.sportCenter = sportCenter;
    }

}
