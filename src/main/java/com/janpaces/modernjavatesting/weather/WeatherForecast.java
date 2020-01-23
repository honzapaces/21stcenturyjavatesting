package com.janpaces.modernjavatesting.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {

	private Currently currently;

	public WeatherForecast() {
	}

	public WeatherForecast(Currently currently) {
		this.currently = currently;
	}

	public Currently getCurrently() {
		return currently;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WeatherForecast that = (WeatherForecast) o;
		return Objects.equals(currently, that.currently);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currently);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Currently {

		private String summary;
		private Double temperature;

		public Currently() {
		}

		public Currently(String summary, double temperature) {
			this.summary = summary;
			this.temperature = temperature;
		}

		public String getSummary() {
			return summary;
		}

		public Double getTemperature() {
			return temperature;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Currently currently = (Currently) o;
			return Objects.equals(summary, currently.summary) &&
					Objects.equals(temperature, currently.temperature);
		}

		@Override
		public int hashCode() {
			return Objects.hash(summary, temperature);
		}
	}
}
