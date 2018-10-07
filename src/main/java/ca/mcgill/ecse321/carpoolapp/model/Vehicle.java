/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4262.30c9ffc7c modeling language!*/

package ca.mcgill.ecse321.carpoolapp.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// line 47 "../../../../../../../ump/tmp788046/model.ump"
// line 113 "../../../../../../../ump/tmp788046/model.ump"
@Entity
@Table(name="vehicle")
public class Vehicle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Vehicle Attributes
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="plate_number")
  private String plateNumber;
  @Column(name="year")
  private int year;
  @Column(name="brand")
  private String brand;
  @Column(name="available_seats")
  private int availableSeat;

  //Vehicle Associations
  private List<Driver> drivers;
  private CarPoolManager carPoolManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Vehicle(int aYear, String aBrand, String aPlateNumber, int aAvailableSeat, CarPoolManager aCarPoolManager, Driver... allDrivers)
  {
    year = aYear;
    brand = aBrand;
    plateNumber = aPlateNumber;
    availableSeat = aAvailableSeat;
    drivers = new ArrayList<Driver>();
    boolean didAddDrivers = setDrivers(allDrivers);
    if (!didAddDrivers)
    {
      throw new RuntimeException("Unable to create Vehicle, must have at least 1 drivers");
    }
    boolean didAddCarPoolManager = setCarPoolManager(aCarPoolManager);
    if (!didAddCarPoolManager)
    {
      throw new RuntimeException("Unable to create vehicle due to carPoolManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setYear(int aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setBrand(String aBrand)
  {
    boolean wasSet = false;
    brand = aBrand;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlateNumber(String aPlateNumber)
  {
    boolean wasSet = false;
    plateNumber = aPlateNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setAvailableSeat(int aAvailableSeat)
  {
    boolean wasSet = false;
    availableSeat = aAvailableSeat;
    wasSet = true;
    return wasSet;
  }

  public int getYear()
  {
    return year;
  }

  public String getBrand()
  {
    return brand;
  }

  public String getPlateNumber()
  {
    return plateNumber;
  }

  public int getAvailableSeat()
  {
    return availableSeat;
  }
  /* Code from template association_GetMany */
  public Driver getDriver(int index)
  {
    Driver aDriver = drivers.get(index);
    return aDriver;
  }

  public List<Driver> getDrivers()
  {
    List<Driver> newDrivers = Collections.unmodifiableList(drivers);
    return newDrivers;
  }

  public int numberOfDrivers()
  {
    int number = drivers.size();
    return number;
  }

  public boolean hasDrivers()
  {
    boolean has = drivers.size() > 0;
    return has;
  }

  public int indexOfDriver(Driver aDriver)
  {
    int index = drivers.indexOf(aDriver);
    return index;
  }
  /* Code from template association_GetOne */
  public CarPoolManager getCarPoolManager()
  {
    return carPoolManager;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfDriversValid()
  {
    boolean isValid = numberOfDrivers() >= minimumNumberOfDrivers();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDrivers()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addDriver(Driver aDriver)
  {
    boolean wasAdded = false;
    if (drivers.contains(aDriver)) { return false; }
    drivers.add(aDriver);
    if (aDriver.indexOfVehicle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aDriver.addVehicle(this);
      if (!wasAdded)
      {
        drivers.remove(aDriver);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeDriver(Driver aDriver)
  {
    boolean wasRemoved = false;
    if (!drivers.contains(aDriver))
    {
      return wasRemoved;
    }

    if (numberOfDrivers() <= minimumNumberOfDrivers())
    {
      return wasRemoved;
    }

    int oldIndex = drivers.indexOf(aDriver);
    drivers.remove(oldIndex);
    if (aDriver.indexOfVehicle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aDriver.removeVehicle(this);
      if (!wasRemoved)
      {
        drivers.add(oldIndex,aDriver);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setDrivers(Driver... newDrivers)
  {
    boolean wasSet = false;
    ArrayList<Driver> verifiedDrivers = new ArrayList<Driver>();
    for (Driver aDriver : newDrivers)
    {
      if (verifiedDrivers.contains(aDriver))
      {
        continue;
      }
      verifiedDrivers.add(aDriver);
    }

    if (verifiedDrivers.size() != newDrivers.length || verifiedDrivers.size() < minimumNumberOfDrivers())
    {
      return wasSet;
    }

    ArrayList<Driver> oldDrivers = new ArrayList<Driver>(drivers);
    drivers.clear();
    for (Driver aNewDriver : verifiedDrivers)
    {
      drivers.add(aNewDriver);
      if (oldDrivers.contains(aNewDriver))
      {
        oldDrivers.remove(aNewDriver);
      }
      else
      {
        aNewDriver.addVehicle(this);
      }
    }

    for (Driver anOldDriver : oldDrivers)
    {
      anOldDriver.removeVehicle(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDriverAt(Driver aDriver, int index)
  {  
    boolean wasAdded = false;
    if(addDriver(aDriver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDrivers()) { index = numberOfDrivers() - 1; }
      drivers.remove(aDriver);
      drivers.add(index, aDriver);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDriverAt(Driver aDriver, int index)
  {
    boolean wasAdded = false;
    if(drivers.contains(aDriver))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDrivers()) { index = numberOfDrivers() - 1; }
      drivers.remove(aDriver);
      drivers.add(index, aDriver);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDriverAt(aDriver, index);
    }
    return wasAdded;
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
      existingCarPoolManager.removeVehicle(this);
    }
    carPoolManager.addVehicle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Driver> copyOfDrivers = new ArrayList<Driver>(drivers);
    drivers.clear();
    for(Driver aDriver : copyOfDrivers)
    {
      aDriver.removeVehicle(this);
    }
    CarPoolManager placeholderCarPoolManager = carPoolManager;
    this.carPoolManager = null;
    if(placeholderCarPoolManager != null)
    {
      placeholderCarPoolManager.removeVehicle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "year" + ":" + getYear()+ "," +
            "brand" + ":" + getBrand()+ "," +
            "plateNumber" + ":" + getPlateNumber()+ "," +
            "availableSeat" + ":" + getAvailableSeat()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "carPoolManager = "+(getCarPoolManager()!=null?Integer.toHexString(System.identityHashCode(getCarPoolManager())):"null");
  }
  
  //----------------
  //Methods for data base
  //Added by Roger Zhang
  //----------------
  
  @Column(name="carpool_manager_id")
  public int getCarpoolManagerId() {
	  return this.getCarPoolManager().getId();
  }
  
  @Column(name="drivers")
  public int[] getDriverIds() {
	  int nbOfDrivers = this.drivers.size();
	  int[] arrayOfDriverIds = new int[nbOfDrivers];
	  
	  for(int i = 0; i < nbOfDrivers; i++) {
		  arrayOfDriverIds[i] = this.drivers.get(i).getUser().getId();
	  }
	  
	  return arrayOfDriverIds;
  }
}