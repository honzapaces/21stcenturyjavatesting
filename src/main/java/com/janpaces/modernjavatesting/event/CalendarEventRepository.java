package com.janpaces.modernjavatesting.event;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
	List<CalendarEvent> findByEventDate(LocalDate date);
}
