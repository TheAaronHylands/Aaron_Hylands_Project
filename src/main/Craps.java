/**
 * Program Name:	Craps.java
 * Purpose:			Simulates the game of Craps
 * @author			Aaron Hylands, 0740727
 * Date:			April 1, 2024 (DUE)
 */

package main;

import java.util.*;

public class Craps {
		

	public static ArrayList<Player> playerArray = new ArrayList<Player>(); // Make non-global
	public static ArrayList<Integer> bankRollArray = new ArrayList<Integer>(); // Make non-global
	public static ArrayList<Integer> betAmountArray = new ArrayList<Integer>(); // Make non-global
	public static int numberOfPlayers = 0; // Make non-global
	public static int pointRoll; // Make non-global
	public static int pointGoal; // Make non-global
	public static int comeOut; // Make non-global
	public static int actionCoverage; // Make non-global
	public static int shooterID = 0; // Make non-global
	public static boolean didShooterWin; //Move to CrapsHelper
	public static boolean didShooterCrap; //Move to CrapsHelper
	public static boolean shootingForPoint; //Move to CrapsHelper or make non-global
	public static boolean gameIsDone; //Move to CrapsHelper or make non-global
	
	
	public static void main(String[]args) {
		
		//load UI
		//		 \  /  \  /
		//Disable \/this\/ via // to play the game in the console
		CrapsUI.initGameWindow();
		
		//Print the welcome message
		CrapsHelper.printWelcomeMessage();

		// Setup players:
		// Get the number of players
		// Instantiate the player objects into playerArray in
		//   accordance with the number of players
		// For each player object set a name and set a bankIndex
		//   to interface with each array with the correct index
		CrapsHelper.configurePlayerArray();
		
		// For each player, setup the starting amount of money
		//  inside of bankRollArray with the players bankIndex
		CrapsHelper.configureBankRollArray();
		
		// Printing bank roll totals and players for debug reasons
		CrapsHelper.printPlayerBankBalances();
		
		// Query if the players would like to see a brief description
		//  of the rules
		CrapsHelper.queryRules();
		
		// Start the main game loop
		do {
			
			// Print who the shooter is and get their bet
			CrapsHelper.actionAmount = CrapsHelper.getShooterBet();
			
			// Query other players to meet the action amount
			CrapsHelper.getOpponentBet();
			
			//Roll the dice
			CrapsHelper.rollComeOut();
			
			// Figure out the outcome of the come out roll
			CrapsHelper.comeOutResult();
			
			// Adjust bank balances according to the come out 
			//  or continue to point roll stage 
			CrapsHelper.adjustBankBalances();
			
			// Check to see if any of the players are out after the round of betting
			CrapsHelper.checkForBust();
			
			//Check for a winner after bank adjustment
			gameIsDone = CrapsHelper.checkForWinner();
			
			// Only run this part of the game if come out did not fail or succeed
			while (shootingForPoint) {
				// Roll again 
				CrapsHelper.rollForPoint();
				
				//check outcome
				CrapsHelper.pointRollResult();
				
				//adjust bankrollArray accordingly depending on the roll
				CrapsHelper.adjustBankBalances();
				
				// Check to see if any of the players are out after the round of betting
				CrapsHelper.checkForBust();
				
				//Check for a winner after bank adjustment
				gameIsDone = CrapsHelper.checkForWinner();
				
				
			}
			// Print totals before queryPass if the game has not ended
			if (!gameIsDone) {
				CrapsHelper.printMessageln("\nAfter this pass, here are the bankroll balances for everyone:");
				CrapsHelper.printPlayerBankBalances();
			}
			
			// Get next shooter if game is not done and the current
			//  shooter decides to pass
			if (!gameIsDone) {
				if (CrapsHelper.queryPass()) {
					CrapsHelper.getNextShooter();
				}
			}
			
		} while (!gameIsDone); // Game loop end
		
		// Launch celebration message for the winner
		CrapsHelper.launchCelebration();
		
		
	}//End of main method
} //End of class
 