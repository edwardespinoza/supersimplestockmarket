package com.jpmorgan.supersimplestocks.engines;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.StatUtils;

import com.jpmorgan.supersimplestocks.caches.Database;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;
import com.jpmorgan.supersimplestocks.vos.TradeVO;
import com.jpmorgan.supersimplestocks.vos.VolumeWeightedStockPriceVO;

public class TradeProcessor {

//	private static final Logger logger = LoggerFactory.getLogger(TradeProcessor.class);
	
	public boolean recordTrade (TradeVO vo, Database db){
		db.addTrade(vo);
		return true;
	}
	
	/**
	 * For every stock traded over the trading horizon
	 * @param seconds
	 * @return
	 */
	
	public Map<String, VolumeWeightedStockPriceVO> calculateVolumneWeightedStockPrice (Map<String, StockExchangeVO> stockExchange, List<TradeVO> trades, int seconds){
		
		Map<String, VolumeWeightedStockPriceVO> mapVWSP = new HashMap<>();
		
		//Initialize new Map
		stockExchange.forEach((k,v)->{
			mapVWSP.put(k, new VolumeWeightedStockPriceVO());
		});
		
//		Iterate Array filtering only n seconds trades
		trades.stream().filter(trade -> trade.isFromTimeHorizon(seconds) == true).forEach(trade -> {
			//Increment and calculate quantity x price
			 mapVWSP.get(trade.getStockSymbol()).incrementPriceXQuantity(trade.getPrice(), trade.getQuantity());
//			mapVWSP.computeIfPresent(trade.getStockSymbol(), (k,v) -> v.incrementPriceXQuantity(trade.getPrice(), trade.getQuantity()));
		});
		
		return mapVWSP;
	}
	
	public double calculateGBCEAllShareIndex (Map<String, VolumeWeightedStockPriceVO> mapVWSP){
		double result = 0;
		double[] arrayOfPrices = new double[mapVWSP.size()];
		mapVWSP.forEach((k,v) -> {
			
		});
		result = StatUtils.geometricMean(arrayOfPrices);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}
