/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Time;
import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 70 "model.ump"
public class SportCenter
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SportCenter Attributes
  private int id;
  private int maxCapacity;
  private Time openingTime;
  private Time closingTime;

  //SportCenter Associations
  private List<Account> accounts;
  private List<Registration> registrations;
  private List<Session> sessions;
  private List<Class> classes;
  private List<Billing> billings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SportCenter(int aId, int aMaxCapacity, Time aOpeningTime, Time aClosingTime)
  {
    id = aId;
    maxCapacity = aMaxCapacity;
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
    accounts = new ArrayList<Account>();
    registrations = new ArrayList<Registration>();
    sessions = new ArrayList<Session>();
    classes = new ArrayList<Class>();
    billings = new ArrayList<Billing>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxCapacity(int aMaxCapacity)
  {
    boolean wasSet = false;
    maxCapacity = aMaxCapacity;
    wasSet = true;
    return wasSet;
  }

  public boolean setOpeningTime(Time aOpeningTime)
  {
    boolean wasSet = false;
    openingTime = aOpeningTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosingTime(Time aClosingTime)
  {
    boolean wasSet = false;
    closingTime = aClosingTime;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public int getMaxCapacity()
  {
    return maxCapacity;
  }

  public Time getOpeningTime()
  {
    return openingTime;
  }

  public Time getClosingTime()
  {
    return closingTime;
  }
  /* Code from template association_GetMany */
  public Account getAccount(int index)
  {
    Account aAccount = accounts.get(index);
    return aAccount;
  }

  public List<Account> getAccounts()
  {
    List<Account> newAccounts = Collections.unmodifiableList(accounts);
    return newAccounts;
  }

  public int numberOfAccounts()
  {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts()
  {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(Account aAccount)
  {
    int index = accounts.indexOf(aAccount);
    return index;
  }
  /* Code from template association_GetMany */
  public Registration getRegistration(int index)
  {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations()
  {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations()
  {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registrations.indexOf(aRegistration);
    return index;
  }
  /* Code from template association_GetMany */
  public Session getSession(int index)
  {
    Session aSession = sessions.get(index);
    return aSession;
  }

  public List<Session> getSessions()
  {
    List<Session> newSessions = Collections.unmodifiableList(sessions);
    return newSessions;
  }

  public int numberOfSessions()
  {
    int number = sessions.size();
    return number;
  }

  public boolean hasSessions()
  {
    boolean has = sessions.size() > 0;
    return has;
  }

  public int indexOfSession(Session aSession)
  {
    int index = sessions.indexOf(aSession);
    return index;
  }
  /* Code from template association_GetMany */
  public Class getClass(int index)
  {
    Class aClass = classes.get(index);
    return aClass;
  }

  public List<Class> getClasses()
  {
    List<Class> newClasses = Collections.unmodifiableList(classes);
    return newClasses;
  }

  public int numberOfClasses()
  {
    int number = classes.size();
    return number;
  }

  public boolean hasClasses()
  {
    boolean has = classes.size() > 0;
    return has;
  }

  public int indexOfClass(Class aClass)
  {
    int index = classes.indexOf(aClass);
    return index;
  }
  /* Code from template association_GetMany */
  public Billing getBilling(int index)
  {
    Billing aBilling = billings.get(index);
    return aBilling;
  }

  public List<Billing> getBillings()
  {
    List<Billing> newBillings = Collections.unmodifiableList(billings);
    return newBillings;
  }

  public int numberOfBillings()
  {
    int number = billings.size();
    return number;
  }

  public boolean hasBillings()
  {
    boolean has = billings.size() > 0;
    return has;
  }

  public int indexOfBilling(Billing aBilling)
  {
    int index = billings.indexOf(aBilling);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Account addAccount(String aEmail, String aPassword, String aLastName, String aFirstName)
  {
    return new Account(aEmail, aPassword, aLastName, aFirstName, this);
  }

  public boolean addAccount(Account aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    SportCenter existingSportCenter = aAccount.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter)
    {
      aAccount.setSportCenter(this);
    }
    else
    {
      accounts.add(aAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAccount(Account aAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aAccount, as it must always have a sportCenter
    if (!this.equals(aAccount.getSportCenter()))
    {
      accounts.remove(aAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAccountAt(Account aAccount, int index)
  {  
    boolean wasAdded = false;
    if(addAccount(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(Account aAccount, int index)
  {
    boolean wasAdded = false;
    if(accounts.contains(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Registration addRegistration(int aId, Date aDate, Session aSession, Customer aCustomer)
  {
    return new Registration(aId, aDate, aSession, aCustomer, this);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    SportCenter existingSportCenter = aRegistration.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter)
    {
      aRegistration.setSportCenter(this);
    }
    else
    {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistration, as it must always have a sportCenter
    if (!this.equals(aRegistration.getSportCenter()))
    {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRegistrationAt(Registration aRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addRegistration(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registrations.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSessions()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Session addSession(int aId, int aPrice, Time aEndTime, Time aStartTime, Date aDate, Instructor aInstructor, Class aClass)
  {
    return new Session(aId, aPrice, aEndTime, aStartTime, aDate, aInstructor, aClass, this);
  }

  public boolean addSession(Session aSession)
  {
    boolean wasAdded = false;
    if (sessions.contains(aSession)) { return false; }
    SportCenter existingSportCenter = aSession.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter)
    {
      aSession.setSportCenter(this);
    }
    else
    {
      sessions.add(aSession);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSession(Session aSession)
  {
    boolean wasRemoved = false;
    //Unable to remove aSession, as it must always have a sportCenter
    if (!this.equals(aSession.getSportCenter()))
    {
      sessions.remove(aSession);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSessionAt(Session aSession, int index)
  {  
    boolean wasAdded = false;
    if(addSession(aSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSessions()) { index = numberOfSessions() - 1; }
      sessions.remove(aSession);
      sessions.add(index, aSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSessionAt(Session aSession, int index)
  {
    boolean wasAdded = false;
    if(sessions.contains(aSession))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSessions()) { index = numberOfSessions() - 1; }
      sessions.remove(aSession);
      sessions.add(index, aSession);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSessionAt(aSession, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClasses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Class addClass(int aId, String aName, String aDescription)
  {
    return new Class(aId, aName, aDescription, this);
  }

  public boolean addClass(Class aClass)
  {
    boolean wasAdded = false;
    if (classes.contains(aClass)) { return false; }
    SportCenter existingSportCenter = aClass.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter)
    {
      aClass.setSportCenter(this);
    }
    else
    {
      classes.add(aClass);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClass(Class aClass)
  {
    boolean wasRemoved = false;
    //Unable to remove aClass, as it must always have a sportCenter
    if (!this.equals(aClass.getSportCenter()))
    {
      classes.remove(aClass);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClassAt(Class aClass, int index)
  {  
    boolean wasAdded = false;
    if(addClass(aClass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClasses()) { index = numberOfClasses() - 1; }
      classes.remove(aClass);
      classes.add(index, aClass);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClassAt(Class aClass, int index)
  {
    boolean wasAdded = false;
    if(classes.contains(aClass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClasses()) { index = numberOfClasses() - 1; }
      classes.remove(aClass);
      classes.add(index, aClass);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClassAt(aClass, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBillings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Billing addBilling(String aCountry, String aState, String aPostalCode, String aCardNumber, String aAddress, Customer aCustomer)
  {
    return new Billing(aCountry, aState, aPostalCode, aCardNumber, aAddress, aCustomer, this);
  }

  public boolean addBilling(Billing aBilling)
  {
    boolean wasAdded = false;
    if (billings.contains(aBilling)) { return false; }
    SportCenter existingSportCenter = aBilling.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter)
    {
      aBilling.setSportCenter(this);
    }
    else
    {
      billings.add(aBilling);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBilling(Billing aBilling)
  {
    boolean wasRemoved = false;
    //Unable to remove aBilling, as it must always have a sportCenter
    if (!this.equals(aBilling.getSportCenter()))
    {
      billings.remove(aBilling);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBillingAt(Billing aBilling, int index)
  {  
    boolean wasAdded = false;
    if(addBilling(aBilling))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBillings()) { index = numberOfBillings() - 1; }
      billings.remove(aBilling);
      billings.add(index, aBilling);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillingAt(Billing aBilling, int index)
  {
    boolean wasAdded = false;
    if(billings.contains(aBilling))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBillings()) { index = numberOfBillings() - 1; }
      billings.remove(aBilling);
      billings.add(index, aBilling);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillingAt(aBilling, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (accounts.size() > 0)
    {
      Account aAccount = accounts.get(accounts.size() - 1);
      aAccount.delete();
      accounts.remove(aAccount);
    }
    
    while (registrations.size() > 0)
    {
      Registration aRegistration = registrations.get(registrations.size() - 1);
      aRegistration.delete();
      registrations.remove(aRegistration);
    }
    
    while (sessions.size() > 0)
    {
      Session aSession = sessions.get(sessions.size() - 1);
      aSession.delete();
      sessions.remove(aSession);
    }
    
    while (classes.size() > 0)
    {
      Class aClass = classes.get(classes.size() - 1);
      aClass.delete();
      classes.remove(aClass);
    }
    
    while (billings.size() > 0)
    {
      Billing aBilling = billings.get(billings.size() - 1);
      aBilling.delete();
      billings.remove(aBilling);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "maxCapacity" + ":" + getMaxCapacity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "openingTime" + "=" + (getOpeningTime() != null ? !getOpeningTime().equals(this)  ? getOpeningTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "closingTime" + "=" + (getClosingTime() != null ? !getClosingTime().equals(this)  ? getClosingTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}