package ca.mcgill.ecse321.gitfit.model;

public class Customer extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private int customerId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aEmail, String aPassword, String aLastName, String aFirstName, int aCustomerId)
  {
    super(aEmail, aPassword, aLastName, aFirstName);
    customerId = aCustomerId;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCustomerId(int aCustomerId)
  {
    boolean wasSet = false;
    customerId = aCustomerId;
    wasSet = true;
    return wasSet;
  }

  public int getCustomerId()
  {
    return customerId;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "customerId" + ":" + getCustomerId()+ "]";
  }
}