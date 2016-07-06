package com.jpmorgan.supersimplestocks.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jpmorgan.supersimplestocks.engines.StreamProcessorTest;
import com.jpmorgan.supersimplestocks.engines.TradeProcessorTest;

@RunWith(Suite.class)
@SuiteClasses({ StreamProcessorTest.class, TradeProcessorTest.class})
public class AllTests {

}
