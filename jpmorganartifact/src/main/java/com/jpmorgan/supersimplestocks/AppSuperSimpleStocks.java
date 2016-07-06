package com.jpmorgan.supersimplestocks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmorgan.supersimplestocks.caches.Context;
import com.jpmorgan.supersimplestocks.caches.Database;
import com.jpmorgan.supersimplestocks.engines.FileIngestion;
import com.jpmorgan.supersimplestocks.exceptions.TradeListEmptyException;
import com.jpmorgan.supersimplestocks.facades.SuperSimpleStockFacade;
import com.jpmorgan.supersimplestocks.vos.StockExchangeVO;
import com.jpmorgan.supersimplestocks.vos.TradeVO;
import com.jpmorgan.supersimplestocks.vos.VolumeWeightedStockPriceVO;


public class AppSuperSimpleStocks {

	private static final Logger logger = LoggerFactory.getLogger(AppSuperSimpleStocks.class);

	static Scanner input = new Scanner(System.in);
	
	public static Context context = null;
	
	//Database
	Database db = new Database();
	
	public static void main(String[] args) {

		//Usage: java -cp "stock.jar;lib/*" com.jpmorgan.supersimplestocks.AppSuperSimpleStocks
		
		// log4j properties file
		String log4jConfPath = "conf/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

		// Newsletters's configuration application file
		String confFile;
		if (args.length != 1) {
			logger.warn("A config file is expected as argument. Using default file, conf/config.properties");
			confFile = "conf/config.properties";
		} else {
			confFile = args[0];
			logger.debug(confFile);
		}

		// Getting context
		
		try {
			context = new Context(confFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final String FILENAME = context.getString("FILENAME");
		
		//loading Stock Exchange
		context.loadStockExchange(FILENAME);
		
		logger.debug("TRADES' FILENAME: " + FILENAME);
		logger.debug("TRADE'S HEADER: " + context.getString("HEADER"));

		boolean case3selected = false;
		boolean case4selected = false;
		
		while (true) {
			int choice = menu();
			switch (choice) {
			case 1:
				System.out.println(callCalculateDividendYield());
				break;
			case 2:
				System.out.println(callCalculatePERatio());
				break;
			case 3:
				case3selected = true;
				System.out.println(callRecordTrade(context.getString("FILENAME_TRADES")));
				break;
			case 4:
				if (case3selected == false) {
					System.out.println("\n You need to execute number 3. Record trades from csv file.");
					break;
				}
				case4selected = true;
				int minutes = 5; //Last 5 minutes
				callCalculateVWSP(minutes*60);
				break;
			case 5:
				if (case4selected == false) {
					System.out.println("\n You need to execute number 4. Calculate VWSP.");
					break;
				}
				callCalculateGBCEAllShareIndex();
				break;				
			case 8:
				showTradesRecorded();
				break;	
			case 9:
				System.exit(0);
				break;
			default:
				logger.error("INVALID OPTION SELECTED");
				// The user input an unexpected choice.
			}
		}

	}

	public static int menu() {

		int selection;

		System.out.println("         Menu");
		System.out.println("-----------------------\n");
		System.out.println("1 - Calculate Dividend Yield" + "(Using file data/DailyStockExchange.csv)");
		System.out.println("2 - Calculate the P/E Ratio" + "(Using file data/DailyStockExchange.csv)");
		System.out.println("3 - Record a trade from csv file " + "(Using file data/trades.csv)");
		System.out.println("4 - Calculate Volume Weighted Stock Price based on trades in past 5 minutes");
		System.out.println("5 - Calculate the GBCE All Share Index using the geometric mean of the VWSP");		
		System.out.println("8 - Show trades recorded");
		System.out.println("9 - Quit");
		System.out.print("\nSelect your choice -> ");
		selection = input.nextInt();
		return selection;
	}
		
	public static String callCalculateDividendYield() {

		double price;
		String stockSymbol;

		System.out.print("INSERT STOCK SYMBOL: ");
		stockSymbol = input.next();
		System.out.print("INSERT THE PRICE: ");
		price = input.nextDouble();
		
		if (price > 0.0){
			StockExchangeVO seVO = context.getStockExchange().get(stockSymbol);
			if (seVO == null) return "\nERROR: StockSymbol has not been found.";
			return "\nDividend Yield is -> " + String.valueOf(SuperSimpleStockFacade.callCalculateDividendYield(price, seVO));
		}
		else{
			return "\n\nERROR: Price must be greater than Zero.";
		}
		
	}
	
	public static String callCalculatePERatio() {

		double price;
		String stockSymbol;

		System.out.print("INSERT STOCK SYMBOL: ");
		stockSymbol = input.next();
		System.out.print("INSERT THE PRICE: ");
		price = input.nextDouble();
		
		if (price > 0.0){
			StockExchangeVO seVO = context.getStockExchange().get(stockSymbol);
			if (seVO == null) return "\nERROR: StockSymbol has not been found.";
			return "\nP/E Ratio is -> " + String.valueOf(SuperSimpleStockFacade.callCalculatePERatio(price, seVO));
		}
		else{
			return "\nERROR: Price must be greater than Zero.";
		}
		
	}
	
	public static boolean callRecordTrade(final String FILENAME) {

		List<TradeVO> trades = null;
		boolean ok = false;
		//Reading file 
		try {
			trades = FileIngestion.getListFromCSV(FILENAME, Integer.parseInt(context.getString("LIMIT_STREAM_LIST")));
			//Record trades in the context. TODO Derby ??
			System.out.println("\nTRADES TO SAVE: ");
			if (trades != null)	trades.forEach(trade -> System.out.println(trade));
			ok = SuperSimpleStockFacade.callRecordTrades(trades, AppSuperSimpleStocks.context.getDatabase());
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (TradeListEmptyException e) {
			logger.error(e.getMessage());
		}
		return ok;

	}
	
	public static void showTradesRecorded() {
		context.getDatabase().getTrades().forEach(trade -> System.out.println(trade));
		return;
	}
	
	public static void callCalculateVWSP(int seconds) {
		Map<String, VolumeWeightedStockPriceVO> mapVWSP;
		
		mapVWSP = SuperSimpleStockFacade.callCalculateVolumneWeightedStockPrice(context.getStockExchange(), context.getDatabase().getTrades(), seconds);
		System.out.println("");
		
		if (mapVWSP != null){
			mapVWSP.forEach((k,v)->{
				System.out.println("STOCK_SYMBOL : " + k + " VWSP : " + v.returnVWSP());	
			});
		}
		
		AppSuperSimpleStocks.context.setMapVWSP(mapVWSP);
		return ;
	}
	
	public static void callCalculateGBCEAllShareIndex() {
		double result;
		result = SuperSimpleStockFacade.callCalculateGBCEAllShareIndex(AppSuperSimpleStocks.context.getMapVWSP());
		System.out.println("\nGBCEAllShareIndex is -> " + result);
	}

}
