package com.janpaces.modernjavatesting.event;

public class CalendarEventNotFoundException extends RuntimeException {
	public CalendarEventNotFoundException(Long id) {
		super("Event with ID: " + id + " not found.");
	}
}
