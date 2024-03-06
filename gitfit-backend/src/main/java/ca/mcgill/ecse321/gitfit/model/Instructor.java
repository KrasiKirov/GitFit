package ca.mcgill.ecse321.gitfit.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Instructor extends Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Instructor Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Instructor() {
    super();
  }

  public Instructor(String aEmail, String aPassword, String aLastName, String aFirstName, SportCenter aSportCenter) {
    super(aEmail, aPassword, aLastName, aFirstName, aSportCenter);
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setId(int aId) {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public int getId() {
    return id;
  }

  public void delete() {
    super.delete();
  }

  public String toString() {
    return super.toString() + "[" +
        "id" + ":" + getId() + "]";
  }
}