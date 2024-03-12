package main;

import java.util.Scanner;
import java.util.ArrayList;

public class Craps {
	public static Scanner scnr = new Scanner(System.in);
	public static Die firstDie = new Die(6);
	public static Die secondDie = new Die(6);
	public static int rollTotal;
	
	public static Player player1, player2, player3, player4, player5, player6;
	public static ArrayList<Player> playerArray = new ArrayList<Player>();
	public static ArrayList<Integer> bankRollArray = new ArrayList<Integer>();
	public static ArrayList<Integer> betAmountArray = new ArrayList<Integer>();
	public static int numberOfPlayers = 0;
	public static int pointRoll;
	public static int comeOut;
	public static int actionAmount;
	public static int actionCoverage;
	public static int[] comeOutPass = {7,11};
	public static int[] comeOutCrap = {2,3,12};
	public static String gameStage;
	public static int shooterID = 0;
	
	public static int textSpeed = 5;
	public static void printMessage(String inputString) {
		
		for(int i = 0;i < inputString.length(); i++) {
			System.out.print(inputString.charAt(i));
			try {
				Thread.sleep(textSpeed);
			} catch (InterruptedException ie){
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void printMessageln(String inputString) {
		for(int i = 0;i < inputString.length(); i++) {
			System.out.print(inputString.charAt(i));
			try {
				Thread.sleep(textSpeed);
			} catch (InterruptedException ie){
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
	
	public static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException ie){
			Thread.currentThread().interrupt();
		}
	}

	
	public static void main(String[]args) {	
		//Welcome message here
		printMessageln("Welcome to Casino Fanshawe! The game here is craps, so we need to get some information about your party...\n");
		
		// Setup players:
		// Get the number of players
		// Instantiate the player objects into playerArray in
		//  accordance with the number of players
		// For each player object set a name and set a bankIndex
		//  to interface with each array with the correct index
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
		while(!CrapsHelper.checkForWinner(bankRollArray)) {
			
			// Print who the shooter is and get their bet
			actionAmount = CrapsHelper.getShooterBet();
			
			// Query other players to meet the action amount
			CrapsHelper.queryActionEnguagement();
			
			//Roll the dice
			CrapsHelper.rollComeOut();
			
			CrapsHelper.comeOutResult();
			
			//Temporary break to avoid infinite loop
			break;
			
		} 
		printMessageln("Game complete.");
		
		
	}//End of main method
} //End of class
 