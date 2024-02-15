/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/



// line 26 "model.ump"
// line 111 "model.ump"
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