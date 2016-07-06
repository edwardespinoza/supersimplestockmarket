# supersimplestockmarket
supersimplestockmarket

Requirements
1. The Global Beverage Corporation Exchange is a new stock market trading in drinks companies.
a. Your company is building the object-oriented system to run that trading.
b. You have been assigned to build part of the core object model for a limited phase 1
2. Provide the source code for an application that will:-
a. For a given stock,
i. Given any price as input, calculate the dividend yield
ii. Given any price as input, calculate the P/E Ratio
iii. Record a trade, with timestamp, quantity, buy or sell indicator and price
iv. Calculate Volume Weighted Stock Price based on trades in past 5 minutes
b. Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all

Solution
It is an app, building a menu using the Java console, uploading to cache a stockExchange values from a file (data/DailyStockExchange.csv). The same as contained in the example.
We can get any calculation using a Facade class which is calling  several methods from 2 main classes (TradeProcessor and StockExchangeProcessor)

Data
I am including several JUnit Tests for the five calculations. You can use < mvn clean test > from any IDE with the maven plugin or other test tool app such as Selenium or the Maven client tool.

Comments
It is a Maven project, and I am trying to implement an extensive use of Java 8 (streams and lambda expressions) and there are few frameworks used in this solution (Junit, Log4J, Apache Math3).
Several JUnits are included in order to test the main 'processor' classes. It is possible to run using the TestSuite class AllTests.java.

Detailed description
To run using an IDE, you have to specify the conf/config.properties file, if not the app will assign you the default one.

After the execute the AppSuperSimpleStocks class a menu will appear:
For number 1 & 2, you have to specify stockSymbol and price
Number 3 is loading trades from the data/trades.csv file
Number 4 is mandatory to run number 3
Number 5 is mandatory to run number 4

      Menu
-----------------------
1 - Calculate Dividend Yield(Using file data/DailyStockExchange.csv)
2 - Calculate the P/E Ratio(Using file data/DailyStockExchange.csv)
3 - Record a trade from csv file (Using file data/trades.csv)
4 - Calculate Volume Weighted Stock Price based on trades in past 5 minutes
5- Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price
8 - Show trades recorded
9 - Quit

How to run from command windows
  You can use this command :
  java -cp "stock.jar;lib/*" com.jpmorgan.supersimplestocks.AppSuperSimpleStocks conf/config.properties

Libraries and framework
- Java 8
- Maven tool
- JUnit  3.6
- Log4J 1.2.7
- Math3 v3
