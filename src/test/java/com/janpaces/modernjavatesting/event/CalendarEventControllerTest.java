package com.janpaces.modernjavatesting.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class CalendarEventControllerTest {

	private CalendarEventController eventController;

	private CalendarEventRepository eventRepository = mock(CalendarEventRepository.class);
	public static final Long TEST_ID = 1L;

	@Before
	public void setup() {
		reset(eventRepository);
		eventController = new CalendarEventController(eventRepository);
	}

	@Test
	public void createEvent() {
		//given
		CalendarEvent calendarEvent = new CalendarEvent();
		when(eventRepository.save(any())).thenReturn(calendarEvent);
		//when
		CalendarEvent actualEvent = eventController.createEvent(calendarEvent);
		//then
		assertThat(actualEvent).isEqualTo(calendarEvent);
		verify(eventRepository).save(calendarEvent);
	}

	@Test
	public void getCalendarEventById() {
		//given
		CalendarEvent calendarEvent = new CalendarEvent();
		when(eventRepository.findById(anyLong())).thenReturn(Optional.of(calendarEvent));
		//when
		CalendarEvent actualEvent = eventController.getCalendarEventById(TEST_ID);
		//then
		assertThat(actualEvent).isEqualTo(calendarEvent);
		verify(eventRepository).findById(TEST_ID);
	}

	@Test
	public void getCalendarEventByDate() {
		//given
		CalendarEvent calendarEvent = new CalendarEvent();
		LocalDate now = LocalDate.now();
		calendarEvent.setEventDate(now);
		when(eventRepository.findByEventDate(any())).thenReturn(List.of(calendarEvent));
		//when
		List<CalendarEvent> actualEvent = eventController.getCalendarEventByDate(now);
		//then
		assertThat(actualEvent).isEqualTo(List.of(calendarEvent));
	}

	@Test
	public void updateCalendarEventById() {
		//given
		CalendarEvent calendarEvent = new CalendarEvent();
		when(eventRepository.save(any())).thenReturn(calendarEvent);
		//when
		CalendarEvent actualEvent = eventController.updateCalendarEventById(TEST_ID, calendarEvent);
		//then
		assertThat(actualEvent).isEqualTo(calendarEvent);
	}

	@Test
	public void deleteEventById() {
		//given
		//when
		eventController.deleteEventById(TEST_ID);
		//then
		verify(eventRepository).deleteById(eq(TEST_ID));
	}
}