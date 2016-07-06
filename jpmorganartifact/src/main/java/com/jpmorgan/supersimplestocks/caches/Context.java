package com.jpmorgan.supersimplestocks.caches;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jpmorgan.supersimplestocks.engines.FileIngestion;
import com.jpmorgan.supersimplestocks.enums.Currencies;
import com.jpmorgan.supersimplestocks.enums.StockTypes;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;
import com.jpmorgan.supersimplestocks.vos.VolumeWeightedStockPriceVO;

public class Context {

	private Properties prop;

	private Map<String, StockExchangeVO> stockExchange;
	
	private Map<String, VolumeWeightedStockPriceVO> mapVWSP; 
	
	public Map<String, VolumeWeightedStockPriceVO> getMapVWSP() {
		return mapVWSP;
	}

	public void setMapVWSP(Map<String, VolumeWeightedStockPriceVO> mapVWSP) {
		this.mapVWSP = mapVWSP;
	}

	private Database database = new Database(); //Containing trades

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public Context(String file) throws FileNotFoundException, IOException {
		prop = new Properties();
		InputStream is = null;
		is = new FileInputStream(file);
		prop.load(is);
	}

	public String getString(String key) {
		return prop.getProperty(key);
	}

	/**
	 * You can load the new stockExchange whenever it is necessary
	 * 
	 * @param FILENAME
	 */
	public void loadStockExchange(String FILENAME) {
		// Read Stock Exchange
		Stream<String> stream = FileIngestion.getStream(FILENAME);
		// ToMap
		this.stockExchange = stream.map(mapToStockExchange).limit(100)
				.collect(Collectors.toMap(StockExchangeVO::getStockSymbol, se -> se));
	}

	protected Function<String, StockExchangeVO> mapToStockExchange = (line) -> {
		String[] s = line.split(",");
		return new StockExchangeVO(s[0], StockTypes.valueOf(s[1]), new BigDecimal(s[2]),
				s[3].length() > 0 ? new BigDecimal(s[3]) : null, new BigDecimal(s[4]), Currencies.PENNY);
	};
	
		

	public Map<String, StockExchangeVO> getStockExchange() {
		return stockExchange;
	}


}
