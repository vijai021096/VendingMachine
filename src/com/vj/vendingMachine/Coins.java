package com.vj.vendingMachine;

public enum Coins {
      ONE(1),FIVE(5),TEN(10),TWENTY(20),FIFTY(50),HUNDRED(100);
	
	private int denomination;

	Coins(int i) {
		this.denomination=i;
	}

	public int getDenomination() {
		return denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}
	
	
}
