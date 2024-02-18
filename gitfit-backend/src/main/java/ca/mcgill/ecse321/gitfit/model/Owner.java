package ca.mcgill.ecse321.gitfit.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Owner extends Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int ownerId;

  public Owner(String aEmail, String aPassword, String aLastName, String aFirstName, int aOwnerId) {
    super(aEmail, aPassword, aLastName, aFirstName);
    ownerId = aOwnerId;
  }

  public Owner() {
    super();
  }

  public boolean setOwnerId(int aOwnerId) {
    boolean wasSet = false;
    ownerId = aOwnerId;
    wasSet = true;
    return wasSet;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void delete() {
    super.delete();
  }

  public String toString() {
    return super.toString() + "[" +
        "ownerId" + ":" + getOwnerId() + "]";
  }
}