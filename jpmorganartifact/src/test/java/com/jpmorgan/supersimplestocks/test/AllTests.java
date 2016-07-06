package com.jpmorgan.supersimplestocks.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jpmorgan.supersimplestocks.engines.StockExchangeProcessorTest;
import com.jpmorgan.supersimplestocks.engines.TradeProcessorTest;

@RunWith(Suite.class)
@SuiteClasses({ StockExchangeProcessorTest.class, TradeProcessorTest.class})
public class AllTests {

}
