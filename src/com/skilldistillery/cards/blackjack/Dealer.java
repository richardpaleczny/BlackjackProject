package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class Dealer extends Player {

	private Deck deck;

	{
		deck = new Deck();
	}

	public Dealer() {
		super();
	}

	public Card dealCards() {

		return deck.dealCard();

	}

	public void shuffle() {
		System.out.println("\nDealer is shuffling cards...");
		deck.shuffle();
	}

	@Override
	public String toString() {
		return "=== Dealer's Hand === " + "\n" + getBjh().toString();
	}

	public int checkDeckSize() {
		return deck.checkDeckSize();
	}

	@Override
	void hit(Dealer dealer) {
		
		getBjh().addCard(dealer.dealCards());
		
	}
	
}
