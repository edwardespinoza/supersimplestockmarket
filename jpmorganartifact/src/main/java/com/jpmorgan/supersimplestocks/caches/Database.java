package com.jpmorgan.supersimplestocks.caches;

import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.supersimplestocks.vos.TradeVO;

public class Database {

	List<TradeVO> trades = new ArrayList<TradeVO>();

	public List<TradeVO> getTrades() {
		return trades;
	}

	public void setTrades(List<TradeVO> trades) {
		this.trades = trades;
	}
	
	public void addTrade(TradeVO vo) {
		trades.add(vo);
	}
	
}
