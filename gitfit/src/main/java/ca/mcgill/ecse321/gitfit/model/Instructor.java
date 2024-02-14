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

  public Instructor(String aEmail, String aPassword, String aLastName, String aFirstName, SportCenter aSportCenter, int aInstructorId)
  {
    super(aEmail, aPassword, aLastName, aFirstName, aSportCenter);
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
}