package plane.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WhatDay {
	public static String isToday() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return today.format(formatter);
	}
	
	public static String addDay(int plus) {
		LocalDate addDay = LocalDate.now().plusDays(plus);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return addDay.format(formatter);
	}
	
	public static String curTime() {
		return LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
	}
}
