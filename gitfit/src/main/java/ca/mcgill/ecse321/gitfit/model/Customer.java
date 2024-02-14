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

  public Customer(String aEmail, String aPassword, String aLastName, String aFirstName, SportCenter aSportCenter, int aCustomerId)
  {
    super(aEmail, aPassword, aLastName, aFirstName, aSportCenter);
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
}