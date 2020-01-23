package com.janpaces.modernjavatesting.weather;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

	private WeatherClient weatherClient;

	@Autowired
	public WeatherController(WeatherClient weatherClient) {
		this.weatherClient = weatherClient;
	}

	@GetMapping("/weather/{date}")
	public WeatherForecast getWeatherForDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return weatherClient.getWeatherForecastForDate(date);
	}
}
