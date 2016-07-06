package com.jpmorgan.supersimplestocks.vos;

public class VolumeWeightedStockPriceVO {

	protected String stockSymbol;
	protected int sumQuantity = 0;
	protected double sumPrice = 0.0;
	protected double sumQuantityxPrice = 0.0; 
	
	public VolumeWeightedStockPriceVO(){
		
	}
	
	public VolumeWeightedStockPriceVO(int sumQuantity, double sumQuantityxPrice){
		this.sumQuantity = sumQuantity;
		this.sumQuantityxPrice = sumQuantityxPrice;
	}
	
	public void setSumQuantity(int sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
	public void setSumQuantityxPrice(double sumQuantityxPrice) {
		this.sumQuantityxPrice = sumQuantityxPrice;
	}
	
	public int getSumQuantity() {
		return sumQuantity;
	}
	public double getSumPrice() {
		return sumPrice;
	}

	public double getSumQuantityxPrice() {
		return sumQuantityxPrice;
	}

	public void incrementPriceXQuantity(double price, int quantity) {
		this.sumQuantity = this.sumQuantity + quantity;
		this.sumPrice = this.sumPrice + price;
		this.sumQuantityxPrice = this.sumQuantityxPrice + (price*quantity); 
	}
	
	public double returnVWSP(){
		if (sumQuantity > 0) return sumQuantityxPrice / sumQuantity;
		return 0;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	
}
