/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/



// line 26 "model.ump"
// line 116 "model.ump"
public class Owner extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Owner Attributes
  private int ownerId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Owner(String aEmail, String aPassword, String aLastName, String aFirstName, SportCenter aSportCenter, int aOwnerId)
  {
    super(aEmail, aPassword, aLastName, aFirstName, aSportCenter);
    ownerId = aOwnerId;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOwnerId(int aOwnerId)
  {
    boolean wasSet = false;
    ownerId = aOwnerId;
    wasSet = true;
    return wasSet;
  }

  public int getOwnerId()
  {
    return ownerId;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "ownerId" + ":" + getOwnerId()+ "]";
  }
}