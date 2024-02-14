/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/



// line 59 "model.ump"
// line 99 "model.ump"
public class Billing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Billing Attributes
  private String country;
  private String state;
  private String postalCode;
  private String cardNumber;
  private String address;

  //Billing Associations
  private Customer customer;
  private SportCenter sportCenter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Billing(String aCountry, String aState, String aPostalCode, String aCardNumber, String aAddress, Customer aCustomer, SportCenter aSportCenter)
  {
    country = aCountry;
    state = aState;
    postalCode = aPostalCode;
    cardNumber = aCardNumber;
    address = aAddress;
    if (!setCustomer(aCustomer))
    {
      throw new RuntimeException("Unable to create Billing due to aCustomer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSportCenter = setSportCenter(aSportCenter);
    if (!didAddSportCenter)
    {
      throw new RuntimeException("Unable to create billing due to sportCenter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCountry(String aCountry)
  {
    boolean wasSet = false;
    country = aCountry;
    wasSet = true;
    return wasSet;
  }

  public boolean setState(String aState)
  {
    boolean wasSet = false;
    state = aState;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setCardNumber(String aCardNumber)
  {
    boolean wasSet = false;
    cardNumber = aCardNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getCountry()
  {
    return country;
  }

  public String getState()
  {
    return state;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getCardNumber()
  {
    return cardNumber;
  }

  public String getAddress()
  {
    return address;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public SportCenter getSportCenter()
  {
    return sportCenter;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer != null)
    {
      customer = aNewCustomer;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSportCenter(SportCenter aSportCenter)
  {
    boolean wasSet = false;
    if (aSportCenter == null)
    {
      return wasSet;
    }

    SportCenter existingSportCenter = sportCenter;
    sportCenter = aSportCenter;
    if (existingSportCenter != null && !existingSportCenter.equals(aSportCenter))
    {
      existingSportCenter.removeBilling(this);
    }
    sportCenter.addBilling(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    customer = null;
    SportCenter placeholderSportCenter = sportCenter;
    this.sportCenter = null;
    if(placeholderSportCenter != null)
    {
      placeholderSportCenter.removeBilling(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "country" + ":" + getCountry()+ "," +
            "state" + ":" + getState()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "cardNumber" + ":" + getCardNumber()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "sportCenter = "+(getSportCenter()!=null?Integer.toHexString(System.identityHashCode(getSportCenter())):"null");
  }
}