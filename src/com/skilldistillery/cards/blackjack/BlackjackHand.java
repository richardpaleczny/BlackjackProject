package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;

public class BlackjackHand extends Hand {

	public BlackjackHand() {

	}

	@Override
	public int getHandValue() {

		int total = 0;
		// Note: The *getCards()* is retrieving BlackjackHand's own list of cards
		// which is the field that was inherited from *Hand*
		for (Card card : getCards()) {
			total += card.getValue();
		}
		
		return total;
	}

}
