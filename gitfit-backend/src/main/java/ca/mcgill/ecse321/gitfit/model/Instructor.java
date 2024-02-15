package ca.mcgill.ecse321.gitfit.model;

public class Instructor extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Attributes
  private int instructorId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instructor(String aEmail, String aPassword, String aLastName, String aFirstName, int aInstructorId)
  {
    super(aEmail, aPassword, aLastName, aFirstName);
    instructorId = aInstructorId;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInstructorId(int aInstructorId)
  {
    boolean wasSet = false;
    instructorId = aInstructorId;
    wasSet = true;
    return wasSet;
  }

  public int getInstructorId()
  {
    return instructorId;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "instructorId" + ":" + getInstructorId()+ "]";
  }
}