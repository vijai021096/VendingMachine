package com.vj.vendingMachine;

import java.util.List;
import java.util.Map;

public interface VendingMachine {

	public void insertCoin(Coins coins);
	
	public List<Coins> refund();
	
	public int getItemPrice(Item item) throws Exception;
	
	public Map<Item,List<Coins>> getItemsAndChange() throws Exception;
	
	public void reset();
	
}
