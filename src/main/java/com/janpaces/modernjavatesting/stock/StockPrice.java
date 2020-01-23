package com.janpaces.modernjavatesting.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPrice {

	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal open;
	private BigDecimal close;

	public StockPrice() {
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StockPrice that = (StockPrice) o;
		return Objects.equals(high, that.high) &&
				Objects.equals(low, that.low) &&
				Objects.equals(open, that.open) &&
				Objects.equals(close, that.close);
	}

	@Override
	public int hashCode() {
		return Objects.hash(high, low, open, close);
	}
}
