package com.jpmorgan.supersimplestocks.vos;

import java.math.BigDecimal;

import com.jpmorgan.supersimplestocks.enums.Currencies;
import com.jpmorgan.supersimplestocks.enums.StockTypes;

public class StockExchangeVO {

	protected String stockSymbol;
	protected StockTypes type;
	protected BigDecimal lastDividend;
	protected BigDecimal fixedDividend;
	protected BigDecimal parValue;

	protected Currencies currency = Currencies.PENNY;

	public StockExchangeVO(){
		
	}
	
	public StockExchangeVO(String stockSymbol, StockTypes type, BigDecimal lastDividend, BigDecimal fixedDividend,
			BigDecimal parValue) {
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;

	}

	public BigDecimal getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public StockExchangeVO(String stockSymbol, StockTypes type, BigDecimal lastDividend, BigDecimal fixedDividend,
			BigDecimal parValue, Currencies currency) {
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.currency = currency;

	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("STOCK SYMBOL: ");
		sb.append(stockSymbol);
		sb.append(" |STOCK TYPE: ");
		sb.append(type);
		sb.append(" |LAST DIVIDEND: ");
		sb.append(lastDividend);
		sb.append(" |FIXED DIVIDEND: ");
		sb.append(fixedDividend);
		sb.append(" |PAR VALUE: ");
		sb.append(parValue);
		sb.append(" |CURRENCY: ");
		sb.append(currency);		
		return sb.toString();
	}
	

	public Currencies getCurrency() {
		return currency;
	}

	public void setCurrency(Currencies currency) {
		this.currency = currency;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockTypes getType() {
		return type;
	}

	public void setType(StockTypes type) {
		this.type = type;
	}



}
