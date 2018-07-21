package com.skilldistillery.cards.blackjack;

public abstract class Player {
	
	private BlackjackHand bjh;
	
	{
		bjh = new BlackjackHand();
	}
	
	public Player() {
		
	}
	
	
	public void stay() {
		
	}

	public BlackjackHand getBjh() {
		return bjh;
	}
	
	public String toString() {
		
		return "=== Player's Hand === " + "\n" + bjh.toString();
		
	}
	
	abstract void hit(Dealer dealer);
	
}
