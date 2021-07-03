package com.nagarro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Flights")
public class Flight {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	@Column(name = "Flight_Id")
	private String flightnumber;

	@Column(name = "Departure")
	private String departure;

	@Column(name = "Arrival")
	private String arrival;

	@Column(name = "Valid_Till")
	private String valid;

	@Column(name = "Time")
	private String time;

	@Column(name = "Duration")
	private String duration;

	@Column(name = "Economy_Fare")
	private int fare;

	@Column(name = "Available")
	private String available;

	@Column(name = "Flight_Class")
	private String flightclass;

	@Column(name = "Business_Fare")
	private int businessfare;

	public Flight() {
	}

	public Flight(String flightnumber, String departure, String arrival, String valid, String time, String duration,
			int fare, String available, String flightclass) {
		this.flightnumber = flightnumber;
		this.departure = departure;
		this.arrival = arrival;
		this.valid = valid;
		this.time = time;
		this.duration = duration;
		this.fare = fare;
		this.available = available;
		this.flightclass = flightclass;
		this.businessfare = (int) (1.4 * fare);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getFlightclass() {
		return flightclass;
	}

	public void setFlightclass(String flightclass) {
		this.flightclass = flightclass;
	}

	public int getBusinessfare() {
		return businessfare;
	}

	public void setBusinessfare(int businessfare) {
		this.businessfare = businessfare;
	}

}
