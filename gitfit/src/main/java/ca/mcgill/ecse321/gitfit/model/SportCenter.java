import java.sql.Time;
import java.util.*;
import java.sql.Date;

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
  private List<FitnessClass> fitnessClasses;
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
    fitnessClasses = new ArrayList<FitnessClass>();
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

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
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

  /* Code from template association_GetMany */
  public FitnessClass getFitnessClass(int index)
  {
    FitnessClass aFitnessClass = fitnessClasses.get(index);
    return aFitnessClass;
  }

  public List<FitnessClass> getFitnessClasses()
  {
    List<FitnessClass> newFitnessClasses = Collections.unmodifiableList(fitnessClasses);
    return newFitnessClasses;
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
}