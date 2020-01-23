package com.janpaces.modernjavatesting.weather;

import java.time.LocalDate;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);

	private RestTemplate restTemplate;

	private final String BRNO_LAT = "49.181000";
	private final String BRNO_LONG = "16.605800";

	private String token;
	private String baseUrl;

	@Autowired
	public WeatherClient(RestTemplate restTemplate, @Value("${weather.api.url}") String baseUrl, @Value("${weather.api.token}") String apiToken) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
		this.token = apiToken;
	}

	public WeatherForecast getWeatherForecastForDate(LocalDate date) {
		String url = timeMachineUrl(date);
		LOGGER.info("Getting weather from URL:{}", url);
		return restTemplate.getForObject(url, WeatherForecast.class);
	}

	private String timeMachineUrl(LocalDate date) {
		long unixDate = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
		return baseUrl +
				token +
				"/" + BRNO_LAT +
				"," + BRNO_LONG + "," +
				unixDate +
				"?lang=cs&exclude=hourly&units=si";
	}
}
