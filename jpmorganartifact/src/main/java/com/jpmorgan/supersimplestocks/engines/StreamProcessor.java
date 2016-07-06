package com.jpmorgan.supersimplestocks.engines;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StreamProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(StreamProcessor.class);
	
	public BigDecimal calculateDividendYield(double price, double lastDividend) {
		double result = lastDividend / price;
		return new BigDecimal(result);
	}
	
	public BigDecimal calculateDividendYield(double price, double fixedDividend, double parValue) {
		logger.info("TODO: Pending to define");
		return null;
	}
	
}
