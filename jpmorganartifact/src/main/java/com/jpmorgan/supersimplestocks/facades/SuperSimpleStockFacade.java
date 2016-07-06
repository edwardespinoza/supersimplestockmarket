package com.jpmorgan.supersimplestocks.facades;

import java.util.List;
import java.util.Map;

import com.jpmorgan.supersimplestocks.caches.Database;
import com.jpmorgan.supersimplestocks.engines.StockExchangeProcessor;
import com.jpmorgan.supersimplestocks.engines.TradeProcessor;
import com.jpmorgan.supersimplestocks.exceptions.TradeListEmptyException;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;
import com.jpmorgan.supersimplestocks.vos.TradeVO;
import com.jpmorgan.supersimplestocks.vos.VolumeWeightedStockPriceVO;

public class SuperSimpleStockFacade {
	
//	private static final Logger logger = LoggerFactory.getLogger(SuperSimpleStockFacade.class);

	static StockExchangeProcessor seProcess = new StockExchangeProcessor();
	static TradeProcessor trProcess = new TradeProcessor();
	
	public static double callCalculateDividendYield (double price,  StockExchangeVO seVO){
		return seProcess.calculateDividendYield(price, seVO);
	}
	
	public static double callCalculatePERatio (double price,  StockExchangeVO seVO){
		return seProcess.calculateDividendYield(price, seVO);
	}
	
	public static boolean callRecordTrades (List<TradeVO> trades, Database db) throws TradeListEmptyException{
		
		boolean ok = false;
		if (trades != null)	{
			trades.forEach(trade -> trProcess.recordTrade(trade, db));
			System.out.println("\nTrades Saved");
			ok = true;
		}else{
			throw new TradeListEmptyException();
		}
		
		return ok;
	}
	
	public static Map<String, VolumeWeightedStockPriceVO> callCalculateVolumneWeightedStockPrice (Map<String, StockExchangeVO> stockExchange, List<TradeVO> trades, int seconds){
		return trProcess.calculateVolumneWeightedStockPrice(stockExchange, trades, seconds);
	}
	
	public static double callCalculateGBCEAllShareIndex (Map<String, VolumeWeightedStockPriceVO> mapVWSP){
		return trProcess.calculateGBCEAllShareIndex(mapVWSP);
	}
	
	
	
}
