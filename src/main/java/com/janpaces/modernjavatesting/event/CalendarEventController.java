package com.janpaces.modernjavatesting.event;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/event")
@RestController
public class CalendarEventController {

	private CalendarEventRepository calendarEventRepository;

	@Autowired
	public CalendarEventController(CalendarEventRepository calendarEventRepository) {
		this.calendarEventRepository = calendarEventRepository;
	}

	@PostMapping
	public CalendarEvent createEvent(@RequestBody CalendarEvent event) {
		return calendarEventRepository.save(event);
	}

	@GetMapping
	public Iterable<CalendarEvent> getAllEvents() {
		return calendarEventRepository.findAll();
	}

	@GetMapping("/{id}")
	public CalendarEvent getCalendarEventById(@PathVariable Long id) {
		return calendarEventRepository.findById(id).orElseThrow(() -> new CalendarEventNotFoundException(id));
	}

	@GetMapping("/{date}")
	public List<CalendarEvent> getCalendarEventByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return calendarEventRepository.findByEventDate(date);
	}

	@PutMapping("/{id}")
	public CalendarEvent updateCalendarEventById(@PathVariable Long id, @RequestBody CalendarEvent newCalendarEvent) {
		return calendarEventRepository.findById(id)
				.map(event -> {
					event.setEventDate(newCalendarEvent.getEventDate());
					event.setEventHeader(newCalendarEvent.getEventHeader());
					event.setEventText(newCalendarEvent.getEventText());
					return calendarEventRepository.save(event);
				}).orElseGet(() -> {
					newCalendarEvent.setId(id);
					return calendarEventRepository.save(newCalendarEvent);
				});
	}

	@DeleteMapping
	public void deleteEventById(@RequestParam Long id) {
		calendarEventRepository.deleteById(id);
	}

}
