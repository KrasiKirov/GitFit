/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/



// line 21 "model.ump"
// line 111 "model.ump"
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