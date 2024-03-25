/**
 * Program Name:	Craps.java
 * Purpose:			Simulates the game of Craps
 * @author			Aaron Hylands, 0740727
 * Date:			April 1, 2024 (DUE)
 */

package main;

import java.util.*;

public class Craps {
		



	public static int actionCoverage; // Make non-global
	

	
	
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
		int numberOfPlayers = CrapsHelper.getNumberOfPlayers();
		ArrayList<Player> playerArray = CrapsHelper.configurePlayerArray(numberOfPlayers);
		System.out.println(playerArray.toString());
		// For each player, setup the starting amount of money
		//  inside of bankRollArray with the players bankIndex
		ArrayList<Integer> bankRollArray = CrapsHelper.configureBankRollArray(playerArray, numberOfPlayers);
		ArrayList<Integer> betAmountArray = CrapsHelper.configureBetAmountArray(playerArray);

		// Printing bank roll totals and players for debug reasons
		CrapsHelper.printPlayerBankBalances(playerArray, bankRollArray);
		
		// Query if the players would like to see a brief description
		//  of the rules
		CrapsHelper.queryRules();
		
		int shooterID = 0;
		// Start the main game loop
		do {
			
			// Print who the shooter is and get their bet
			CrapsHelper.actionAmount = CrapsHelper.getShooterBet(playerArray, bankRollArray, betAmountArray, shooterID);
			betAmountArray = CrapsHelper.addShooterBetToArray(CrapsHelper.actionAmount, shooterID, betAmountArray);

			// Query other players to meet the action amount
			betAmountArray = CrapsHelper.getOpponentBet(playerArray, bankRollArray, betAmountArray, shooterID);
			//Roll the dice
			int comeOut = CrapsHelper.rollComeOut();
			
			// Figure out the outcome of the come out roll
			int pointGoal = CrapsHelper.comeOutResult(playerArray, comeOut, shooterID);
			
			// Adjust bank balances according to the come out and clear betArray
			//  or continue to point roll stage 
			bankRollArray = CrapsHelper.adjustBankBalances(playerArray, bankRollArray, betAmountArray, shooterID, numberOfPlayers);
			betAmountArray = CrapsHelper.clearBetAmountArray(betAmountArray);
			
			// Check to see if any of the players are out after the round of betting
			CrapsHelper.checkForBust(playerArray, bankRollArray, betAmountArray);
			
			//Check for a winner after bank adjustment
			CrapsHelper.gameIsDone = CrapsHelper.checkForWinner(playerArray, bankRollArray, numberOfPlayers);
			
			// Only run this part of the game if shooting for point
			while (CrapsHelper.shootingForPoint) {
				// Roll again 
				int pointRoll = CrapsHelper.rollForPoint();
				
				//check point roll outcome
				CrapsHelper.pointRollResult(playerArray, shooterID, pointRoll, pointGoal);
				
				//adjust bankrollArray accordingly depending on the point roll
				bankRollArray = CrapsHelper.adjustBankBalances(playerArray, bankRollArray, betAmountArray, shooterID, numberOfPlayers);
				betAmountArray = CrapsHelper.clearBetAmountArray(betAmountArray);
				
				// Check to see if any of the players are out after the round of betting
				playerArray = CrapsHelper.checkForBust(playerArray, bankRollArray, betAmountArray);
				
				//Check for a winner after bank adjustment
				CrapsHelper.gameIsDone = CrapsHelper.checkForWinner(playerArray, bankRollArray, numberOfPlayers);
				
				
			}
			// Print totals before queryPass if the game has not ended
			if (!CrapsHelper.gameIsDone) {
				CrapsHelper.printMessageln("\nAfter this pass, here are the bankroll balances for everyone:");
				CrapsHelper.printPlayerBankBalances(playerArray, bankRollArray);
			}
			
			// Get next shooter if game is not done and the current
			//  shooter decides to pass
			if (!CrapsHelper.gameIsDone) {
				if (CrapsHelper.queryPass(playerArray, shooterID)) {
					shooterID = CrapsHelper.getNextShooter(playerArray, shooterID, numberOfPlayers);
				}
			}
			
		} while (!CrapsHelper.gameIsDone); // Game loop end
		
		// Launch celebration message for the winner
		CrapsHelper.launchCelebration();
		
		
	}//End of main method
} //End of class
 