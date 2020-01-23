package com.janpaces.modernjavatesting.event;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CalendarEvent {

	public CalendarEvent() {
	}

	public CalendarEvent(LocalDate eventDate, String eventHeader, String eventText) {
		this.eventDate = eventDate;
		this.eventHeader = eventHeader;
		this.eventText = eventText;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate eventDate;

	private String eventHeader;
	private String eventText;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CalendarEvent that = (CalendarEvent) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(eventDate, that.eventDate) &&
				Objects.equals(eventHeader, that.eventHeader) &&
				Objects.equals(eventText, that.eventText);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, eventDate, eventHeader, eventText);
	}
}
