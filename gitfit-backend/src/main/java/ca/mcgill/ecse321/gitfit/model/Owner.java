package ca.mcgill.ecse321.gitfit.model;

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

  public Owner(String aEmail, String aPassword, String aLastName, String aFirstName, int aOwnerId)
  {
    super(aEmail, aPassword, aLastName, aFirstName);
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