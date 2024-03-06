package ca.mcgill.ecse321.gitfit.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.ManyToOne;

@MappedSuperclass
public abstract class Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Account Attributes
  private String email;
  private String password;
  private String lastName;
  private String firstName;

  // Account Associations
  @ManyToOne(optional = false)
  private SportCenter sportCenter;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Account() {
  }

  public Account(String aEmail, String aPassword, String aLastName, String aFirstName, SportCenter aSportCenter) {
    email = aEmail;
    password = aPassword;
    lastName = aLastName;
    firstName = aFirstName;
    boolean didAddSportCenter = setSportCenter(aSportCenter);
    if (!didAddSportCenter) {
      throw new RuntimeException(
          "Unable to create account due to sportCenter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setEmail(String aEmail) {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword) {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName) {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setFirstName(String aFirstName) {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  /* Code from template association_GetOne */
  public SportCenter getSportCenter() {
    return sportCenter;
  }

  /* Code from template association_SetOneToMany */
  public boolean setSportCenter(SportCenter aSportCenter) {
    boolean wasSet = false;
    if (aSportCenter == null) {
      return wasSet;
    }

    SportCenter existingSportCenter = sportCenter;
    sportCenter = aSportCenter;
    if (existingSportCenter != null && !existingSportCenter.equals(aSportCenter)) {
      existingSportCenter.removeAccount(this);
    }
    sportCenter.addAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete() {
    SportCenter placeholderSportCenter = sportCenter;
    this.sportCenter = null;
    if (placeholderSportCenter != null) {
      placeholderSportCenter.removeAccount(this);
    }
  }

  public String toString() {
    return super.toString() + "[" +
        "email" + ":" + getEmail() + "," +
        "password" + ":" + getPassword() + "," +
        "lastName" + ":" + getLastName() + "," +
        "firstName" + ":" + getFirstName() + "]" + System.getProperties().getProperty("line.separator") +
        "  " + "sportCenter = "
        + (getSportCenter() != null ? Integer.toHexString(System.identityHashCode(getSportCenter())) : "null");
  }
}