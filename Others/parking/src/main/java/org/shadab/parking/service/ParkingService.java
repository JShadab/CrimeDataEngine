package org.shadab.parking.service;

import static org.shadab.parking.utils.TimeUtils.FIFTEEN_HOURS;
import static org.shadab.parking.utils.TimeUtils.FIVE_HOURS;
import static org.shadab.parking.utils.TimeUtils.TEN_HOURS;
import static org.shadab.parking.utils.TimeUtils.TWENTY_FOUR_HOURS;
import static org.shadab.parking.utils.TimeUtils.TWO_HOURS;
import static org.shadab.parking.utils.TimeUtils.convertIntoLocalDateTime;
import static org.shadab.parking.utils.TimeUtils.getDuration;
import static org.shadab.parking.utils.TimeUtils.getTime;
import static org.shadab.parking.utils.TimeUtils.isWeekend;

import java.time.LocalDateTime;
import java.util.Date;

import org.shadab.parking.model.ParkingModel;
import org.shadab.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingService {

	@Autowired
	private ParkingRepository parkingRepository;

	public ParkingModel calculateBill(ParkingModel model) {

		Date entryTime = model.getEntryTime();
		Date exitTime = model.getExitTime();

		LocalDateTime entryLocalDateTime = convertIntoLocalDateTime(entryTime);
		LocalDateTime exitLocalDateTime = convertIntoLocalDateTime(exitTime);

		// Period period = getPeriod(entryLocalDateTime, exitLocalDateTime);
		long time[] = getTime(entryLocalDateTime, exitLocalDateTime);

		long duration = getDuration(entryLocalDateTime, exitLocalDateTime);

		// int years = period.getYears();
		// int months = period.getMonths();
		// int days = period.getDays();

		long hours = time[0];
		// long minutes = time[1];
		// long seconds = time[2];

		boolean isWeekend = isWeekend(entryLocalDateTime, exitLocalDateTime);

		float bill = 0;

		if (duration < TWO_HOURS) {
			bill = isWeekend ? (hours * 5) : (hours * 7);
		} else if (duration > TWO_HOURS && duration <= FIVE_HOURS) {
			bill = isWeekend ? (hours * 8) : (hours * 10);
		} else if (duration > FIVE_HOURS && duration <= TEN_HOURS) {
			bill = isWeekend ? (hours * 12) : (hours * 15);
		} else if (duration > TEN_HOURS && duration <= FIFTEEN_HOURS) {
			bill = isWeekend ? (hours * 18) : (hours * 22);
		} else if (duration > FIFTEEN_HOURS && duration <= TWENTY_FOUR_HOURS) {
			bill = isWeekend ? (hours * 25) : (hours * 30);
		}

		model.setBill(bill);

		return model;

	}

	public Iterable<ParkingModel> getAllRecords() {

		return parkingRepository.findAll();

	}

	public void saveRecord(ParkingModel model) {
		parkingRepository.save(model);

	}

}
