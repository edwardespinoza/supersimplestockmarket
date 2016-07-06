package com.jpmorgan.supersimplestocks.engines;


import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.jpmorgan.supersimplestocks.enums.Currencies;
import com.jpmorgan.supersimplestocks.enums.StockTypes;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;


public class StreamProcessorTest {

//	private static final double DELTA = 1e-15d;
	
	StockExchangeProcessor seProcess = new StockExchangeProcessor ();
	StockExchangeVO seVOCommon;
	StockExchangeVO seVOPreferred;
	double price;
	double expectedDividendYield;
	
	@Before
	public void setUp(){
		
		double lastDividend = 8.0;
		double fixedDividend = 2.0;
		double parValue = 100;
		
		seVOCommon = new StockExchangeVO("POP", StockTypes.COMMON, new BigDecimal(lastDividend), null, null, Currencies.PENNY);
		seVOPreferred = new StockExchangeVO("POP", StockTypes.PREFERRED, new BigDecimal(lastDividend), new BigDecimal(fixedDividend), new BigDecimal(parValue), Currencies.PENNY);
		
		price = 2.0d;
		expectedDividendYield = 4.0d;
	}
	
	@Test
	public void testCalculateDividendYieldCommon() {
		
		//Testing calculateDividendYield 
		double expectedDividendYield = 4.0d;
		assertTrue (seProcess.calculateDividendYield(price, seVOCommon) == expectedDividendYield);
		
	}
	
	@Test
	public void testCalculateDividendYieldPreferred() {
		
		//Testing calculateDividendYield 
		double expectedDividendYield = 100.0d;
 		assertTrue (seProcess.calculateDividendYield(price, seVOPreferred) == expectedDividendYield);
		
	}
	
	@Test
	public void testCalculatePERatio() {
		
		//Testing calculateDividendYield 
		double expectedPERatio = 0.5d;
 		assertTrue (seProcess.calculatePERatio(price, seVOCommon) == expectedPERatio);
		
	}


	
	@After
	public void finalise(){
		
	}

	

}
