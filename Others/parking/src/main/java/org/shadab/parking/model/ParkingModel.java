
package org.shadab.parking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.shadab.parking.utils.CustomerDateAndTimeDeserialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class ParkingModel {

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getExitTime() {
		return exitTime;
	}

	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String vehicleNumber;

	private float bill;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	private Date entryTime;
	@JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date exitTime;

}
