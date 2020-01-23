package com.janpaces.modernjavatesting.stock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(StockClient.class);

	public final static String SWI_STOCK_SYMBOL = "SWI";

	private RestTemplate restTemplate;

	private String apiKey;

	private String baseUrl;

	@Autowired
	public StockClient(RestTemplate restTemplate, @Value("${stock.api.url}") String url, @Value("${stock.api.token}") String apiKey) {
		this.restTemplate = restTemplate;
		this.apiKey = apiKey;
		this.baseUrl = url;
	}

	public Optional<StockPrice> getStockPriceForDate(String stockSymbol, LocalDate date) {
		String url = historicalDataURL(date);
		LOGGER.info("Requesting historical stcok price from URL:{}", url);

		ResponseEntity<List<StockPrice>> rateResponse =
				restTemplate.exchange(url,
						HttpMethod.GET, null, new ParameterizedTypeReference<>() {
						});
		List<StockPrice> rates = rateResponse.getBody();
		if (rates != null && !rates.isEmpty()) {
			return Optional.of(rates.get(0));
		} else {
			return Optional.empty();
		}
	}

	private String historicalDataURL(LocalDate date) {
		return baseUrl +
				"stock/" +
				SWI_STOCK_SYMBOL +
				"/chart/date/" +
				DateTimeFormatter.BASIC_ISO_DATE.format(date) +
				"?chartByDay=true" +
				"&token=" +
				apiKey;
	}

}
