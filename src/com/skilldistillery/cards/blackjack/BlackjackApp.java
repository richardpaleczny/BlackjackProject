package com.skilldistillery.cards.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.cards.common.Card;

public class BlackjackApp {

	public static void main(String[] args) {

		// Create *BlackjackApp* object to run its *run()*,
		// which starts the program
		BlackjackApp app = new BlackjackApp();

		app.run();

	}

	public void run() {
		// Have the scanner here, and we can pass it to methods as needed
		Scanner scanner = new Scanner(System.in);

		// Create a welcome menu, introducing the player to the game.
		// We will also check if the player wishes to play or not.
		introductionMenu(scanner);

		// Create the players; the gambler and the dealer, to start
		Gambler player = new Gambler();
		Dealer dealer = new Dealer();

		// Get both the player's hand and the dealer's hand. Store in respective
		// variables.
		BlackjackHand playerHand = player.getBjh();
		BlackjackHand dealerHand = dealer.getBjh();

		// Dealer has to shuffle the deck first
		dealer.shuffle();

		// Deal two cards to both the player and the dealer, to start.
		// The dealer has the deck and deals the cards. Cards are removed from the
		// dealer's deck.
		dealInitialCards(dealer, playerHand, dealerHand);

		// Show the player their hand after the initial dealing and the value of their
		// current hand as well
		printHand(player, playerHand);

//		int test = 21;
//		int test2 = 21;
//		if (test == 21 && test2 != 21) { 
//			System.out.println("\n/////////////////////////////////////");
//			System.out.println("You have blackjack! You win the game!");
//			System.out.println("/////////////////////////////////////");
//		} else if (test == 21 && test2 == 21) {
//			System.out.println("\n/////////////////////////////////////");
//			System.out.println("Your hand has a value of 21.\nChecking dealer's hand..." 
//							 + "\nDealer also has a hand value of 21."
//							 + "\nThe result is a push. Game is a draw.");
//			System.out.println("/////////////////////////////////////");
//		}
		
		// After the initial dealing, determine whether player has blackjack. If so,
		// also determine if dealer has blackjack. If so, push, else player wins.
		// If neither, then ask player if they would like to hit or stay.
		gameLogic(playerHand, dealerHand, scanner, player, dealer);

		// Set the second card dealer dealt to itself as dealer's "hole" card
		Card holeCard = dealerHand.getCards().get(1);

	}

	public void introductionMenu(Scanner scanner) {

		while (true) {
			try {
				System.out.println("=== Blackjack ===");
				System.out.println("Welcome to the game of Blackjack");
				System.out.print("Would you like to play? (Y/N): ");
				String input = scanner.next();
				if (input.equalsIgnoreCase("Y")) {
					return;
				} else if (input.equalsIgnoreCase("N")) {
					System.out.println("Exiting program...");
					System.exit(0);
				} else {
					System.out.println("\nInvalid input. Please enter (Y/N).\n");
				}
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void dealInitialCards(Dealer dealer, BlackjackHand playerHand, BlackjackHand dealerHand) {

		System.out.println("\nDealer deals out initial cards...");
		for (int i = 0; i < 2; i++) {
			playerHand.addCard(dealer.dealCards());
			dealerHand.addCard(dealer.dealCards());
		}

	}
	
	public void printHand(Player gamblerOrDealer, BlackjackHand gamblerOrDealerHand) {
		System.out.println();
		System.out.println("-------------------------");
		System.out.println(gamblerOrDealer);
		if (gamblerOrDealer instanceof Gambler) {
			System.out.println("Your hand's value: " + gamblerOrDealerHand.getHandValue());
		} else if (gamblerOrDealer instanceof Dealer) {
			System.out.println("Dealer's hand value: " + gamblerOrDealerHand.getHandValue());
		}
		System.out.println("-------------------------");
	}
	
	public void gameLogic(BlackjackHand playerHand, BlackjackHand dealerHand,
			Scanner scanner, Player player, Dealer dealer) {
		
		// The dealer's face-up card after initial dealing
		Card faceUpCard = dealerHand.getCards().get(0);
		
		if (playerHand.getHandValue() == 21 && dealerHand.getHandValue() != 21) { 
			System.out.println("\n/////////////////////////////////////");
			System.out.println("You have blackjack! You win the game!");
			System.out.println("/////////////////////////////////////");
		} else if (playerHand.getHandValue() == 21 && dealerHand.getHandValue() == 21) {
			System.out.println("Your hand has a value of 21.\nChecking dealer's hand..." 
					+ "\nDealer also has a hand value of 21."
					+ "\nThe result is a push. Game is a draw.");
			System.out.println("/////////////////////////////////////");
		} else {
			System.out.println("\nDealer's face-up card: " + faceUpCard);
			System.out.print("\nWould you like to hit or stay? (H/S) ");
			String input = scanner.next();
			if (input.equalsIgnoreCase("H")) {
				player.hit(dealer);
				System.out.println(dealer.checkDeckSize());
				printHand(player, playerHand);
			}
		}		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
