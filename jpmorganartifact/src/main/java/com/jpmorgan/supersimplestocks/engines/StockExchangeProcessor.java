package com.jpmorgan.supersimplestocks.engines;

import com.jpmorgan.supersimplestocks.enums.StockTypes;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;

public class StockExchangeProcessor {

	public double calculateDividendYield (double price, StockExchangeVO seVO) {
		double dividendYield;
		if (seVO.getType() == StockTypes.COMMON) {
			dividendYield = seVO.getLastDividend().doubleValue() / price;
		} else {
			dividendYield = (seVO.getFixedDividend().doubleValue() * seVO.getParValue().doubleValue()) / price;
		}
		return dividendYield;
	}

	public double calculatePERatio(double price, StockExchangeVO seVO) {
		double peRatio;
		peRatio = price / calculateDividendYield (price, seVO);
		return peRatio;
	}
	
}
