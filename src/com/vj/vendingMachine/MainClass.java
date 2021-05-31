package com.vj.vendingMachine;

public class MainClass {

	public static void main(String ab[]) throws Exception {
		VendingMachieImpl vm = new VendingMachieImpl();
		
		//Inserting 100.
		vm.insertCoin(Coins.HUNDRED);
		
		//Purchasing coke for 25rs.
		vm.getItemPrice(Item.COKE);
		System.out.println(vm.getItemsAndChange());
		
	   //printing sales details
	   vm.printStats();
	   
	   //Inserting 50.
	   vm.insertCoin(Coins.FIFTY);
	   //Purchasing Sprite
	   vm.getItemPrice(Item.SPRITE);
	   System.out.println(vm.getItemsAndChange());

	   //Printing sales details
	   vm.printStats();

	}
}
