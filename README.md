# Super Simple Stock Market

##Requirements
1. The Global Beverage Corporation Exchange is a new stock market trading in drinks companies.
  1. Your company is building the object-oriented system to run that trading.
  2. You have been assigned to build part of the core object model for a limited phase 1
2. Provide the source code for an application that will:-
  1. For a given stock,
    1. Given any price as input, calculate the dividend yield
    2. Given any price as input, calculate the P/E Ratio
    3. Record a trade, with timestamp, quantity, buy or sell indicator and price
    4. Calculate Volume Weighted Stock Price based on trades in past 5 minutes
  2. Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all

##Solution
It is an app using the Java console as a menu. During the first execution, some data are uploaded to the cache such as the  stockExchange values specified in the PDF assignment document. These data are in the file **data/DailyStockExchange.csv**. 

The solution is using a Facade pattern class which is calling several methods from 2 main classes (TradeProcessor and StockExchangeProcessor contained in the com.jpmorgan...engine package)

I am using Eclipse and you can upload to this IDE using the metadata uploaded in the master branch.

##Data
I am including several JUnit Tests for the five calculations. You can use < mvn clean test > from any IDE with the maven plugin or other test tool app such as Selenium or the Maven client tool.

##Comments
It is a Maven project, and I am trying to make an extensive use of Java 8 (streams and lambda expressions).

Several JUnit tests are included in order to test the main classes. It is possible to run using the TestSuite class AllTests.java.

##Detailed description
To run using the IDE, you can specify a new config file, in other case the app is assigning the default one for you.

After the execution of the **AppSuperSimpleStocks** class a menu will appear:

> For number 1 & 2, you have to specify stockSymbol and price

> Number 3 is loading trades from the data/trades.csv file

> Number 4 is mandatory to run number 3

> Number 5 is mandatory to run number 4

```
      Menu
-----------------------
1 - Calculate Dividend Yield(Using file data/DailyStockExchange.csv)
2 - Calculate the P/E Ratio(Using file data/DailyStockExchange.csv)
3 - Record a trade from csv file (Using file data/trades.csv)
4 - Calculate Volume Weighted Stock Price based on trades in past 5 minutes
5 - Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price
8 - Show trades recorded
9 - Quit
Select your choice -> 
```

##How to run from command windows
  You can use this command :
```  
  java -cp "stock.jar;lib/*" com.jpmorgan.supersimplestocks.AppSuperSimpleStocks conf/config.properties
```

##Libraries and framework used
- Java 8
- Maven tool
- JUnit  3.6
- Log4J 1.2.7
- Math3 v3
