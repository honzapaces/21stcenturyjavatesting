package com.janpaces.modernjavatesting.day;

import com.janpaces.modernjavatesting.event.CalendarEvent;
import com.janpaces.modernjavatesting.stock.StockPrice;
import com.janpaces.modernjavatesting.weather.WeatherForecast;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OneDay {

	private LocalDate date;
	private List<CalendarEvent> events;
	private StockPrice stockPrice;
	private WeatherForecast forecast;

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setEvents(List<CalendarEvent> events) {
		this.events = events;
	}

	public void setStockPrice(StockPrice stockPrice) {
		this.stockPrice = stockPrice;
	}

	public void setForecast(WeatherForecast forecast) {
		this.forecast = forecast;
	}

	public LocalDate getDate() {
		return date;
	}

	public List<CalendarEvent> getEvents() {
		return events;
	}

	public StockPrice getStockPrice() {
		return stockPrice;
	}

	public WeatherForecast getForecast() {
		return forecast;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		OneDay oneDay = (OneDay) o;
		return Objects.equals(date, oneDay.date) &&
				Objects.equals(events, oneDay.events) &&
				Objects.equals(stockPrice, oneDay.stockPrice) &&
				Objects.equals(forecast, oneDay.forecast);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, events, stockPrice, forecast);
	}
}
