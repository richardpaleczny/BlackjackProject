//	Create a class called Card.
//	
//	A card has a Suit and Rank. Set these in the constructor.
//	Generate the methods hashCode and equals
//	Add a toString which says rank + " of " + suit.
//	Add a method getValue to return the card's numeric value.

package com.skilldistillery.cards.common;

public class Card {
	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (!(rank == Rank.KING || rank == Rank.QUEEN || rank == Rank.JACK || rank == Rank.ACE)) {
			builder.append(rank.getValue() + " of " + suit.toString());
			return builder.toString();

		} else {
			builder.append(rank.getName() + " of " + suit.toString());
			return builder.toString();
		}
	}

	public int getValue() {
		return this.rank.getValue();
	}
}
