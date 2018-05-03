package org.shadab.parking.utils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class TimeUtils {

	public static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
		return Period.between(dob.toLocalDate(), now.toLocalDate());
	}

	public static long getDuration(LocalDateTime dob, LocalDateTime now) {
		LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), dob.getHour(),
				dob.getMinute(), dob.getSecond());
		Duration duration = Duration.between(today, now);

		long seconds = duration.getSeconds();

		return seconds;
	}

	public static long[] getTime(LocalDateTime dob, LocalDateTime now) {
		LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), dob.getHour(),
				dob.getMinute(), dob.getSecond());
		Duration duration = Duration.between(today, now);

		long seconds = duration.getSeconds();

		long hours = seconds / SECONDS_PER_HOUR;
		long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
		long secs = (seconds % SECONDS_PER_MINUTE);

		return new long[] { hours, minutes, secs };
	}

	public static LocalDateTime convertIntoLocalDateTime(Date date) {

		ZoneId defaultZoneId = ZoneId.systemDefault();

		// 1. Convert Date -> Instant
		Instant instant = date.toInstant();
		System.out.println("instant : " + instant); // Zone : UTC+0

		// 2. Instant + system default time zone + toLocalDate() = LocalDate
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		System.out.println("localDate : " + localDate);

		// 3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
		LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
		System.out.println("localDateTime : " + localDateTime);

		// 4. Instant + system default time zone = ZonedDateTime
		ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
		System.out.println("zonedDateTime : " + zonedDateTime);

		return localDateTime;
	}

	public static boolean isWeekend(LocalDateTime entry, LocalDateTime exit) {

		DayOfWeek dayObj = entry.getDayOfWeek();

		// the day-of-week, from 1 (Monday) to 7 (Sunday)
		int day = dayObj.getValue();

		boolean isWeekendEntry = day > 5 ? true : false;

		dayObj = exit.getDayOfWeek();

		// the day-of-week, from 1 (Monday) to 7 (Sunday)
		day = dayObj.getValue();

		boolean isWeekendExit = day > 5 ? true : false;

		return isWeekendEntry & isWeekendExit;
	}

	private static final int MINUTES_PER_HOUR = 60;
	private static final int SECONDS_PER_MINUTE = 60;
	private static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

	public static final int TWO_HOURS = 2 * SECONDS_PER_HOUR;
	public static final int FIVE_HOURS = 5 * SECONDS_PER_HOUR;
	public static final int TEN_HOURS = 10 * SECONDS_PER_HOUR;
	public static final int FIFTEEN_HOURS = 15 * SECONDS_PER_HOUR;
	public static final int TWENTY_FOUR_HOURS = 24 * SECONDS_PER_HOUR;

}