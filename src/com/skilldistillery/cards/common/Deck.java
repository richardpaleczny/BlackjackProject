//	We will be simulating a deck of cards.
//	
//	Create a class Deck. It will hold a List of Cards.
//	In the constructor, initialize the List with all 52 cards.
//	Add a method checkDeckSize which returns the number of cards still in the deck.
//	Add a method dealCard that removes a Card from the deck.
//	Add a method shuffle to shuffle the deck.

package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> deck;

	{
		deck = new ArrayList<>();
	}

	// Create constructor, initialize list with all 52 cards
	// Automatically creates a *deck* since we have an "init block"
	public Deck() {

		// Add 52 cards to deck
		addToDeck();

	}

	// Create loops to step through enum Class values and populate *deck*
	// *ArrayList* for all suits and ranks
	private void addToDeck() {

		Suit[] suitArray = Suit.values();
		Rank[] rankArray = Rank.values();
		for (Suit suit : suitArray) {
			for (Rank rank : rankArray) {
				deck.add(new Card(suit, rank));
			}
		}

	}

	public int checkDeckSize() {

		// Return number of cards still in the deck
		int numCardsLeft = deck.size();

		return numCardsLeft;
	}

	// Removes a card from the deck
	public Card dealCard() {

		return deck.remove(0);

	}

	// Shuffle the deck
	public void shuffle() {

		Collections.shuffle(deck);

	}

}
