package ca.mcgill.ecse321.carpoolapp.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.carpoolapp.model.Ad;
import ca.mcgill.ecse321.carpoolapp.model.Admin;
import ca.mcgill.ecse321.carpoolapp.model.Driver;
import ca.mcgill.ecse321.carpoolapp.model.Passenger;
import ca.mcgill.ecse321.carpoolapp.model.Stop;
import ca.mcgill.ecse321.carpoolapp.model.Vehicle;
import ca.mcgill.ecse321.carpoolapp.repository.CarpoolappRepository;

@RestController
public class CarpoolappController {

	@Autowired
	CarpoolappRepository repository;

	@RequestMapping("/")
	public String greeting() {
		return "Hello carpool!";
	}

	
	@PostMapping("/driver/{id}")
	public String createDriver(@PathVariable("id") int id, String name)
	{
		Driver driver = repository.createDriver(id, name);
		return driver.getUser().getName();
	}
	
	@GetMapping("/driver/{id}")
	public String queryDrvier(@PathVariable("id") int id)
	{
		Driver driver = repository.getDriver(id);
		if (driver == null) {
			return "NOT FOUND";
		}
		return driver.getUser().getName();
	}
	
	@PostMapping("/passenger/{id}")
	public String createPassenger(@PathVariable("id") int id, String name)
	{
		Passenger passenger = repository.createPassenger(id, name);
		return passenger.getUser().getName();
	}
	
	@GetMapping("/passenger/{id}")
	public String queryPassenger(@PathVariable("id") int id)
	{
		Passenger passenger = repository.getPassenger(id);
		if (passenger == null) {
			return "NOT FOUND";
		}
		return passenger.getUser().getName();
	}
	
	@PostMapping("/ad/{id}/{price}/{}")
	public int createAd(Driver driver, @PathVariable("id") int id, int price, Vehicle vehicle) {
		
		Ad ad = repository.createAd(driver, id, price, vehicle);
		return ad.getId();
	}
	
	@GetMapping("/ad/{id}")
	public String queryAd(@PathVariable("id") int id) {
		
		Ad ad = repository.getAd(id);
		if (ad == null) {
			return "NOT FOUND";
		}
		return "Ad number" + ad.getId();
	}
	
	@PostMapping("/stop/{id}")
	public int createStop(Ad ad, Time time, Date date, int x, int y, @PathVariable("id") int id) {
		
		Stop stop = repository.createStop(ad, time, date, x, y, id);
		return stop.getId();
	}
	
	@GetMapping("/stop/{id}")
	public String queryStop(@PathVariable("id") int id) {
		
		Stop stop = repository.getStop(id);
		if (stop == null) {
			return "NOT FOUND";
		}
		return "Ad number" + stop.getId();
	}
	
	
//	@PostMapping("/participants/{name}")
//	public String createParticipant(@PathVariable("name") String name) {
//		Participant participant = repository.createParticipant(name);
//		return participant.getName();
//	}
//
//	@GetMapping("/participants/{name}")
//	public String queryParticipant(@PathVariable("name") String name) {
//		Participant participant = repository.getParticipant(name);
//		if(participant == null) {
//			return "NOT FOUND";
//		}
//		return participant.getName();
//	}

}