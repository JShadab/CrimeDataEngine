package org.shadab.parking.controller;

import org.shadab.parking.model.ParkingModel;
import org.shadab.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {

	@Autowired
	private ParkingService parkingService;

	@GetMapping(value = "/hello")
	public String sayHello() {

		return "Ya Allah";
	}

	@PostMapping(value = "/bill")

	public String calculateBill(@RequestBody ParkingModel model) {

		model = parkingService.calculateBill(model);

		parkingService.saveRecord(model);

		return "$".concat(String.valueOf(model.getBill()));

	}

	@GetMapping(value = "/records")

	public Iterable<ParkingModel> getAllRecords() {

		return  parkingService.getAllRecords();

	}

}
