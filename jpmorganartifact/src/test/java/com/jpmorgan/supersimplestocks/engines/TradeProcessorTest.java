package com.jpmorgan.supersimplestocks.engines;

import static org.junit.Assert.*;

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
	double DELTA = 1e-8;
	
	Map<String, StockExchangeVO> mapStock;
	Map<String, VolumeWeightedStockPriceVO> mapPricesForGBCEIndex = new HashMap<String, VolumeWeightedStockPriceVO>();
	Database db;
	
	double expectedPOP = 2.625;
	double expectedJOE = 1.5;
	double expectedTEA = 2.5;
	double expectedALE = 2.1;
	double expectedGIN = 1.2;
	
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
		
		mapPricesForGBCEIndex.put("TEA", new VolumeWeightedStockPriceVO(10,25.0));
		mapPricesForGBCEIndex.put("POP", new VolumeWeightedStockPriceVO(8,21.0));
		mapPricesForGBCEIndex.put("JOE", new VolumeWeightedStockPriceVO(10,15.0));
		mapPricesForGBCEIndex.put("ALE", new VolumeWeightedStockPriceVO(7,14.7));
		mapPricesForGBCEIndex.put("GIN", new VolumeWeightedStockPriceVO(1,1.2));
/*
		sumq	sumtxq	VWSP
TEA	10	2,5	25	10	25	2,5
POP	5	3	15			
POP	3	2	6	8	21	2,625
JOE	5	1	5			
JOE	5	2	10	10	15	1,5
ALE	3	2,1	6,3			
ALE	4	2,1	8,4	7	14,7	2,1
GIN	1	1,2	1,2	1	1,2	1,2
*/
	}

	@Test
	public void testRecordTrades() {
		assertTrue(trProcess.recordTrade(trades.get(0), db));
	}
	
	@Test
	public void testCalculateVolumneWeightedStockPrice() {
		

		Map<String, VolumeWeightedStockPriceVO> map;
		
		map = trProcess.calculateVolumneWeightedStockPrice(mapStock, trades, timeHorizonInMinutes*60);
		
		assertTrue(map.get("POP").returnVWSP() == expectedPOP);
		assertTrue(map.get("JOE").returnVWSP() == expectedJOE);
		assertTrue(map.get("TEA").returnVWSP() == expectedTEA);
		assertTrue(map.get("ALE").returnVWSP() == expectedALE);
		assertTrue(map.get("GIN").returnVWSP() == expectedGIN);
	}
	
	@Test
	public void testcalculateGBCEAllShareIndex() {
		
		double expected = 1.90069408528776;  // actual -> 1.9006940852877638 Using DELTA value
		double result = trProcess.calculateGBCEAllShareIndex(mapPricesForGBCEIndex);
		
		assertEquals(expected, result, DELTA);
		
	}

}
