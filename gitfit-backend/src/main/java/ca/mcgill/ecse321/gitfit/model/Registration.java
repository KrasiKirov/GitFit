package ca.mcgill.ecse321.gitfit.model;


import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private Date date;

  @ManyToOne(optional = false)
  private Session session;

  @ManyToOne(optional = false)
  private Customer customer;

  public Registration() {}

  public Registration(int aId, Date aDate, Session aSession, Customer aCustomer) {
    id = aId;
    date = aDate;
    if (!setSession(aSession))
    {
      throw new RuntimeException("Unable to create Registration due to aSession. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setCustomer(aCustomer))
    {
      throw new RuntimeException("Unable to create Registration due to aCustomer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public boolean setId(int aId) {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate) {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public Session getSession() {
    return session;
  }

  public Customer getCustomer() {
    return customer;
  }

  public boolean setSession(Session aNewSession) {
    boolean wasSet = false;
    if (aNewSession != null)
    {
      session = aNewSession;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean setCustomer(Customer aNewCustomer) {
    boolean wasSet = false;
    if (aNewCustomer != null)
    {
      customer = aNewCustomer;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete() {
    session = null;
    customer = null;
  }


  public String toString() {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "session = "+(getSession()!=null?Integer.toHexString(System.identityHashCode(getSession())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}