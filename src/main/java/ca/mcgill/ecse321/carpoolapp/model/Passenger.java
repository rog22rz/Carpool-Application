/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4295.41a59b8ce modeling language!*/

package ca.mcgill.ecse321.carpoolapp.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

// line 25 "../../../../../../../../ump/18102077559/model.ump"
// line 104 "../../../../../../../../ump/18102077559/model.ump"
@Entity
@Table(name="passenger")
public class Passenger extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Passenger Attributes
  private int averagePaidPerKm;
  private int totalDistance;
  private int passenger_id;

  //Passenger Associations
  private List<Ad> ads;
  private List<Stop> stops;
  private CarPoolManager carPoolManager;
  private String passenger_name;
  
  @Id
  public int getId() {
	  if(this.getUser() == null) {
		  return this.passenger_id;
	  } else
		  this.passenger_id = this.getUser().getId();
	  return this.passenger_id;
  }
  
  public boolean setId(int aId) {
	  this.passenger_id = aId;
	  if(this.getUser() == null) {
		return true;  
	  } else
		return this.getUser().setId(aId);
  }
  
	public String getName() {
		if(this.getUser() == null) {
			return this.passenger_name;
		} else {
			this.passenger_name = this.getUser().getName();
		}
		return this.passenger_name;
	}
	
	public boolean setName(String name) {
		if(this.getUser() == null) {
			this.passenger_name = name;
			return true;
		} else {
			return this.getUser().setName(name);
		}
	}
  
  public void setAds(List<Ad> ads) {
	  this.ads = ads;
  }
	
  public void setStops(List<Stop> stops) {
	  this.stops = stops;
  }


  //------------------------
  // CONSTRUCTOR
  //------------------------



public Passenger(User aUser, int aAveragePaidPerKm, int aTotalDistance, CarPoolManager aCarPoolManager)
  {
    super(aUser);
    averagePaidPerKm = aAveragePaidPerKm;
    totalDistance = aTotalDistance;
    ads = new ArrayList<Ad>();
    stops = new ArrayList<Stop>();
    boolean didAddCarPoolManager = setCarPoolManager(aCarPoolManager);
    if (!didAddCarPoolManager)
    {
      throw new RuntimeException("Unable to create passenger due to carPoolManager");
    }
  }

public Passenger()
{

}

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAveragePaidPerKm(int aAveragePaidPerKm)
  {
    boolean wasSet = false;
    averagePaidPerKm = aAveragePaidPerKm;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalDistance(int aTotalDistance)
  {
    boolean wasSet = false;
    totalDistance = aTotalDistance;
    wasSet = true;
    return wasSet;
  }

  public int getAveragePaidPerKm()
  {
    return averagePaidPerKm;
  }

  public int getTotalDistance()
  {
    return totalDistance;
  }
  /* Code from template association_GetMany */
  public Ad getAd(int index)
  {
    Ad aAd = ads.get(index);
    return aAd;
  }
  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "passengers")
  public List<Ad> getAds()
  {
    List<Ad> newAds = Collections.unmodifiableList(ads);
    return newAds;
  }

  public int numberOfAds()
  {
    int number = ads.size();
    return number;
  }

  public boolean hasAds()
  {
    boolean has = ads.size() > 0;
    return has;
  }

  public int indexOfAd(Ad aAd)
  {
    int index = ads.indexOf(aAd);
    return index;
  }
  /* Code from template association_GetMany */
  public Stop getStop(int index)
  {
    Stop aStop = stops.get(index);
    return aStop;
  }
  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "passengers")
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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "passenger_carpoolManager")
  @JsonIgnore
  public CarPoolManager getCarPoolManager()
  {
    return carPoolManager;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAds()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAd(Ad aAd)
  {
    boolean wasAdded = false;
    if (ads.contains(aAd)) { return false; }
    ads.add(aAd);
    if (aAd.indexOfPassenger(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAd.addPassenger(this);
      if (!wasAdded)
      {
        ads.remove(aAd);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAd(Ad aAd)
  {
    boolean wasRemoved = false;
    if (!ads.contains(aAd))
    {
      return wasRemoved;
    }

    int oldIndex = ads.indexOf(aAd);
    ads.remove(oldIndex);
    if (aAd.indexOfPassenger(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAd.removePassenger(this);
      if (!wasRemoved)
      {
        ads.add(oldIndex,aAd);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAdAt(Ad aAd, int index)
  {  
    boolean wasAdded = false;
    if(addAd(aAd))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAds()) { index = numberOfAds() - 1; }
      ads.remove(aAd);
      ads.add(index, aAd);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAdAt(Ad aAd, int index)
  {
    boolean wasAdded = false;
    if(ads.contains(aAd))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAds()) { index = numberOfAds() - 1; }
      ads.remove(aAd);
      ads.add(index, aAd);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAdAt(aAd, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStops()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addStop(Stop aStop)
  {
    boolean wasAdded = false;
    if (stops.contains(aStop)) { return false; }
    stops.add(aStop);
    if (aStop.indexOfPassenger(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aStop.addPassenger(this);
      if (!wasAdded)
      {
        stops.remove(aStop);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeStop(Stop aStop)
  {
    boolean wasRemoved = false;
    if (!stops.contains(aStop))
    {
      return wasRemoved;
    }

    int oldIndex = stops.indexOf(aStop);
    stops.remove(oldIndex);
    if (aStop.indexOfPassenger(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aStop.removePassenger(this);
      if (!wasRemoved)
      {
        stops.add(oldIndex,aStop);
      }
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
      existingCarPoolManager.removePassenger(this);
    }
    carPoolManager.addPassenger(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Ad> copyOfAds = new ArrayList<Ad>(ads);
    ads.clear();
    for(Ad aAd : copyOfAds)
    {
      aAd.removePassenger(this);
    }
    ArrayList<Stop> copyOfStops = new ArrayList<Stop>(stops);
    stops.clear();
    for(Stop aStop : copyOfStops)
    {
      aStop.removePassenger(this);
    }
    CarPoolManager placeholderCarPoolManager = carPoolManager;
    this.carPoolManager = null;
    if(placeholderCarPoolManager != null)
    {
      placeholderCarPoolManager.removePassenger(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "averagePaidPerKm" + ":" + getAveragePaidPerKm()+ "," +
            "totalDistance" + ":" + getTotalDistance()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "carPoolManager = "+(getCarPoolManager()!=null?Integer.toHexString(System.identityHashCode(getCarPoolManager())):"null");
  }
}