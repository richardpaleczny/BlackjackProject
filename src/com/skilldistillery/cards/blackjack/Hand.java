package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.cards.common.Card;

public abstract class Hand {

	private List<Card> cards;
	
	{
		cards = new ArrayList<>();
	}
	
	public Hand() {

	}

	// Must be implemented by subclasses
	public abstract int getHandValue();

	public void addCard(Card card) {
		
		cards.add(card);

	}

	public void clearHand() {

	}

	public List<Card> getCards() {
		return cards;
	}

	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		// Shows all cards in the hand, each on a new line
		for (int i = 0; i < cards.size(); i++) {
			sb.append(cards.get(i)).append("\n");
		}
		
		return sb.toString();
	}

}
