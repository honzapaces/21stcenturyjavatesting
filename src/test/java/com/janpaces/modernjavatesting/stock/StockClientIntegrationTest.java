package com.janpaces.modernjavatesting.stock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.janpaces.modernjavatesting.stock.StockClient.SWI_STOCK_SYMBOL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.janpaces.modernjavatesting.helper.FileLoader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockClientIntegrationTest {

	@Autowired
	private StockClient subject;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	@Test
	public void shouldCallWeatherService() throws Exception {

		// https://cloud.iexapis.com/stable/stock/SWI/chart/date/20190816?chartByDay=true&token=sk_1567e40de22c465f8dd22c5233dbe520

		wireMockRule.stubFor(get(urlEqualTo("/stock/SWI/chart/date/20190816?chartByDay=true&token=stockToken"))
				.willReturn(aResponse()
						.withBody(FileLoader.read("classpath:stockPriceResponse.json"))
						.withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.withStatus(200)));

		LocalDate testDate = LocalDate.of(2019, 8, 16);

		Optional<StockPrice> stockPriceForDate = subject.getStockPriceForDate(SWI_STOCK_SYMBOL, testDate);

		StockPrice value = new StockPrice();
		value.setClose(BigDecimal.valueOf(16.93));
		value.setHigh(BigDecimal.valueOf(17.03));
		value.setOpen(BigDecimal.valueOf(16.93));
		value.setLow(BigDecimal.valueOf(16.64));

		Optional<StockPrice> expectedResponse = Optional.of(value);
		assertThat(stockPriceForDate).isEqualTo(expectedResponse);
	}

}