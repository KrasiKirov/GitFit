package ca.mcgill.ecse321.gitfit.model;


import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SportCenter
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SportCenter Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private int maxCapacity;
  private Time openingTime;
  private Time closingTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SportCenter()
  {
  }
  
  // removed id from constructor
  public SportCenter(int aMaxCapacity, Time aOpeningTime, Time aClosingTime)
  {
    // id = aId; // id is auto-generated
    maxCapacity = aMaxCapacity;
    openingTime = aOpeningTime;
    closingTime = aClosingTime;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "maxCapacity" + ":" + getMaxCapacity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "openingTime" + "=" + (getOpeningTime() != null ? !getOpeningTime().equals(this)  ? getOpeningTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "closingTime" + "=" + (getClosingTime() != null ? !getClosingTime().equals(this)  ? getClosingTime().toString().replaceAll("  ","    ") : "this" : "null");
  }
}