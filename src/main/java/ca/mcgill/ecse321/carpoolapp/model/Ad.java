/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4295.41a59b8ce modeling language!*/

package ca.mcgill.ecse321.carpoolapp.model;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

// line 38 "../../../../../../../../ump/18102077559/model.ump"
// line 114 "../../../../../../../../ump/18102077559/model.ump"
@Entity
@Table(name="ad")
public class Ad
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ad Attributes
  private int id;
  private double price;
  private boolean isActive;
  private boolean isCompleted;

  //Ad Associations
  private List<Stop> stops;
  private Driver driver;
  private List<Passenger> passengers;
  private Vehicle vehicle;
  private CarPoolManager carPoolManager;
  
	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

  //------------------------
  // CONSTRUCTOR
  //------------------------

public Ad(int aId, double aPrice, boolean aIsActive, boolean aIsCompleted, Driver aDriver, Vehicle aVehicle, CarPoolManager aCarPoolManager)
  {
    id = aId;
    price = aPrice;
    isActive = aIsActive;
    isCompleted = aIsCompleted;
    stops = new ArrayList<Stop>();
    boolean didAddDriver = setDriver(aDriver);
    if (!didAddDriver)
    {
      throw new RuntimeException("Unable to create ad due to driver");
    }
    passengers = new ArrayList<Passenger>();
    if (!setVehicle(aVehicle))
    {
      throw new RuntimeException("Unable to create Ad due to aVehicle");
    }
    boolean didAddCarPoolManager = setCarPoolManager(aCarPoolManager);
    if (!didAddCarPoolManager)
    {
      throw new RuntimeException("Unable to create ad due to carPoolManager");
    }
  }

public Ad() {
	
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

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsActive(boolean aIsActive)
  {
    boolean wasSet = false;
    isActive = aIsActive;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsCompleted(boolean aIsCompleted)
  {
    boolean wasSet = false;
    isCompleted = aIsCompleted;
    wasSet = true;
    return wasSet;
  }
  @Id
  @JsonView(View.Summary.class)
  public int getId()
  {
    return id;
  }

  @JsonView(View.Summary.class)
  public double getPrice()
  {
    return price;
  }

  @JsonView(View.Summary.class)
  public boolean getIsActive()
  {
    return isActive;
  }

  @JsonView(View.Summary.class)
  public boolean getIsCompleted()
  {
    return isCompleted;
  }
  /* Code from template association_GetMany */
  public Stop getStop(int index)
  {
    Stop aStop = stops.get(index);
    return aStop;
  }
  @JsonView(View.Summary.class)
  @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
  public List<Stop> getStops()
  {
    List<Stop> newStops = Collections.unmodifiableList(stops);
    return newStops;
  }

  public int numberOfStops()
  {
    int number = stops.size();
    return number;
  }

  public boolean hasStops()
  {
    boolean has = stops.size() > 0;
    return has;
  }

  public int indexOfStop(Stop aStop)
  {
    int index = stops.indexOf(aStop);
    return index;
  }
  /* Code from template association_GetOne */
  @JsonView(View.Summary.class)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "ad_driver")
  public Driver getDriver()
  {
    return driver;
  }
  /* Code from template association_GetMany */
  public Passenger getPassenger(int index)
  {
    Passenger aPassenger = passengers.get(index);
    return aPassenger;
  }
  @JsonView(View.Summary.class)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name="ad_passenger", joinColumns=@JoinColumn(name="id"), inverseJoinColumns=
  @JoinColumn(name="passenger_id"))
  public List<Passenger> getPassengers()
  {
    List<Passenger> newPassengers = Collections.unmodifiableList(passengers);
    return newPassengers;
  }

  public int numberOfPassengers()
  {
    int number = passengers.size();
    return number;
  }

  public boolean hasPassengers()
  {
    boolean has = passengers.size() > 0;
    return has;
  }

  public int indexOfPassenger(Passenger aPassenger)
  {
    int index = passengers.indexOf(aPassenger);
    return index;
  }
  /* Code from template association_GetOne */
  @JsonView(View.Summary.class)
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "ad_vehicle")
  public Vehicle getVehicle()
  {
    return vehicle;
  }
  /* Code from template association_GetOne */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "ad_carpoolManager")
  public CarPoolManager getCarPoolManager()
  {
    return carPoolManager;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStops()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Stop addStop(Time aTime, Date aDate, int aNbOfAvailableSeat, int aX, int aY, int aId, CarPoolManager aCarPoolManager)
  {
    return new Stop(aTime, aDate, aNbOfAvailableSeat, aX, aY, aId, this, aCarPoolManager);
  }

  public boolean addStop(Stop aStop)
  {
    boolean wasAdded = false;
    if (stops.contains(aStop)) { return false; }
    Ad existingAd = aStop.getAd();
    boolean isNewAd = existingAd != null && !this.equals(existingAd);
    if (isNewAd)
    {
      aStop.setAd(this);
    }
    else
    {
      stops.add(aStop);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStop(Stop aStop)
  {
    boolean wasRemoved = false;
    //Unable to remove aStop, as it must always have a ad
    if (!this.equals(aStop.getAd()))
    {
      stops.remove(aStop);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStopAt(Stop aStop, int index)
  {  
    boolean wasAdded = false;
    if(addStop(aStop))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStops()) { index = numberOfStops() - 1; }
      stops.remove(aStop);
      stops.add(index, aStop);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStopAt(Stop aStop, int index)
  {
    boolean wasAdded = false;
    if(stops.contains(aStop))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStops()) { index = numberOfStops() - 1; }
      stops.remove(aStop);
      stops.add(index, aStop);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStopAt(aStop, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setDriver(Driver aDriver)
  {
    boolean wasSet = false;
    if (aDriver == null)
    {
      return wasSet;
    }

    Driver existingDriver = driver;
    driver = aDriver;
    if (existingDriver != null && !existingDriver.equals(aDriver))
    {
      existingDriver.removeAd(this);
    }
    driver.addAd(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPassengers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPassenger(Passenger aPassenger)
  {
    boolean wasAdded = false;
    if (passengers.contains(aPassenger)) { return false; }
    passengers.add(aPassenger);
    if (aPassenger.indexOfAd(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPassenger.addAd(this);
      if (!wasAdded)
      {
        passengers.remove(aPassenger);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePassenger(Passenger aPassenger)
  {
    boolean wasRemoved = false;
    if (!passengers.contains(aPassenger))
    {
      return wasRemoved;
    }

    int oldIndex = passengers.indexOf(aPassenger);
    passengers.remove(oldIndex);
    if (aPassenger.indexOfAd(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPassenger.removeAd(this);
      if (!wasRemoved)
      {
        passengers.add(oldIndex,aPassenger);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPassengerAt(Passenger aPassenger, int index)
  {  
    boolean wasAdded = false;
    if(addPassenger(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePassengerAt(Passenger aPassenger, int index)
  {
    boolean wasAdded = false;
    if(passengers.contains(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPassengerAt(aPassenger, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setVehicle(Vehicle aNewVehicle)
  {
    boolean wasSet = false;
    if (aNewVehicle != null)
    {
      vehicle = aNewVehicle;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCarPoolManager(CarPoolManager aCarPoolManager)
  {
    boolean wasSet = false;
    if (aCarPoolManager == null)
    {
      return wasSet;
    }

    CarPoolManager existingCarPoolManager = carPoolManager;
    carPoolManager = aCarPoolManager;
    if (existingCarPoolManager != null && !existingCarPoolManager.equals(aCarPoolManager))
    {
      existingCarPoolManager.removeAd(this);
    }
    carPoolManager.addAd(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (stops.size() > 0)
    {
      Stop aStop = stops.get(stops.size() - 1);
      aStop.delete();
      stops.remove(aStop);
    }
    
    Driver placeholderDriver = driver;
    this.driver = null;
    if(placeholderDriver != null)
    {
      placeholderDriver.removeAd(this);
    }
    ArrayList<Passenger> copyOfPassengers = new ArrayList<Passenger>(passengers);
    passengers.clear();
    for(Passenger aPassenger : copyOfPassengers)
    {
      aPassenger.removeAd(this);
    }
    vehicle = null;
    CarPoolManager placeholderCarPoolManager = carPoolManager;
    this.carPoolManager = null;
    if(placeholderCarPoolManager != null)
    {
      placeholderCarPoolManager.removeAd(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "price" + ":" + getPrice()+ "," +
            "isActive" + ":" + getIsActive()+ "," +
            "isCompleted" + ":" + getIsCompleted()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "driver = "+(getDriver()!=null?Integer.toHexString(System.identityHashCode(getDriver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "vehicle = "+(getVehicle()!=null?Integer.toHexString(System.identityHashCode(getVehicle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "carPoolManager = "+(getCarPoolManager()!=null?Integer.toHexString(System.identityHashCode(getCarPoolManager())):"null");
  }
}