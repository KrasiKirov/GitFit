package ca.mcgill.ecse321.gitfit.model;
import jakarta.persistence.*;
@Entity
public class Billing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Billing Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String country;
  private String state;
  private String postalCode;
  private String cardNumber;
  private String address;

  //Billing Associations
  @OneToOne(optional = false)
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Billing(String aCountry, String aState, String aPostalCode, String aCardNumber, String aAddress, Customer aCustomer)
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
  }
  public Billing() {}
  //------------------------
  // INTERFACE
  //------------------------

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public void delete()
  {
    customer = null;
  }

//
//  public String toString()
//  {
//    return super.toString() + "["+
//            "country" + ":" + getCountry()+ "," +
//            "state" + ":" + getState()+ "," +
//            "postalCode" + ":" + getPostalCode()+ "," +
//            "cardNumber" + ":" + getCardNumber()+ "," +
//            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
//            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
//  }
}