package com.jpmorgan.supersimplestocks.vos;

import java.time.Duration;
import java.time.LocalDateTime;

public class TradeVO {

	
	protected String stockSymbol; // ISIN, others
	protected LocalDateTime tradeDate; // Original trade date
	protected LocalDateTime tradeModificationDate; // To amends or corrections
	protected int quantity;
	protected double price;
	protected String sideIndicator; // Buy(B) or sell(S)
	
	public static final String BUY_INDICATOR = "B";
	public static final String SELL_INDICATOR = "S";

	protected String discardReason;
	protected boolean isValidTrade = false;

	public TradeVO(){
		
	}
	
	public TradeVO(String stockSymbol, LocalDateTime tradeDate, LocalDateTime tradeModificationDate, int quantity,
			double price, String sideIndicator) {
		this.stockSymbol = stockSymbol;
		this.tradeDate = tradeDate;
		this.tradeModificationDate = tradeModificationDate;
		this.quantity = quantity;
		this.price = price;
		this.sideIndicator = sideIndicator;
			
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("InstrumentID: ");
		sb.append(stockSymbol);
		sb.append(" |TradeDate: ");
		sb.append(tradeDate);
		sb.append(" |QUANTITY: ");
		sb.append(quantity);
		sb.append(" |PRICE: ");
		sb.append(price);
		sb.append(" |sideIndicator: ");
		sb.append(sideIndicator);
		return sb.toString();
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String instrumentID) {
		this.stockSymbol = instrumentID;
	}

	public boolean isFromTimeHorizon(int seconds) {
		if (Duration.between(tradeDate,LocalDateTime.now()).getSeconds() <= seconds) return true;
		return false;
	}

	
	public LocalDateTime getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDateTime tradeDate) {
		this.tradeDate = tradeDate;
	}

	public LocalDateTime getTradeModificationDate() {
		return tradeModificationDate;
	}

	public void setTradeModificationDate(LocalDateTime tradeModificationDate) {
		this.tradeModificationDate = tradeModificationDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public String getSideIndicator() {
		return sideIndicator;
	}

	public void setSideIndicator(String sideIndicator) {
		this.sideIndicator = sideIndicator;
	}

	public String getDiscardReason() {
		return discardReason;
	}

	public void setDiscardReason(String discardReason) {
		this.discardReason = discardReason;
	}

	public boolean isValidTrade() {
		return isValidTrade;
	}

	public void setValidTrade(boolean isValidTrade) {
		this.isValidTrade = isValidTrade;
	}

}
