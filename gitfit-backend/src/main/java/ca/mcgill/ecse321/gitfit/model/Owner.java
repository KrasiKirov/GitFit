package ca.mcgill.ecse321.gitfit.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Owner extends Account {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Owner Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  // Owner Associations
  @ManyToOne(optional = false)
  private SportCenter sportCenter;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Owner() {
    super();
  }

  public Owner(String aUsername, String aEmail, String aPassword, String aLastName, String aFirstName,
      SportCenter aSportCenter) {
    super(aUsername, aEmail, aPassword, aLastName, aFirstName);
    boolean didAddSportCenter = setSportCenter(aSportCenter);
    if (!didAddSportCenter) {
      throw new RuntimeException(
          "Unable to create owner due to sportCenter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
      existingSportCenter.removeOwner(this);
    }
    sportCenter.addOwner(this);
    wasSet = true;
    return wasSet;
  }

  public void delete() {
    SportCenter placeholderSportCenter = sportCenter;
    this.sportCenter = null;
    if (placeholderSportCenter != null) {
      placeholderSportCenter.removeOwner(this);
    }
    super.delete();
  }

  public String toString() {
    return super.toString() + "[" +
        "id" + ":" + getId() + "]" + System.getProperties().getProperty("line.separator") +
        "  " + "sportCenter = "
        + (getSportCenter() != null ? Integer.toHexString(System.identityHashCode(getSportCenter())) : "null");
  }
}