package com.jpmorgan.supersimplestocks.engines;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.supersimplestocks.caches.Database;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;
import com.jpmorgan.supersimplestocks.vos.TradeVO;
import com.jpmorgan.supersimplestocks.vos.VolumeWeightedStockPriceVO;

public class TradeProcessorTest {

	TradeProcessor trProcess = new TradeProcessor();
	List<TradeVO> trades;
	
	Map<String, StockExchangeVO> mapStock;
	Database db;
	
	int timeHorizonInMinutes = 0; 
	
	@Before
	public void setUp() throws Exception {
		
		timeHorizonInMinutes = 5; 
		
		db = new Database();
		trades = new ArrayList<TradeVO>();
		trades.add(new TradeVO("TEA", LocalDateTime.now(), LocalDateTime.now(), 10, 2.5, "B"));
		trades.add(new TradeVO("POP", LocalDateTime.now(), LocalDateTime.now(), 5, 3.0, "B"));
		trades.add(new TradeVO("POP", LocalDateTime.now(), LocalDateTime.now(), 3, 2.0, "B"));
		trades.add(new TradeVO("JOE", LocalDateTime.now(), LocalDateTime.now(), 5, 1.0, "S"));
		trades.add(new TradeVO("JOE", LocalDateTime.now(), LocalDateTime.now(), 5, 2.0, "S"));
		trades.add(new TradeVO("ALE", LocalDateTime.now(), LocalDateTime.now(), 3, 2.1, "B"));
		trades.add(new TradeVO("ALE", LocalDateTime.now(), LocalDateTime.now(), 4, 2.1, "S"));
		trades.add(new TradeVO("GIN", LocalDateTime.now(), LocalDateTime.now(), 1, 1.2, "S"));
		
		mapStock = new HashMap<String, StockExchangeVO>();
		mapStock.put("TEA", new StockExchangeVO());
		mapStock.put("POP", new StockExchangeVO());
		mapStock.put("ALE", new StockExchangeVO());
		mapStock.put("GIN", new StockExchangeVO());
		mapStock.put("JOE", new StockExchangeVO());

	}

	@Test
	public void testRecordTrades() {
		assertTrue(trProcess.recordTrade(trades.get(0), db));
	}
	
	@Test
	public void testCalculateVolumneWeightedStockPrice() {
		
		double expectedPOP = 2.625;
		double expectedJOE = 1.5;
		double expectedTEA = 2.5;
		double expectedALE = 2.1;
		double expectedGIN = 1.2;
		
		Map<String, VolumeWeightedStockPriceVO> map;
		
		map = trProcess.calculateVolumneWeightedStockPrice(mapStock, trades, timeHorizonInMinutes*60);
		
		assertTrue(map.get("POP").returnVWSP() == expectedPOP);
		assertTrue(map.get("JOE").returnVWSP() == expectedJOE);
		assertTrue(map.get("TEA").returnVWSP() == expectedTEA);
		assertTrue(map.get("ALE").returnVWSP() == expectedALE);
		assertTrue(map.get("GIN").returnVWSP() == expectedGIN);
	}

}
