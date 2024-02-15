package ca.mcgill.ecse321.gitfit.model;


import java.sql.Time;
import java.sql.Date;

// line 38 "model.ump"
// line 82 "model.ump"
public class Session
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Session Attributes
  private int id;
  private int price;
  private Time endTime;
  private Time startTime;
  private Date date;

  //Session Associations
  private Instructor instructor;
  private FitnessClass fitnessClass;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Session(int aId, int aPrice, Time aEndTime, Time aStartTime, Date aDate, Instructor aInstructor, FitnessClass aFitnessClass)
  {
    id = aId;
    price = aPrice;
    endTime = aEndTime;
    startTime = aStartTime;
    date = aDate;
    if (!setInstructor(aInstructor))
    {
      throw new RuntimeException("Unable to create Session due to aInstructor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setFitnessClass(aFitnessClass))
    {
      throw new RuntimeException("Unable to create Session due to aFitnessClass. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public int getPrice()
  {
    return price;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template association_GetOne */
  public Instructor getInstructor()
  {
    return instructor;
  }
  /* Code from template association_GetOne */
  public FitnessClass getFitnessClass()
  {
    return fitnessClass;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setInstructor(Instructor aNewInstructor)
  {
    boolean wasSet = false;
    if (aNewInstructor != null)
    {
      instructor = aNewInstructor;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setFitnessClass(FitnessClass aNewFitnessClass)
  {
    boolean wasSet = false;
    if (aNewFitnessClass != null)
    {
      fitnessClass = aNewFitnessClass;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    instructor = null;
    fitnessClass = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "instructor = "+(getInstructor()!=null?Integer.toHexString(System.identityHashCode(getInstructor())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "fitnessClass = "+(getFitnessClass()!=null?Integer.toHexString(System.identityHashCode(getFitnessClass())):"null");
  }
}