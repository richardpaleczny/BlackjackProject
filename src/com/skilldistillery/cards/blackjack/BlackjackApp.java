package com.skilldistillery.cards.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.cards.common.Card;

public class BlackjackApp {
	
	// For if the player wants to play again, determines a more appropriate menu printout
	public boolean seenOnce = false;
	
	public static void main(String[] args) {

		// Create *BlackjackApp* object to run its *run()*,
		// which starts the program
		BlackjackApp app = new BlackjackApp();
		
		app.run(app);

	}

	public void run(BlackjackApp app) {
		
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
		
		// After the initial dealing, determine whether player has blackjack. If so,
		// also determine if dealer has blackjack. If so, push, else player wins.
		// If neither, then ask player if they would like to hit or stay.
		gameLogic(playerHand, dealerHand, scanner, player, dealer, app, false);

	}

	public void introductionMenu(Scanner scanner) {

		while (true) {
			try {
				System.out.println("=== Blackjack ===");
				System.out.println("Welcome to the game of Blackjack");
				
				if (seenOnce == true) {
					System.out.print("Would you like to play again? (Y/N): ");
				} else {
					System.out.print("Would you like to play? (Y/N): ");
				}
				
				String input = scanner.next();
				
				// The first time the user sees the menu, it will print the *else* 
				// block in the prior if/else block - otherwise, set *seenOnce* to 
				// *true* to display "Would you like to play again?"
				seenOnce = true;
				if (input.equalsIgnoreCase("Y")) {
					return;
				} else if (input.equalsIgnoreCase("N")) {
					System.out.println("\nExiting program...");
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
			Scanner scanner, Player player, Dealer dealer, BlackjackApp app, boolean stay) {
		
		// The dealer's face-up card after initial dealing
		Card faceUpCard = dealerHand.getCards().get(0);

		if (playerHand.getHandValue() == 21 || stay == true) {
			
			// Set to true if dealer busts
			boolean dealerBust = false;
			
			System.out.println("\nYour hand has a value of "+ playerHand.getHandValue() 
				                 + ". \n\nDealer's turn...");
			
			// Show the player the dealer's initial cards
			Card holeCard = dealerHand.getCards().get(1);
			System.out.println("\nDealer's face-up card: " + faceUpCard);
			System.out.println("Dealer's hole card: " + holeCard);
			printHand(dealer, dealerHand);
			
			while (!(dealerHand.getHandValue() >= 17)) {
					System.out.println("\nDealer has to hit.\nDealer is hitting...");
					dealer.hit(dealer);
					printHand(dealer, dealerHand);
					if (dealerHand.getHandValue() > 21) {
						System.out.println("\nDealer has bust!");
						dealerBust = true;
					}
			}
			
			// This is the case if the sum of the dealer's initial hand is > 17 but also not
			// 21
			if (!(dealerHand.getHandValue() >= 21)) {
				System.out.println("\nDealer stays.");
			}
			
			// Player wins if dealer cannot get 21 - otherwise, the game is a draw
			if (dealerHand.getHandValue() != 21 && playerHand.getHandValue() >
			dealerHand.getHandValue() || dealerBust) {
				if (playerHand.getHandValue() == 21) {
					System.out.println("\n/////////////////////////////////////");
					System.out.println("You have blackjack! You win the game!");
					System.out.println("/////////////////////////////////////\n");
				} else {
					System.out.println("\n/////////////////////////////////////");
					System.out.println("You win the game!");
					System.out.println("/////////////////////////////////////\n");
				}
				app.run(app);
			} else if (dealerHand.getHandValue() > playerHand.getHandValue()){
				System.out.println("\n/////////////////////////////////////");
				System.out.println("Dealer's hand is greater than yours.\nDealer wins.");
				System.out.println("/////////////////////////////////////\n");
				app.run(app);
			} else {
				System.out.println("\n/////////////////////////////////////");
				System.out.println("Dealer has a hand value of " + dealerHand.getHandValue()
						+ "\nThe result is a push. Game is a draw.");
				System.out.println("/////////////////////////////////////\n");
				app.run(app);
			}
			
		} else {
			
			// In the case the player does not already have blackjack, run this logic
			System.out.println("\nDealer's face-up card: " + faceUpCard);
			
			while (true) {
				
				System.out.print("\nWould you like to hit or stay? (H/S) ");
				String input = scanner.next();
				
				if (input.equalsIgnoreCase("H")) {
					player.hit(dealer);
					printHand(player, playerHand);
					if (playerHand.getHandValue() > 21) {
						System.out.println("\nPlayer busts!\nGame Over!\n");
						// Run the whole application again, just in case the player
						// wants to play another round
						app.run(app);
					}
					gameLogic(playerHand, dealerHand, scanner, player, dealer, app, false);
				} else if (input.equalsIgnoreCase("S")) {
					gameLogic(playerHand, dealerHand, scanner, player, dealer, app, true);
				} else {
					System.out.println("\nInvalid input. Please enter (H/S).");
				}
				
			}
			
		}
		
	}
	
}
