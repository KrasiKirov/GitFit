package ca.mcgill.ecse321.gitfit.model;

import java.sql.Time;
import java.util.*;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class SportCenter {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // SportCenter Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private int maxCapacity;
  private Time openingTime;
  private Time closingTime;

  // SportCenter Associations
  @OneToMany(mappedBy="sportCenter", cascade = { CascadeType.ALL })
  private List<Account> accounts;
  @OneToMany(mappedBy = "sportCenter", cascade = { CascadeType.ALL })
  private List<Registration> registrations;
  @OneToMany(mappedBy = "sportCenter", cascade = { CascadeType.ALL })
  private List<Session> sessions;
  @OneToMany(mappedBy = "sportCenter", cascade = { CascadeType.ALL })
  private List<FitnessClass> fitnessClasses;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public SportCenter() {
  }

  public SportCenter(int aMaxCapacity, Time aOpeningTime, Time aClosingTime) {
    maxCapacity = aMaxCapacity;
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
    accounts = new ArrayList<Account>();
    registrations = new ArrayList<Registration>();
    sessions = new ArrayList<Session>();
    fitnessClasses = new ArrayList<FitnessClass>();
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setId(int aId) {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxCapacity(int aMaxCapacity) {
    boolean wasSet = false;
    maxCapacity = aMaxCapacity;
    wasSet = true;
    return wasSet;
  }

  public boolean setOpeningTime(Time aOpeningTime) {
    boolean wasSet = false;
    openingTime = aOpeningTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosingTime(Time aClosingTime) {
    boolean wasSet = false;
    closingTime = aClosingTime;
    wasSet = true;
    return wasSet;
  }

  public int getId() {
    return id;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public Time getOpeningTime() {
    return openingTime;
  }

  public Time getClosingTime() {
    return closingTime;
  }

  /* Code from template association_GetMany */
  public Account getAccount(int index) {
    Account aAccount = accounts.get(index);
    return aAccount;
  }

  public List<Account> getAccounts() {
    List<Account> newAccounts = Collections.unmodifiableList(accounts);
    return newAccounts;
  }

  public int numberOfAccounts() {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts() {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(Account aAccount) {
    int index = accounts.indexOf(aAccount);
    return index;
  }

  /* Code from template association_GetMany */
  public Registration getRegistration(int index) {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations() {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations() {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations() {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration) {
    int index = registrations.indexOf(aRegistration);
    return index;
  }

  /* Code from template association_GetMany */
  public Session getSession(int index) {
    Session aSession = sessions.get(index);
    return aSession;
  }

  public List<Session> getSessions() {
    List<Session> newSessions = Collections.unmodifiableList(sessions);
    return newSessions;
  }

  public int numberOfSessions() {
    int number = sessions.size();
    return number;
  }

  public boolean hasSessions() {
    boolean has = sessions.size() > 0;
    return has;
  }

  public int indexOfSession(Session aSession) {
    int index = sessions.indexOf(aSession);
    return index;
  }

  /* Code from template association_GetMany */
  public FitnessClass getFitnessClass(int index) {
    FitnessClass aFitnessClass = fitnessClasses.get(index);
    return aFitnessClass;
  }

  public List<FitnessClass> getFitnessClasses() {
    List<FitnessClass> newFitnessClasses = Collections.unmodifiableList(fitnessClasses);
    return newFitnessClasses;
  }

  public int numberOfFitnessClasses() {
    int number = fitnessClasses.size();
    return number;
  }

  public boolean hasFitnessClasses() {
    boolean has = fitnessClasses.size() > 0;
    return has;
  }

  public int indexOfFitnessClass(FitnessClass aFitnessClass) {
    int index = fitnessClasses.indexOf(aFitnessClass);
    return index;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts() {
    return 0;
  }
  /* Code from template association_AddManyToOne */

  public boolean addAccount(Account aAccount) {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) {
      return false;
    }
    SportCenter existingSportCenter = aAccount.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter) {
      aAccount.setSportCenter(this);
    } else {
      accounts.add(aAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAccount(Account aAccount) {
    boolean wasRemoved = false;
    // Unable to remove aAccount, as it must always have a sportCenter
    if (!this.equals(aAccount.getSportCenter())) {
      accounts.remove(aAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_AddIndexControlFunctions */
  public boolean addAccountAt(Account aAccount, int index) {
    boolean wasAdded = false;
    if (addAccount(aAccount)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfAccounts()) {
        index = numberOfAccounts() - 1;
      }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(Account aAccount, int index) {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfAccounts()) {
        index = numberOfAccounts() - 1;
      }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } else {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistrations() {
    return 0;
  }

  /* Code from template association_AddManyToOne */
  public Registration addRegistration(int aId, Date aDate, Session aSession, Customer aCustomer) {
    return new Registration(aId, aDate, aSession, aCustomer, this);
  }

  public boolean addRegistration(Registration aRegistration) {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) {
      return false;
    }
    SportCenter existingSportCenter = aRegistration.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter) {
      aRegistration.setSportCenter(this);
    } else {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration) {
    boolean wasRemoved = false;
    // Unable to remove aRegistration, as it must always have a sportCenter
    if (!this.equals(aRegistration.getSportCenter())) {
      registrations.remove(aRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_AddIndexControlFunctions */
  public boolean addRegistrationAt(Registration aRegistration, int index) {
    boolean wasAdded = false;
    if (addRegistration(aRegistration)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfRegistrations()) {
        index = numberOfRegistrations() - 1;
      }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index) {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfRegistrations()) {
        index = numberOfRegistrations() - 1;
      }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } else {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSessions() {
    return 0;
  }

  /* Code from template association_AddManyToOne */
  public Session addSession(int aPrice, Time aEndTime, Time aStartTime, Date aDate, Instructor aInstructor,
      FitnessClass aFitnessClass) {
    return new Session(aPrice, aEndTime, aStartTime, aDate, aInstructor, aFitnessClass, this);
  }

  public boolean addSession(Session aSession) {
    boolean wasAdded = false;
    if (sessions.contains(aSession)) {
      return false;
    }
    SportCenter existingSportCenter = aSession.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter) {
      aSession.setSportCenter(this);
    } else {
      sessions.add(aSession);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSession(Session aSession) {
    boolean wasRemoved = false;
    // Unable to remove aSession, as it must always have a sportCenter
    if (!this.equals(aSession.getSportCenter())) {
      sessions.remove(aSession);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_AddIndexControlFunctions */
  public boolean addSessionAt(Session aSession, int index) {
    boolean wasAdded = false;
    if (addSession(aSession)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfSessions()) {
        index = numberOfSessions() - 1;
      }
      sessions.remove(aSession);
      sessions.add(index, aSession);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSessionAt(Session aSession, int index) {
    boolean wasAdded = false;
    if (sessions.contains(aSession)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfSessions()) {
        index = numberOfSessions() - 1;
      }
      sessions.remove(aSession);
      sessions.add(index, aSession);
      wasAdded = true;
    } else {
      wasAdded = addSessionAt(aSession, index);
    }
    return wasAdded;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFitnessClasses() {
    return 0;
  }

  /* Code from template association_AddManyToOne */
  public FitnessClass addFitnessClass(String aName, String aDescription, boolean aIsApproved) {
    return new FitnessClass(aName, aDescription, aIsApproved, this);
  }

  public boolean addFitnessClass(FitnessClass aFitnessClass) {
    boolean wasAdded = false;
    if (fitnessClasses.contains(aFitnessClass)) {
      return false;
    }
    SportCenter existingSportCenter = aFitnessClass.getSportCenter();
    boolean isNewSportCenter = existingSportCenter != null && !this.equals(existingSportCenter);
    if (isNewSportCenter) {
      aFitnessClass.setSportCenter(this);
    } else {
      fitnessClasses.add(aFitnessClass);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFitnessClass(FitnessClass aFitnessClass) {
    boolean wasRemoved = false;
    // Unable to remove aFitnessClass, as it must always have a sportCenter
    if (!this.equals(aFitnessClass.getSportCenter())) {
      fitnessClasses.remove(aFitnessClass);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  /* Code from template association_AddIndexControlFunctions */
  public boolean addFitnessClassAt(FitnessClass aFitnessClass, int index) {
    boolean wasAdded = false;
    if (addFitnessClass(aFitnessClass)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfFitnessClasses()) {
        index = numberOfFitnessClasses() - 1;
      }
      fitnessClasses.remove(aFitnessClass);
      fitnessClasses.add(index, aFitnessClass);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFitnessClassAt(FitnessClass aFitnessClass, int index) {
    boolean wasAdded = false;
    if (fitnessClasses.contains(aFitnessClass)) {
      if (index < 0) {
        index = 0;
      }
      if (index > numberOfFitnessClasses()) {
        index = numberOfFitnessClasses() - 1;
      }
      fitnessClasses.remove(aFitnessClass);
      fitnessClasses.add(index, aFitnessClass);
      wasAdded = true;
    } else {
      wasAdded = addFitnessClassAt(aFitnessClass, index);
    }
    return wasAdded;
  }

  public void delete() {
    while (accounts.size() > 0) {
      Account aAccount = accounts.get(accounts.size() - 1);
      aAccount.delete();
      accounts.remove(aAccount);
    }

    while (registrations.size() > 0) {
      Registration aRegistration = registrations.get(registrations.size() - 1);
      aRegistration.delete();
      registrations.remove(aRegistration);
    }

    while (sessions.size() > 0) {
      Session aSession = sessions.get(sessions.size() - 1);
      aSession.delete();
      sessions.remove(aSession);
    }

    while (fitnessClasses.size() > 0) {
      FitnessClass aFitnessClass = fitnessClasses.get(fitnessClasses.size() - 1);
      aFitnessClass.delete();
      fitnessClasses.remove(aFitnessClass);
    }

  }

  public String toString() {
    return super.toString() + "[" +
        "id" + ":" + getId() + "," +
        "maxCapacity" + ":" + getMaxCapacity() + "]" + System.getProperties().getProperty("line.separator") +
        "  " + "openingTime" + "="
        + (getOpeningTime() != null
            ? !getOpeningTime().equals(this) ? getOpeningTime().toString().replaceAll("  ", "    ") : "this"
            : "null")
        + System.getProperties().getProperty("line.separator") +
        "  " + "closingTime" + "="
        + (getClosingTime() != null
            ? !getClosingTime().equals(this) ? getClosingTime().toString().replaceAll("  ", "    ") : "this"
            : "null");
  }
}