package com.janpaces.modernjavatesting.day;

import com.janpaces.modernjavatesting.event.CalendarEvent;
import com.janpaces.modernjavatesting.event.CalendarEventRepository;
import com.janpaces.modernjavatesting.stock.StockClient;
import com.janpaces.modernjavatesting.stock.StockPrice;
import com.janpaces.modernjavatesting.weather.WeatherClient;
import com.janpaces.modernjavatesting.weather.WeatherForecast;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/day")
public class DayController {

	private CalendarEventRepository repository;
	private WeatherClient weatherClient;
	private StockClient stockClient;

	@Autowired
	public DayController(CalendarEventRepository repository, WeatherClient weatherClient, StockClient stockClient) {
		this.repository = repository;
		this.weatherClient = weatherClient;
		this.stockClient = stockClient;
	}

	@GetMapping
	public OneDay getDayOverview(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		WeatherForecast weatherForecastForDate = weatherClient.getWeatherForecastForDate(date);
		Optional<StockPrice> stockPriceForDate = stockClient.getStockPriceForDate(StockClient.SWI_STOCK_SYMBOL, date);
		List<CalendarEvent> events = repository.findByEventDate(date);
		OneDay oneDay = new OneDay();
		oneDay.setDate(date);

		oneDay.setEvents(events);
		oneDay.setForecast(weatherForecastForDate);
		stockPriceForDate.ifPresent(oneDay::setStockPrice);
		return oneDay;
	}
}
