package ca.mcgill.ecse321.gitfit.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Instructor extends Account {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int instructorId;

  public Instructor(String aEmail, String aPassword, String aLastName, String aFirstName, int aInstructorId) {
    super(aEmail, aPassword, aLastName, aFirstName);
    instructorId = aInstructorId;
  }

  public Instructor() {
    super();
  }

  public boolean setInstructorId(int aInstructorId) {
    boolean wasSet = false;
    instructorId = aInstructorId;
    wasSet = true;
    return wasSet;
  }

  public int getInstructorId() {
    return instructorId;
  }

  public void delete() {
    super.delete();
  }

  public String toString() {
    return super.toString() + "[" +
        "instructorId" + ":" + getInstructorId() + "]";
  }
}