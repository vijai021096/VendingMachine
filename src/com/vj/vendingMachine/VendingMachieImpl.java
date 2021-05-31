package com.vj.vendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VendingMachieImpl implements VendingMachine {

	private Inventory<Coins> cashInventory = new Inventory<Coins>();
	private Inventory<Item> itemInventory = new Inventory<Item>();
	private int totalSales;
	private Item currentItem;
	private int currentBalance;

	public VendingMachieImpl() {
		initialize();
	}

	public void initialize() {
		for (Coins coin : Coins.values()) {
			cashInventory.put(coin, 10);
		}
		for (Item item : Item.values()) {
			itemInventory.put(item, 10);
		}
	}

	public int getBalance() {
		return currentBalance;
	}

	@Override
	public void insertCoin(Coins coins) {
		// TODO Auto-generated method stub
		currentBalance = currentBalance + coins.getDenomination();
		cashInventory.add(coins);
	}

	@Override
	public List<Coins> refund() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getItemPrice(Item item) throws Exception {
		if (itemInventory.hasItem(item)) {
			currentItem=item;
			return currentItem.getPrice();
		}
		throw new Exception("Item Sold Out");
	}

	@Override
	public HashMap<Item, List<Coins>> getItemsAndChange() throws Exception {
		HashMap<Item,List<Coins>> mp = new HashMap<Item, List<Coins>>();
		Item item = collectItem();
		totalSales = totalSales + currentItem.getPrice();
		List<Coins> change = collectChange();
		mp.put(item, change);
		return mp;
	}

	private Item collectItem() throws Exception {
		if (isFullPaid()) {
			if (hasSufficientChange()) {
				itemInventory.deduct(currentItem);
				return currentItem;
			}
			throw new Exception("Not Sufficient change in Inventory");
		}
		
		throw new Exception("Price not paid fully");
	}

	private List<Coins> collectChange() throws Exception {
		long changeAmount = currentBalance - currentItem.getPrice();
		List<Coins> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		return change;
	}

	private void updateCashInventory(List<Coins> change) {
		// TODO Auto-generated method stub

		for (Coins c : change) {
			cashInventory.deduct(c);
		}
	}

	private boolean isFullPaid() {
		if (currentBalance >= currentItem.getPrice()) {
			return true;
		}
		return false;
	}

	private List<Coins> getChange(long amount) throws Exception {
		List<Coins> changes =new ArrayList<>();

		if (amount > 0) {
			
			long balance = amount;
			while (balance > 0) {
				 if (balance >= Coins.HUNDRED.getDenomination() && cashInventory.hasItem(Coins.HUNDRED)) {
						changes.add(Coins.HUNDRED);
						balance = balance - Coins.HUNDRED.getDenomination();
						continue;

					} 
				 else if (balance >= Coins.FIFTY.getDenomination() && cashInventory.hasItem(Coins.FIFTY)) {
						changes.add(Coins.FIFTY);
						balance = balance - Coins.FIFTY.getDenomination();
						continue;

					} 
				 else if (balance >= Coins.TWENTY.getDenomination() && cashInventory.hasItem(Coins.TWENTY)) {
						changes.add(Coins.TWENTY);
						balance = balance - Coins.TWENTY.getDenomination();
						continue;

					} 
				 else if (balance >= Coins.TEN.getDenomination() && cashInventory.hasItem(Coins.TEN)) {
					changes.add(Coins.TEN);
					balance = balance - Coins.TEN.getDenomination();
					continue;

				}
				else if (balance >= Coins.FIVE.getDenomination() && cashInventory.hasItem(Coins.FIVE)) {
					changes.add(Coins.FIVE);
					balance = balance - Coins.FIVE.getDenomination();
					continue;

				}else {
					throw new Exception("NotSufficientChange Please try another product");
				}
			}
		}

		return changes;
	}

	public void printStats() {
		System.out.println("Total Sales : " + totalSales);
		System.out.println("Current Item Inventory : " + itemInventory.getMap());
		System.out.println("Current Cash Inventory : " + cashInventory.getMap());
	}

	private boolean hasSufficientChange() {
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}

	private boolean hasSufficientChangeForAmount(long amount) {
		boolean hasChange = true;
		try {
			getChange(amount);
		} catch (Exception nsce) {
			return hasChange = false;
		}

		return hasChange;
	}

	public long getTotalSales() {
		return totalSales;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

		cashInventory.clear();
		itemInventory.clear();
		totalSales = 0;
		currentItem = null;
		currentBalance = 0;

	}

}
