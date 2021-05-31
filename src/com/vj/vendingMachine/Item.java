package com.vj.vendingMachine;

public enum Item {
 
	COKE(25),PEPSI(35),UP(40),SPRITE(50),MAAZA(75),MOUNTAINDEW(100);
	
	private int price;

	Item(int i) {
		this.price=i;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
