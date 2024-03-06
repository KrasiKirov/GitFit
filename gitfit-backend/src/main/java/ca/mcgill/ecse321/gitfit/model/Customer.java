package ca.mcgill.ecse321.gitfit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer extends Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Customer Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int customerId;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Customer(String aEmail, String aPassword, String aLastName, String aFirstName) {
    super(aEmail, aPassword, aLastName, aFirstName);
  }

  public Customer() {
    super();
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setCustomerId(int aCustomerId) {
    boolean wasSet = false;
    customerId = aCustomerId;
    wasSet = true;
    return wasSet;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void delete() {
    super.delete();
  }

  public String toString() {
    return super.toString() + "[" +
        "customerId" + ":" + getCustomerId() + "]";
  }
}