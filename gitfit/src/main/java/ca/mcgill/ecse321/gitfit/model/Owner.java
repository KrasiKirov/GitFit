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
}