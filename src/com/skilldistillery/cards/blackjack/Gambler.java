package com.skilldistillery.cards.blackjack;

public class Gambler extends Player {

	public Gambler() {
		super();
	}

	@Override
	public String toString() {
		
		return "=== Your Current Hand === " + "\n" + getBjh().toString();
		
	}

	@Override
	void hit(Dealer dealer) {
		
		getBjh().addCard(dealer.dealCards());
		
	}

}
