package com.janpaces.modernjavatesting.stock;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockPriceController {

	private StockClient stockClient;

	@Autowired
	public StockPriceController(StockClient stockClient) {
		this.stockClient = stockClient;
	}

	@GetMapping("stock/{date}")
	public StockPrice getSwiStockPriceForDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return stockClient.getStockPriceForDate("SWI", date).orElseThrow();
	}
}
