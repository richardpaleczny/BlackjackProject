package com.skilldistillery.cards.common;

public enum Rank {

    TWO("Two", 2), THREE("Three", 3), FOUR("Four", 4), FIVE("Five", 5), SIX("Six", 6), 
    SEVEN("Seven", 7), EIGHT("Eight", 8), NINE("Nine", 9), TEN("Ten", 10), 
    JACK("Jack", 10), QUEEN("Queen", 10), KING("King", 10), ACE("Ace", 11);
    
    private int value;
    private String name;

    private Rank(int value) {
      this.value = value;
    }
    
    private Rank(String name, int value) {
    	this.value = value;
    	this.name = name;
    }
    
    public int getValue() {
      return value;
    }

	public String getName() {
		return name;
	}
    
}