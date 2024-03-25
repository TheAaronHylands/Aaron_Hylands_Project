/**
 * This is my CrapsHelper class.
 * This class holds all of the methods used in Craps.java as well
 *  as some methods not used in Craps.Java
 *  
 * There are some variables only used in this class that I have
 *  declared as private static. Usually it is above the method
 *  that it is being used in.
 *  
 * I tried to be as thorough as possible in providing comments as
 *  to the function of each method and what it does.
 */


package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class CrapsHelper {
	
	public static Scanner input = new Scanner(System.in);
	public static int actionAmount;
	
	
	// Custom implementation global values
	public static Die firstDie = new Die(6);
	public static Die secondDie = new Die(6);
	public static Player winner;
	public static Player player1, player2, player3, player4, player5, player6;
	public static boolean didShooterWin; 
	public static boolean didShooterCrap; 
	public static boolean shootingForPoint; 
	public static boolean gameIsDone; 
	 
	
	//	   			   \  /
	// This is used for \/ most of the message delays. Default(3000)
	private static int longPause = 3000;
	
	//				   \  /
	// This is used for \/ the point roll message added to longPause to allow for reading time. Default (2000)
	private static int mediumPause = 2000;
	
	//					\  /
	// This is the pause \/ in the welcome message. Default(750)
	private static int welcomeMessagePause = 750;
	

	
//===================================================================================	
	
	/**
	Name: 		printMessage & printMessageln
	Parameters:	String inputString
	Return:		void
	Purpose:	This is a custom method that I made originally for my text based adventure game,
	   			 (The Caves of Amnesia) so that text would print onto the screen one character at a time. 
	   			 I felt that the typing animation of the characters added immersion, and something much 
	   			 more stimulating for the brain to experience.
	*/
	
	//Text speed can be set to 0 for instant printing. Default(5)
//						\     /    ___  ___     ___     __   __   ___  ___  __
//                  	 \   /      |  |__  \_/  |     /__` |__) |__  |__  |  \
//                  	  \ /       |  |___ / \  |     .__/ |    |___ |___ |__/  
	public static int textSpeed = 5;

	public static void printMessage(String inputString) {
		
		for(int i = 0;i < inputString.length(); i++) {
			System.out.print(inputString.charAt(i));
			if(CrapsUI.gameWindow.isVisible()) {
				CrapsUI.outputText.setText(CrapsUI.outputText.getText() + inputString.charAt(i));
			}
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
			if(CrapsUI.gameWindow.isVisible()) {
				CrapsUI.outputText.setText(CrapsUI.outputText.getText() + inputString.charAt(i));
			}
			try {
				Thread.sleep(textSpeed);
			} catch (InterruptedException ie){
				Thread.currentThread().interrupt();
			}
		}
		if(CrapsUI.gameWindow.isVisible()) {
			CrapsUI.outputText.setText(CrapsUI.outputText.getText() + "\n");
		}
		System.out.println();
	}
	
	/**
	Name: 		sleep
	Parameters:	int sleepTime
	Return:		void
	Purpose:	This method is used to add pauses in executon to improve user expereince
	*/
	
	public static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException ie){
			Thread.currentThread().interrupt();
		}
	}
	
//===================================================================================	

	/**
	Name: 		printWelcomeMessage
	Parameters:	N/A
	Return:		void
	Purpose:	This method prints an ASCII art welcome message
	*/
	
	public static void printWelcomeMessage() {
		sleep(welcomeMessagePause);
		printMessageln("       _______       _______");
		printMessageln("     /\\       \\     /       /\\");
		printMessageln("    /()\\   ()  \\   /  ()   /()\\");
		printMessageln("   /    \\_______\\ /_______/    \\");
		printMessageln("   \\    /()     / \\     ()\\    /");
		printMessageln("    \\()/   ()  /   \\  ()   \\()/");
		printMessageln("     \\/_____()/     \\()_____\\/");
		printMessageln("       ___       __   __         ___");
		printMessageln(" |  | |__  |    /  ` /  \\  |\\/| |__  ");
		printMessageln(" |/\\| |___ |___ \\__, \\__/  |  | |___ ");
		printMessageln("___  __      __        __          __ ");
		printMessageln(" |  /  \\    /  `  /\\  /__` | |\\ | /  \\");
		printMessageln(" |  \\__/    \\__, /~~\\ .__/ | | \\| \\__/");
		printMessageln(" ___            __                  ___");
		printMessageln("|__   /\\  |\\ | /__` |__|  /\\  |  | |__ ");
		printMessageln("|    /~~\\ | \\| .__/ |  | /~~\\ |/\\| |___");
		printMessageln("");
		printMessageln("");
		sleep(welcomeMessagePause);
	}
	
//===================================================================================

	/**
	Name: 		getNumberOfPlayers
	Parameters:	N/A
	Return:		int numberOfPlayers
	Purpose:	This method sets up the number of players and returns the number
	*/
	
	public static int getNumberOfPlayers() {
		int numberOfPlayers = 0;
		printMessage("Enter number of players(2-6): ");
		while(numberOfPlayers < 2 || numberOfPlayers > 6) {
			if(CrapsUI.gameWindow.isVisible()) {
				CrapsUI.showPlayerSelect();
				CrapsUI.waitForInput();
				numberOfPlayers = CrapsUI.numberOfPlayersInputFromUI;
			} else {
				try {
					numberOfPlayers = input.nextInt();
				} catch(InputMismatchException ime) {
					printMessageln("Invalid.");
					input.nextLine();
				}
				if (numberOfPlayers < 2 || numberOfPlayers > 6) {
					printMessageln("Invalid.");
					printMessage("Enter the number of players for this game (minimum of 2 to maximum of 6):");
				}
			
			}
			
		}
		if(CrapsUI.gameWindow.isVisible()) {
			System.out.println(numberOfPlayers);
			CrapsUI.clearTextOutput();
			CrapsUI.submitNamesButton.setVisible(true);
			CrapsUI.hidePlayerSelect();
		}
		return numberOfPlayers;
	}
	
//===================================================================================
	
	/**
	Name: 		configurePlayerArray
	Parameters:	int numberOfPlayers
	Return:		ArrayList<Player> playerArray
	Purpose:	This method takes the users input and assigns the value of 2-6 to numberOfPlayers.
				The method then calls setupPlayerArray() where the player objects are loaded.
				At the end of the method is a loop that utilizes inputCheck() to ensure the user 
				 didn't make any typing mistakes when entering the name.
	*/
	
	private static String nameHolder;
	
	public static ArrayList<Player> configurePlayerArray(int numberOfPlayers) {
		
		// initalize player array
		ArrayList<Player> playerArray = setupPlayerArray(numberOfPlayers);
		
		// This portion is for setting the names of the players
		if(!CrapsUI.gameWindow.isVisible()) {	
			input.nextLine();
			playerArray.forEach((player) -> {
				do {
					System.out.println();
					printMessage("Player " 
							+ player.getNumber()
							+ " Name: ");
					
					nameHolder = input.nextLine();
				}while(!inputCheck(nameHolder,player.getNumber()));
				
				player.setName(nameHolder);
				
				
			});
		} else {
			
			CrapsUI.waitForInput();
			CrapsUI.hidePlayerNameInput();
			
			CrapsUI.outputText.setText("");
			CrapsUI.outputText.setVisible(true);
		}

		System.out.println();//for formatting
		return playerArray;
		
	}
	
//===================================================================================	

	/**
	Name: 		configureBankRollArray
	Parameters:	ArrayList<Player> playerArray, int numberOfPlayers
	Return:		ArrayList<Integer> bankRollArray
	Purpose:	This method takes in the playerArray and the number of players and configures
				 the bankRollArray accordingly
	*/
	
	public static ArrayList<Integer> configureBankRollArray(ArrayList<Player> playerArray, int numberOfPlayers){
		ArrayList<Integer> bankRollArray = new ArrayList<Integer>();
		
		playerArray.forEach((player) -> {
			bankRollArray.add(player.getBankRollIndex(), 100);
			//move to its own method
		});
		if (CrapsUI.gameWindow.isVisible()) {
			CrapsUI.configureBankDisplay(playerArray, numberOfPlayers);
			CrapsUI.showBankDisplay();
		}
		
		return bankRollArray;
		
	}
		
	
//===================================================================================	

	/**
	Name: 		configureBetAmountArray
	Parameters:	ArrayList<Player> playerArray
	Return:		ArrayList<Integer> betAmountArray
	Purpose:	This method takes in the playerArray and creates and returns the betAmount array  
	*/
	public static ArrayList<Integer> configureBetAmountArray(ArrayList<Player> playerArray) {
		ArrayList<Integer> betAmountArray = new ArrayList<Integer>();
		
		playerArray.forEach((player) -> {
			betAmountArray.add(player.getBankRollIndex(), 0);
			//move to its own method
		});
		
		
		return betAmountArray;
	}
	
//===================================================================================
	
	/**
	Name: 		printPlayerBankBalances
	Parameters:	ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray
	Return:		void
	Purpose:	This method prints the players number, their name, and their bankroll amount.
				It also updates the bank display if the UI is enabled.
	*/
	
	public static void printPlayerBankBalances(ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray) {
		
		playerArray.forEach((player) -> {
			printMessageln("Player " + (playerArray.indexOf(player) + 1) 
					+ " - " + player.getName()
					+ " -  Life Savings: $" 
					+ bankRollArray.get(player.getBankRollIndex()));
		});
		if(CrapsUI.gameWindow.isVisible()) {
			CrapsUI.updateBankDisplay(playerArray, bankRollArray);
			sleep(longPause);
			CrapsUI.clearTextOutput();
		}
		
	}//printPlayerBankBalances() end
	
//===================================================================================	

	/**
	Name: 		inputCheck
	Parameters:	String inputString, int inputInt
	Return:		boolean
	Purpose:	This method returns a boolean value depending on the users input. 
				It provides the string that the user inputted and queries a y or n. 
				This method does not return true unless y is inputted, any other 
				 input is assumed to be a no. 
				If an empty name is provided then the method returns false and 
				 prints a reminder to the console to provide a name.
	*/
	
	public static boolean inputCheck(String inputString,int inputInt) {
		
		if(inputString.trim().length() < 1) {
			printMessageln("Please input a name.");
			return false;
		}
		
		String userResponse;
		printMessageln("Your input: " + inputString.trim());
		printMessage("Verify y/n: ");
		userResponse = input.nextLine();
		if(userResponse.toLowerCase().trim().equals("y")) {
			return true;
		} else {
			return false;
		}
		
	}
	
//===================================================================================	

	/**
	Name: 		setupPlayerArray
	Parameters:	numberOfPlayers
	Return:		ArrayList<Player> playerArray
	Purpose:	This method takes the numberOfPlayers and loads the ArrayList of playerArray 
				 with the requisite amount of Player objects. 
				These player objects hold the players name, and this method of loading the 
				 array allows for standardized indexing of each player (player1 is always 
				 in index 0, player2 index 1 etc.)
				I used a list of if statements to allow for adding onto the array as needed
				 for the amount of players. (If there are only two players then only the first 
				 if statement runs, creating and placing the player object into the array. If 
				 the numberOfPlayers is 3 then the second if statement runs adding a third 
				 player etc.)
				
	*/
	
	public static ArrayList<Player> setupPlayerArray(int numberOfPlayers) {
		ArrayList<Player> playerArray = new ArrayList<Player>();
		if(numberOfPlayers == 2 || numberOfPlayers > 1 ) {
			player1 = new Player(1,0);
			playerArray.add(player1);
			player2 = new Player(2,1);
			playerArray.add(player2);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player1NameInput.setVisible(true);
				CrapsUI.player1NameInput.setFontColorToPromptColor();
				CrapsUI.player2NameInput.setVisible(true);
				CrapsUI.player2NameInput.setFontColorToPromptColor();
			}
		}
		if(numberOfPlayers == 3 || numberOfPlayers > 2) {
			player3 = new Player(3,2);
			playerArray.add(player3);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player3NameInput.setVisible(true);
				CrapsUI.player3NameInput.setFontColorToPromptColor();
			}
		}
		if(numberOfPlayers == 4 || numberOfPlayers > 3) {
			player4 = new Player(4,3);
			playerArray.add(player4);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player4NameInput.setVisible(true);
				CrapsUI.player4NameInput.setFontColorToPromptColor();
			}
		}
		if(numberOfPlayers == 5 || numberOfPlayers > 4) {
			player5 = new Player(5,4);
			playerArray.add(player5);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player5NameInput.setVisible(true);
				CrapsUI.player5NameInput.setFontColorToPromptColor();
			}
		}
		if(numberOfPlayers == 6 || numberOfPlayers > 5) {
			player6 = new Player(6,5);
			playerArray.add(player6);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player6NameInput.setVisible(true);
				CrapsUI.player6NameInput.setFontColorToPromptColor();
			}
		}
		
		return playerArray;
	}
	
//===================================================================================	

	/**
	Name: 		queryRules
	Parameters:	N/A
	Return:		void
	Purpose:	This method is for printing the rules if the user doesn't know how to play the game.
				If the user inputs that they know the rules, this method instead prints a message 
				 acknowledging that and stating that it's time for the game to start.
	*/
	
	public static String yesNo;
	public static void queryRules() {
		System.out.println();
		if(!CrapsUI.gameWindow.isVisible()) {
			printMessage("Do you know the rules? y/n: ");
			String userInput = input.nextLine();
			switch(userInput.toLowerCase().trim()) {
				case"n":{
					printRules();
					break;
				}
				case"y":{
					printMessageln("OK, you know the rules...let's play some craps!");
					System.out.println();
				}
		
			}
		} else {
			
			printMessage("          Do you know the rules?");
			CrapsUI.showYesNoButtons();
			CrapsUI.waitForInput();
			CrapsUI.hideYesNoButtons();
			switch(yesNo) {
				case"yes":{
					CrapsUI.clearTextOutput();
					printMessageln("Now that the boring stuff is out of the way. Let's play some craps!\n");
					break;
				}
				case"no":{
					CrapsUI.clearTextOutput();
					CrapsUI.showRulesWindow();
					CrapsUI.waitForInput();
					CrapsUI.hideRulesWindow();
					break;
				}
			
			}
		}
		
		
	}
	
//===================================================================================	
	
	/**
	Name: 		printRules
	Parameters:	N/A
	Return:		void
	Purpose:	This prints the rules to the console
	*/
	
	public static void printRules() {
		printMessageln("Objective: Players place bets and roll two six sided dice to determine the outcome.\r\n"
				+ "\r\n"
				+ "Setup: A “shooter” is designated, this shooter will roll the dice each round until they either lose by running out of money, or decide to pass the dice to the next player at the end of the round. A round ends once the shooter wins or loses.\r\n"
				+ "\r\n"
				+ "Betting: The shooter places a starting bet amount called the “action amount” into the “pot”(The place where the bets are held). After the action amount is set, each player in turn places their own bets in the pot in order to “cover” or meet the action amount. (eg. If an action amount of $100 is placed, the next non-shooter player can only  put forwards up to $100, if they do so they will cover the bet and no other players can place bets. If they only put $50 in, then the rest of the players must cover the rest of the bet if able.) If the players cannot cover the entire action amount, the action that is not covered will be returned to the shooter before rolling the “come out”.\r\n"
				+ "\r\n"
				+ "Rolling: Once bets are placed, the shooter rolls what is called the “come out” roll. It is important to note that the dice must be rolled and bounced off of a wall to ensure fair rolling. If the shooter rolls a total of 7 or 11, the shooter wins and gets all of the money in the pot. If the shooter rolls a 2, 3, or 12 then the shooter loses, and each player gets twice their bet amount from the pot. If the shooter rolls a 4, 5, 6, 8, 9 or 10, said roll is set as the “point”, and the point phase is entered.\r\n"
				+ "\r\n"
				+ "Point Phase: If the point phase is entered, the shooter continuously rolls the dice until they either roll the point set in the come out, or a 7. If the shooter rolls the point then they win the bets, and if they roll a 7 then they lose the bet.\r\n"
				+ "\r\n"
				+ "Next Shooter: After the bets have been settled and the shooter either wins or loses, as long as the shooter still has money left they can choose to shoot again. Alternatively they can choose to pass the dice to the next player, and that player is now the shooter. As long as there is more than one player with money left, the game continues and the shooter then rolls a come out roll.\r\n"
				+ "\r\n"
				+ "Winning or Losing: If a player runs out of money, then they lose and are out of the game. Once a player has acquired all of the money from the other players then they win!\r\n"
				+ "\r\n"
				+ "Now that you know the rules, lets play some craps!!"
				+ "\n\n");
	}
	
//===================================================================================	
	//⚀ ⚁ ⚂ ⚃ ⚄ ⚅ <- Tried adding these but the swing textArea won't support it.
	
	/**
	Name: 		rollDice
	Parameters:	N/A
	Return:		int rollTotal
	Purpose:	This method runs the .roll() method for each Die object and returns
				 the total amount.
	*/
	
	public static int rollDice() {
		
		int rollTotal = firstDie.roll() + secondDie.roll();
		
		return rollTotal;
		
	}
	
//===================================================================================

	/**
	Name: 		rollComeOut
	Parameters:	N/A
	Return:		int comeOut
	Purpose:	This method rolls the comeOut roll from the shooter and returns it.
				I utilize my sleep() method between each period in the message 
				 to allow for dramatic tension to build while waiting for the roll.
	*/
	
	// This is the delay \/ between periods when rolling. Default(500)
	private static int dramaticPause = 500;
	public static int rollComeOut() {
		
		
		printMessage("***** Rolling the dice");
		int comeOut = rollDice();
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessageln(" " + comeOut + "! *****");
		System.out.println();
		
		return comeOut;
		
	}
	
//===================================================================================	
	//()
	/* 
	 */
	
	/**
	Name: 		rollForPoint
	Parameters:	N/A
	Return:		int pointRoll
	Purpose:	This method is the same as rollComeOut but has slightly different word 
				 formatting while retaining the dramatic tension of the comeOut roll. 
				It assigns the roll to pointRoll and returns it
	*/
	
	// This is the pause \/ after each point roll. Default(750)
	private static int rollForPointPause = 750;
	public static int rollForPoint() {
		
		printMessage("Rolling");
		sleep(dramaticPause);
		int pointRoll = rollDice();
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessageln(" " + pointRoll);
		timesRolledForPoint++;
		sleep(rollForPointPause);
		return pointRoll;
	}
	
//===================================================================================	

	/**
	Name: 		pointRollResult
	Parameters:	ArrayList<Player> playerArray, int shooterID, int pointRoll, int pointGoal
	Return:		void
	Purpose:	This method determines the outcome of the point roll with the value stored within pointRoll
				I use the same combination of boolean values to determine the outcome of each point roll so that 
				 the same method adjustBankBalances can be used.
	*/
	
	public static void pointRollResult(ArrayList<Player> playerArray, int shooterID, int pointRoll, int pointGoal) {
		
		switch(pointRoll) {
			case 7:{
				printMessageln("Sorry, " + playerArray.get(shooterID).getName() +
						", you rolled a seven. You lose...");
				didShooterWin = false;
				didShooterCrap = true;
				shootingForPoint = false;
				if(CrapsUI.gameWindow.isVisible()) {
					sleep(longPause);
					CrapsUI.clearTextOutput();
				}
				break;
			}
			default:{
				//putting this in default since case arguments have to be constant
				/* This allows me to utilize the switch as well as an if to determine outcome, in this case
				 *  I am checking if pointRoll equals pointGoal
				 */
				if(pointRoll == pointGoal) {
					printMessageln("\nCongratulations " + playerArray.get(shooterID).getName() 
							+ "! You have rolled " + pointGoal + ". You win!");
					didShooterWin = true;
					didShooterCrap = false;
					shootingForPoint = false;
					if(CrapsUI.gameWindow.isVisible()) {
						sleep(longPause);
						CrapsUI.clearTextOutput();
					}
					break;
				} else {
					didShooterWin = false;
					didShooterCrap = false;
					shootingForPoint = true;
					break;
				}
			}
		
		}
		System.out.println();
	}
	
//===================================================================================	

	/**
	Name: 		comeOutResult
	Parameters:	ArrayList<Player> playerArray, int comeOut, int shooterID
	Return:		int pointGoal
	Purpose:	This method handles the result of the comeOut roll with a switch case.
				Depending on the outcome a combination of the three boolean values 
				 didShooterWin and didShooterCrap are set.
				With the combined outcome of these boolean values, I determine the 
				 outcome of the roll in adjustBankBalances().
				Additionally the boolean value shootingForPoint determines whether 
				 the shooting for point loop is entered or not. 
				This is also where pointGoal is set.
	*/
	
	private static int timesRolledForPoint;//also used in adjustBankBalances
	public static int comeOutResult(ArrayList<Player> playerArray, int comeOut, int shooterID) {
		int pointGoal;
		switch(comeOut) {
			case 2: {
				printMessageln("Sorry, " + playerArray.get(shooterID).getName() +
						", you rolled a two. You lose...");
				System.out.println();
				didShooterWin = false;
				didShooterCrap = true;
				shootingForPoint = false;
				if(CrapsUI.gameWindow.isVisible()) {
					sleep(longPause);
					CrapsUI.clearTextOutput();
				}
				break;
			} 
			case 3: {
				printMessageln("Sorry, " + playerArray.get(shooterID).getName() +
						", you rolled a three. You lose...");
				System.out.println();
				didShooterWin = false;
				didShooterCrap = true;
				shootingForPoint = false;
				if(CrapsUI.gameWindow.isVisible()) {
					sleep(longPause);
					CrapsUI.clearTextOutput();
				}
				break;
			}
			case 12: {
				printMessageln("Sorry, " + playerArray.get(shooterID).getName() +
						", you rolled a twelve. You lose...");
				System.out.println();
				didShooterWin = false;
				didShooterCrap = true;
				shootingForPoint = false;
				if(CrapsUI.gameWindow.isVisible()) {
					sleep(longPause);
					CrapsUI.clearTextOutput();
				}
				break;
			}
			case 11://This is here because 11 and 7 have the same outcome
			case 7: {
				printMessageln("Congratulations " + playerArray.get(shooterID).getName() 
						+ "! You rolled a natural. You win!");
				System.out.println();
				didShooterWin = true;
				didShooterCrap = false;
				shootingForPoint = false;
				if(CrapsUI.gameWindow.isVisible()) {
					sleep(longPause);
					CrapsUI.clearTextOutput();
				}
				break;
			}
			default: {// This is when the shooter neither craps or wins on their comeOut
				
				timesRolledForPoint = 0;
				pointGoal = comeOut; // This is where point goal is set
				printMessageln("OK " + playerArray.get(shooterID).getName() + ", your point is " 
						+ pointGoal + ". To win, you need to roll your point again, but if you roll a seven, you lose.");
				didShooterWin = false;
				didShooterCrap = false;
				shootingForPoint = true;
				if(CrapsUI.gameWindow.isVisible()) {
					sleep(longPause + mediumPause);
					CrapsUI.clearTextOutput();
				}
				return pointGoal;
			}
			
		}// Switch comeOut end
		
		return 0;// This is here so that the compiler doesn't complain about potentially not returning
		
	}
//===================================================================================	

	/**
	Name: 		clearBetAmountArray
	Parameters:	ArrayList<Integer> betAmountArray
	Return:		ArrayList<Integer> betAmountArray
	Purpose:	This method clears the betAmount array after the bankRoll adjustments are complete
	*/
	
	public static ArrayList<Integer> clearBetAmountArray(ArrayList<Integer> betAmountArray) {
		betAmountArray.forEach((bet) -> {
			betAmountArray.set(betAmountArray.indexOf(bet), 0);
		});
		return betAmountArray;
	}
	
//===================================================================================	

	/**
	Name: 		adjustBankBalances
	Parameters:	ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray, int shooterID, int numberOfPlayers
	Return:		ArrayList<Integer> bankRollArray
	Purpose:	This method adjusts the bankRollArray according to the roll result
	*/
	
	public static ArrayList<Integer> adjustBankBalances(ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray, int shooterID, int numberOfPlayers) {
		if (!didShooterWin && didShooterCrap) {//Shooter craps out 
			
			playerArray.forEach((player) -> {
				
				if (player.getBankRollIndex() == shooterID) {
					//remove bet from bankroll
					bankRollArray.set(player.getBankRollIndex(), (bankRollArray.get(player.getBankRollIndex())) - betAmountArray.get(player.getBankRollIndex()));

				} else {
					//Award money
					bankRollArray.set(player.getBankRollIndex(), (bankRollArray.get(player.getBankRollIndex())) + betAmountArray.get(player.getBankRollIndex()));

				}
			});

			//For debug purposes while developing to determine accuracy of array changes.

			
			if(CrapsUI.gameWindow.isVisible()) {
				CrapsUI.updateBankDisplay(playerArray, bankRollArray);
			}
			return bankRollArray;
			
		} else if (didShooterWin && !didShooterCrap) {// Shooter wins

			playerArray.forEach((player) -> {

				if (player.getBankRollIndex() == shooterID) {// I have to split this into two seperate methods so each is returned and modified accordingly

					//Award money
					bankRollArray.set(player.getBankRollIndex(), (bankRollArray.get(player.getBankRollIndex())) + betAmountArray.get(player.getBankRollIndex()));

				} else {

					//remove bet from bank
					bankRollArray.set(player.getBankRollIndex(), (bankRollArray.get(player.getBankRollIndex())) - betAmountArray.get(player.getBankRollIndex()));

				}
				
			});

			
			if(CrapsUI.gameWindow.isVisible()) {
				CrapsUI.updateBankDisplay(playerArray, bankRollArray);
			}
			return bankRollArray;
			
		} else if (!didShooterCrap && !didShooterWin) {// This runs when shooting for point
			if((timesRolledForPoint % 3) == 0 && timesRolledForPoint != 0) {
				if (CrapsUI.gameWindow.isVisible()) {
					CrapsUI.clearTextOutput();
				}
			}
			if (timesRolledForPoint < 5) {
				printMessageln("Rolling again to try for your point...");
			} else if (timesRolledForPoint < 10) {
				printMessageln("Impressive, let's keep trying...");
			} else if (timesRolledForPoint < 15) {
				printMessageln("This is getting weird...");
			} else if (timesRolledForPoint < 20) {
				printMessageln("How is this even possible???...");
			} else if (timesRolledForPoint < 25) {
				printMessageln("You know what, I give up, you win.");
				sleep(longPause);
				winner = playerArray.get(shooterID);
				shootingForPoint = false;
				bankRollArray.set(shooterID, (numberOfPlayers * 100));
				CrapsUI.clearTextOutput();
			}
			
		}
		
		//update bank display with new totals
		
		return bankRollArray;
		
	}
	
//===================================================================================	
	
	/**
	Name: 		checkForBust
	Parameters:	ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray
	Return:		ArrayList<Player> playerArray
	Purpose:	This method checks if any players have bust, if so it runs the Player
	 			 method of gameOver() and sets the players isOut boolean to true. 
	 			I check this value when determining whether a player can interact with 
	 			 the game with betting or shooting.
	*/
	
	public static ArrayList<Player> checkForBust(ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray) {
		// check if any players are out of the game
			bankRollArray.forEach((account) -> {
				
				if (account == 0 && (betAmountArray.get(bankRollArray.indexOf(account)) == 0 )){
					
					playerArray.get(bankRollArray.indexOf(account)).gameOver();
				}
				
			});
			
		return playerArray;
	}
	
//===================================================================================	

	/**
	Name: 		checkForWinner
	Parameters:	ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, int numberOfPlayers
	Return:		boolean
	Purpose:	This method checks for a winner, if there is one it points winner 
				 at the winning player and returns the boolean value of winnerDecided. 
				This gets applied to boolean gameIsDone which controls whether the 
				 main game loop runs or not
	*/
	
	private static boolean winnerDecided;
	public static int totalMoney;
	
	public static boolean checkForWinner(ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, int numberOfPlayers) {
		winnerDecided = false;
		
		totalMoney = numberOfPlayers * 100;
		
		bankRollArray.forEach((bankAmount) -> {
			if (bankAmount == totalMoney) {
				winnerDecided = true;
				winner = playerArray.get(bankRollArray.indexOf(bankAmount));
			} 
		});
		
		return winnerDecided;
		
	}

//===================================================================================
	
	/**
	Name: 		queryPass
	Parameters:	ArrayList<Player> playerArray, int shooterID
	Return:		boolean
	Purpose:	This method queries the shooter at the end of the round if they would
				 like to shoot again or pass the dice to the next player who can shoot
	*/
	
	private static String passResponse;
	public static boolean willPass;
	
	public static boolean queryPass(ArrayList<Player> playerArray, int shooterID) {
		System.out.println();
		willPass = true;
		if(!CrapsUI.gameWindow.isVisible()) {
			input.nextLine();
		}
		if (!playerArray.get(shooterID).hasLost()) {
			if(!CrapsUI.gameWindow.isVisible()) {
				printMessage(playerArray.get(shooterID).getName() 
						+ ", do you want to roll again or pass the dice? "
						+ "Enter Y to shoot again or enter P to pass the dice to the next shooter: ");
				passResponse = input.nextLine();
				if (passResponse.toLowerCase().trim().equals("y")) {
					willPass = false;
				}
				System.out.println();
			} else {
				printMessage("\n" + playerArray.get(shooterID).getName() 
						+ ", do you want to roll again or pass the dice? ");
				CrapsUI.showQueryPassButtons();
				CrapsUI.waitForInput();
				CrapsUI.hideQueryPassButtons();
				CrapsUI.clearTextOutput();
			}
		}
		return willPass;
		
	}
	
//===================================================================================
	
	/**
	Name: 		getNextShooter
	Parameters:	ArrayList<Player> playerArray, int shooterID, int numberOfPlayers
	Return:		void
	Purpose:	Gets the next available shooter from the list of players and assigns 
				 the shooterID accordingly
	*/
	
	public static int getNextShooter(ArrayList<Player> playerArray, int shooterID, int numberOfPlayers) {//aka find next available shooter
		int index;
		
		if ((shooterID + 1) > (numberOfPlayers - 1)) {
			index = 0;
		} else {
			index = shooterID + 1;
		}
			
		
		for(int i = index; i <= (numberOfPlayers - 1); i++) {
			
			if (!playerArray.get(i).hasLost()) {
				shooterID = i;
				break;
			}
			
		}
		
		return shooterID;
	}
	
//===================================================================================
	
	/**
	Name: 		getShooterBet
	Parameters:	ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray, int shooterID
	Return:		int shooterBetInput
	Purpose:	This method sets the actionAmount from user input based on some restrictions. 
				These restrictions include the shooters bank balance, whether the input is 
				 divisible by 10, and that it is above 0
				Instead of restricting the maximum actionAmount to the maximum amount of money 
				 that the other players can bet, the game instead adjusts the shooters bet to 
				 meet the coverage if it can't be fully covered. This is handled in the 
				 getOpponentBet() method underneath this one.
	*/
	
	public static int getShooterBet(ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray, int shooterID) {
		// checking shooter bank balance will be covered by getNextShooter
		int shooterBankAmount = bankRollArray.get(shooterID);
		int shooterBetInput = 0;
		
		printMessage(playerArray.get(shooterID).getName());
		printMessageln(", you are the shooter!");
		do {
			printMessage("You have "); 
			printMessage("$" + shooterBankAmount);
			if(CrapsUI.gameWindow.isVisible()) {
				printMessage(" of life savings left, setting less than $10 is for chumps. How much action will you set? ");
			} else {
				printMessage(" of life savings left, setting less than $10 is for chumps. How much action will you set: ");
			}
			
			if(!CrapsUI.gameWindow.isVisible()) {
				try {
					shooterBetInput = input.nextInt();
				} catch (InputMismatchException ime) {
					printMessageln("Invalid input.");
				}
				
				if (!(shooterBetInput <= shooterBankAmount) || !((shooterBetInput % 10) == 0) || !(shooterBetInput >= 10) ) {
					printMessageln("Enter a bet that is less than $" + bankRollArray.get(shooterID) + ", is at least $10, and is a multiple of 10.");
					
				}
			} else if (CrapsUI.gameWindow.isVisible()) {
				CrapsUI.betAmountSelect.removeAllItems();
				for(int i = 10; i <= bankRollArray.get(shooterID); i += 10) {
					
					CrapsUI.betAmountSelect.addItem(i);
				}
				CrapsUI.showBetAmountSelection();
				CrapsUI.waitForInput();
				shooterBetInput = CrapsUI.betAmountSelect.getItemAt(CrapsUI.betAmountSelect.getSelectedIndex());
				CrapsUI.clearTextOutput();
			}
		} while (shooterBetInput > shooterBankAmount || ((shooterBetInput % 10) != 0) || shooterBetInput < 10 );
		
		return shooterBetInput;
		
	}
	
//===================================================================================	

	/**
	Name: 		addShooterBetToArray
	Parameters:	int actionAmount, int shooterID, ArrayList<Integer> betAmountArray
	Return:		ArrayList<Integer> betAmountArray
	Purpose:	This adds the shooters action amount to the betArray
	*/
	
	public static ArrayList<Integer> addShooterBetToArray(int actionAmount, int shooterID, ArrayList<Integer> betAmountArray) {
		
		betAmountArray.set(shooterID, actionAmount);
		
		return betAmountArray;
	}
	
//===================================================================================	
	
	/**
	Name: 		getOpponentBet
	Parameters:	ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray, int shooterID
	Return:		ArrayList<Integer> betAmountArray
	Purpose:	This method collects the bets from the other players, adds them to 
	 			 the betArray and returns the betArray.
				I've been instructed to assume that the coverage will 
				 be met by the time all players are queried for coverage. 
				As such I have not added functionality to re-query players 
				 to add to their bet. 
	*/
	
	public static ArrayList<Integer> getOpponentBet(ArrayList<Player> playerArray, ArrayList<Integer> bankRollArray, ArrayList<Integer> betAmountArray, int shooterID) {
		Craps.actionCoverage = 0;
		playerArray.forEach((player) -> {
			int playerBetInput = 0;
			if (!player.hasLost() && playerArray.indexOf(player) != shooterID && Craps.actionCoverage != actionAmount) {
				
				System.out.println();
				printMessage(player.getName());
				printMessageln(" how much of the action do you want?");
				printMessage("Betting less than $10 is for chumps and you can't bet higher than ");
				printMessage("$" + (actionAmount - Craps.actionCoverage));
				printMessage(", or your life savings");
				
				if(!CrapsUI.gameWindow.isVisible()) {
					printMessage(". ($" + bankRollArray.get(player.getBankRollIndex()));
					printMessage("): ");
					do {
						try {
							playerBetInput = input.nextInt();
						} catch (InputMismatchException ime) {
							printMessageln("Invalid input.");
						}
						if (playerBetInput < 10 || playerBetInput > actionAmount || ((playerBetInput % 10) != 0) ) {
							printMessage("Your bet must be at least $10 or up to the remaining action");
							printMessage("$" + actionAmount);
							printMessage("less than or equal to your current life savings $");
							printMessage("" + (bankRollArray.get(player.getBankRollIndex()) - betAmountArray.get(player.getBankRollIndex()))); 
							printMessageln("and must be a multiple of 10.");
						}
						
					} while (playerBetInput < 10 || playerBetInput > (actionAmount - Craps.actionCoverage) || ((playerBetInput % 10) != 0));
				} else {
					printMessage(".");
					CrapsUI.betAmountSelect.removeAllItems();
					for(int i = 10; i <= actionAmount && i <= (actionAmount - Craps.actionCoverage) && i <= bankRollArray.get(player.getBankRollIndex()); i += 10) {
						
						CrapsUI.betAmountSelect.addItem(i);
					}
					
					CrapsUI.waitForInput();
					playerBetInput = CrapsUI.betAmountSelect.getItemAt(CrapsUI.betAmountSelect.getSelectedIndex());
				}
				betAmountArray.set(player.getBankRollIndex(), playerBetInput);
				Craps.actionCoverage += playerBetInput;
				
			}// Large IF statement end
			if (CrapsUI.gameWindow.isVisible()) {
				CrapsUI.clearTextOutput();
			}
		});
		if (Craps.actionCoverage < actionAmount) {
			int leftOver = (actionAmount - Craps.actionCoverage);
			betAmountArray.set(shooterID, (betAmountArray.get(shooterID) - leftOver));
		}
		if (CrapsUI.gameWindow.isVisible()) {
			CrapsUI.hideBetAmountSelect();
		}
		printMessageln("The shooter's bet has been covered. \nNO MORE BETS!.");
		
		System.out.println();// This is for formatting
		return betAmountArray;
	}
	
//===================================================================================
	
	/**
	Name: 		launchCelebration
	Parameters:	N/A
	Return:		void
	Purpose:	This method launches the proper celebration message depending 
				 on whether the gameWindow from CrapsUI is visible.
	*/
	
	public static void launchCelebration() {
		System.out.println();// For formatting
		if(!CrapsUI.gameWindow.isVisible()) {
			printMessageln("***** AND WE HAVE A WINNER! Congratulations, " + winner.getName() + "!*****");
			printMessageln("You have won the total pot of $" + totalMoney + "!");
		} else {
			CrapsUI.hideBankDisplay();
			printMessageln("****** AND WE HAVE A WINNER!******");
			printMessageln("Congratulations, " + winner.getName() + "!");
			printMessageln("You have won the total pot of $" + totalMoney + "!");
			CrapsUI.celebrateUntilExit();
		}
	}
	
}//End of class
 