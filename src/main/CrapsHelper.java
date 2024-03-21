package main;

import java.util.InputMismatchException;

public class CrapsHelper {
	
	
//===================================================================================	
	//This is a custom method that I made originally for my text based adventure game,(The Caves of Amnesia)  
	//  So that text would print onto the screen one character at a time. I felt that the typing animation
	//  of the characters added immersion, and something much more stimulating for the brain to experience.
	
	//Text speed can be set to 0 for instant printing
//						\     /    ___  ___     ___     __   __   ___  ___  __
//                  	 \   /      |  |__  \_/  |     /__` |__) |__  |__  |  \
//                  	  \ /       |  |___ / \  |     .__/ |    |___ |___ |__/  
	public static int textSpeed = 0;
	static boolean skipTheReturn;
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
	
	public static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException ie){
			Thread.currentThread().interrupt();
		}
	}
	
//===================================================================================	
	//printWelcomeMessage()
	/* This method prints an ASCII art welcome message
	 */
	
	public static void printWelcomeMessage() {
		CrapsHelper.printMessageln("       _______       _______");
		CrapsHelper.printMessageln("     /\\       \\     /       /\\");
		CrapsHelper.printMessageln("    /()\\   ()  \\   /  ()   /()\\");
		CrapsHelper.printMessageln("   /    \\_______\\ /_______/    \\");
		CrapsHelper.printMessageln("   \\    /()     / \\     ()\\    /");
		CrapsHelper.printMessageln("    \\()/   ()  /   \\  ()   \\()/");
		CrapsHelper.printMessageln("     \\/_____()/     \\()_____\\/");
		CrapsHelper.printMessageln("       ___       __   __         ___");
		CrapsHelper.printMessageln(" |  | |__  |    /  ` /  \\  |\\/| |__  ");
		CrapsHelper.printMessageln(" |/\\| |___ |___ \\__, \\__/  |  | |___ ");
		CrapsHelper.printMessageln("___  __      __        __          __ ");
		CrapsHelper.printMessageln(" |  /  \\    /  `  /\\  /__` | |\\ | /  \\");
		CrapsHelper.printMessageln(" |  \\__/    \\__, /~~\\ .__/ | | \\| \\__/");
		CrapsHelper.printMessageln(" ___            __                  ___");
		CrapsHelper.printMessageln("|__   /\\  |\\ | /__` |__|  /\\  |  | |__ ");
		CrapsHelper.printMessageln("|    /~~\\ | \\| .__/ |  | /~~\\ |/\\| |___");
		CrapsHelper.printMessageln("");
		CrapsHelper.printMessageln("");
		CrapsHelper.sleep(750);
	}
	
//===================================================================================
	//configurePlayerArray() 
	/* This method takes the users input and assigns the value of 2-6 to numberOfPlayers
	 * The method then calls setupPlayerArray() where the player objects are loaded
	 * At the end of the method is a loop that utilizes inputCheck() to ensure the user
	 * 	didn't make any typing mistakes when entering the name.
	 */
	static String nameHolder;
	
	public static void configurePlayerArray() {
		
		// Collect number of players
		
		printMessage("Enter number of players(2-6): ");
		while(Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
			if(CrapsUI.gameWindow.isVisible()) {
				CrapsUI.showPlayerSelect();
				CrapsUI.waitForInput();
			} else {
				try {
					Craps.numberOfPlayers = Craps.input.nextInt();
				} catch(InputMismatchException ime) {
					printMessageln("Invalid.");
					Craps.input.nextLine();
				}
				if (Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
					printMessageln("Invalid.");
					printMessage("Enter the number of players for this game (minimum of 2 to maximum of 6):");
				}
			
			}
			
		}
		if(CrapsUI.gameWindow.isVisible()) {
			System.out.println(Craps.numberOfPlayers);
			CrapsUI.clearTextOutput();
			CrapsUI.submitNamesButton.setVisible(true);
			CrapsUI.hidePlayerSelect();
		}
		
		// Setup player array
		setupPlayerArray();
		
		// This portion is for setting the names of the players
		if(!CrapsUI.gameWindow.isVisible()) {	
			Craps.input.nextLine();
			Craps.playerArray.forEach((player) -> {
				do {
					System.out.println();
					printMessage("Player " 
							+ player.getNumber()
							+ " Name: ");
					
					nameHolder = Craps.input.nextLine();
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
		
	}
	
//===================================================================================	
	//configureBankRollArray()
	/* This method initializes the betAmountArray and bankRollArray by only adding the amount of indexes
	 *  that corresponds with the amount of players. I do this using forEach from the playerArray, allowing
	 *  for the creation of each array index only for the amount of players in the game. I utilize my get methods
	 *  from within player to synchronize the indexes. 
	 */
	public static void configureBankRollArray(){
		
		Craps.playerArray.forEach((player) -> {
			Craps.bankRollArray.add(player.getBankRollIndex(), 100);
			Craps.betAmountArray.add(player.getBankRollIndex(), 0);
		});
		
	}
	
//===================================================================================	
	// printPlayerBankBalances()
	/* This method simply prints the players number, their name, and their bankroll amount
	 */
	public static void printPlayerBankBalances() {
		
		Craps.playerArray.forEach((player) -> {
			printMessageln("Player" + (Craps.playerArray.indexOf(player) + 1) 
					+ " - " + player.getName()
					+ " -  Life Savings: $" 
					+ Craps.bankRollArray.get(player.getBankRollIndex()));
		});
	}
	
//===================================================================================	
	//inputCheck()
	/* This method returns a boolean value depending on the users input. It provides the string
	 *  that the user inputted and queries a y or n. This method does not return true unless y
	 *  is inputted, any other input is assumed to be a no. If an empty name is provided then
	 *  the method returns false and prints a reminder to the console to provide a name.
	 */
	public static boolean inputCheck(String inputString,int inputInt) {
		
		if(inputString.trim().length() < 1) {
			printMessageln("Please input a name.");
			return false;
		}
		
		String userResponse;
		printMessageln("Your input: " + inputString.trim());
		printMessage("Verify y/n: ");
		userResponse = Craps.input.nextLine();
		if(userResponse.toLowerCase().trim().equals("y")) {
			return true;
		} else {
			return false;
		}
		
	}
	
//===================================================================================	
	//setupPlayerArray()
	/* This method takes the numberOfPlayers and loads the ArrayList of playerArray with the requisite
	 *  amount of Player objects. These player objects hold the players name, and this method of loading
	 *  the array allows for standardized indexing of each player. player1 is always in index 0, player2 index 1 etc.
	 * I used a list of if statements to allow for adding onto the array as needed for the amount of players.
	 *  If there are only two players then only the first if statement runs, creating and placing the player object
	 *  into the array. If the numberOfPlayers is 3 then the second if statement runs adding a third player etc.
	 */
	public static void setupPlayerArray() {
		
		if(Craps.numberOfPlayers == 2 || Craps.numberOfPlayers > 1 ) {
			Craps.player1 = new Player(1,0);
			Craps.playerArray.add(Craps.player1);
			Craps.player2 = new Player(2,1);
			Craps.playerArray.add(Craps.player2);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player1NameInput.setVisible(true);
				CrapsUI.player2NameInput.setVisible(true);
			}
		}
		if(Craps.numberOfPlayers == 3 || Craps.numberOfPlayers > 2) {
			Craps.player3 = new Player(3,2);
			Craps.playerArray.add(Craps.player3);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player3NameInput.setVisible(true);
			}
		}
		if(Craps.numberOfPlayers == 4 || Craps.numberOfPlayers > 3) {
			Craps.player4 = new Player(4,3);
			Craps.playerArray.add(Craps.player4);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player4NameInput.setVisible(true);
			}
		}
		if(Craps.numberOfPlayers == 5 || Craps.numberOfPlayers > 4) {
			Craps.player5 = new Player(5,4);
			Craps.playerArray.add(Craps.player5);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player5NameInput.setVisible(true);
			}
		}
		if(Craps.numberOfPlayers == 6 || Craps.numberOfPlayers > 5) {
			Craps.player6 = new Player(6,5);
			Craps.playerArray.add(Craps.player6);
			if(CrapsUI.gameWindow.isVisible()){
				CrapsUI.player6NameInput.setVisible(true);
			}
		}
	}
	
//===================================================================================	
	//queryRules()
	/* This method is for printing the rules if the user doesn't know how to play the game.
	 * If the user inputs that they know the rules, this method instead prints a message
	 *  acknowledging that and stating that it's time for the game to start.
	 */
	public static String yesNo;
	public static void queryRules() {
		System.out.println();
		if(!CrapsUI.gameWindow.isVisible()) {
			printMessage("Do you know the rules? y/n: ");
			String userInput = Craps.input.nextLine();
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
			
			printMessage("Do you know the rules?");
			CrapsUI.showYesNoButtons();
			CrapsUI.waitForInput();
			CrapsUI.hideYesNoButtons();
			switch(yesNo) {
				case"yes":{
					CrapsUI.clearTextOutput();
					printMessageln("OK, you know the rules...let's play some craps!");
					break;
				}
				case"no":{
					CrapsUI.clearTextOutput();
					printRules();
					break;
				}
			
			}
		}
		
		
	}
	
//===================================================================================	
	
	public static void printRules() {
		printMessageln("Rules");
	}
	
//===================================================================================	
	
	public static int rollDice() {
		int rollTotal = Craps.firstDie.roll() + Craps.secondDie.roll();
		return rollTotal;
	}
	
//===================================================================================
	//rollComeOut()
	/* This method rolls the comeOut roll from the shooter. I utilize my sleep() method 
	 *  between each period in the message to allow for dramatic tension to build while
	 *  waiting for the roll.
	 */
	static int dramaticPause = 500;
	public static void rollComeOut() {
		
		
		printMessage("***** Rolling the dice");
		Craps.comeOut = rollDice();// Might as well make it so the program rolls while it says its rolling
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessageln("and the result is: " + Craps.comeOut + "! *****");
		System.out.println();
		
	}
	
//===================================================================================	
	//rollForPoint()
	/* This method is the same as rollComeOut but has slightly different word formatting while retaining
	 *  the dramatic tension of the comeOut roll. It assigns the roll to Craps.pointRoll
	 */
	public static void rollForPoint() {
		printMessage("Rolling");
		sleep(dramaticPause);
		Craps.pointRoll = rollDice();
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessage(".");
		sleep(dramaticPause);
		printMessageln("you rolled a " + Craps.pointRoll);
		
	}
	
//===================================================================================	
	//pointRollResult
	/* This method determines the outcome of the point roll with the value stored within Craps.pointRoll
	 * I use the same combination of boolean values to determine the outcome of each point roll so that the same
	 *  method adjustBankBalances can be used.
	 */
	public static void pointRollResult() {
		
		switch(Craps.pointRoll) {
			case 7:{
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a seven. You lose...");
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			}
			default:{
				//putting this in default since case arguments have to be constant
				/* This allows me to utilize the switch as well as an if to determine outcome, in this case
				 *  I am checking if pointRoll equals pointGoal
				 */
				if(Craps.pointRoll == Craps.pointGoal) {
					printMessageln("Congratulations " + Craps.playerArray.get(Craps.shooterID).getName() 
							+ "! You have rolled " + Craps.pointGoal + ". You win!");
					Craps.didShooterWin = true;
					Craps.didShooterCrap = false;
					Craps.shootingForPoint = false;
					break;
				} else {
					Craps.didShooterWin = false;
					Craps.didShooterCrap = false;
					Craps.shootingForPoint = true;
					break;
				}
			}
		
		}
		System.out.println();
	}
	
//===================================================================================	
	//comeOutResult()
	/* This method handles the result of the comeOut roll. I decided to use a switch. Depending on the
	 *  outcome a combination of the three boolean values didShooterWin and didShooterCrap are set.
	 *  With the combined outcome of these boolean values, I determine the outcome of the roll in 
	 *  adjustBankBalances(). 
	 * Additionally the boolean value shootingForPoint determines whether
	 *  the shooting for point loop is entered or not. 
	 * This is also where pointGoal is set.
	 */
	
	
	public static void comeOutResult() {
		switch(Craps.comeOut) {
			case 2: {
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a two. You lose...");
				System.out.println();
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			} 
			case 3: {
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a three. You lose...");
				System.out.println();
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			}
			case 12: {
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a twelve. You lose...");
				System.out.println();
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			}
			case 11://This is here because 11 and 7 have the same outcome
			case 7: {
				printMessageln("Congratulations " + Craps.playerArray.get(Craps.shooterID).getName() 
						+ "! You have rolled a natural. You win!");
				System.out.println();
				Craps.didShooterWin = true;
				Craps.didShooterCrap = false;
				Craps.shootingForPoint = false;
				break;
			}
			default: {// This is when the shooter neither craps or wins on their comeOut
				Craps.pointGoal = Craps.comeOut; // This is where point goal is set
				printMessageln("OK " + Craps.playerArray.get(Craps.shooterID).getName() + ", your point is " 
						+ Craps.pointGoal + ". To win, you need to roll your point again, but if you roll a seven, you lose.");
				Craps.didShooterWin = false;
				Craps.didShooterCrap = false;
				Craps.shootingForPoint = true;
				break;
			}
			
		}
		
	}
	
//===================================================================================	
	//adjustBankBalances()
	/* This method adjusts the bank balances according to the combination of the boolean values
	 *  didShooterWin and didShooterCrap, set in the previous method comeOutResult()
	 */
	
	
	public static void adjustBankBalances() {
		if (!Craps.didShooterWin && Craps.didShooterCrap) {//Shooter craps out 
			
			//Take the amount the shooter put up for betting and divide it according to the action covered.
			Craps.betAmountArray.forEach((bet) -> {
				if (Craps.betAmountArray.indexOf(bet) == Craps.shooterID) {
					//Set bet amount in array to 0
					Craps.betAmountArray.set(Craps.betAmountArray.indexOf(bet), 0);
				} else {
					//Award money
					Craps.bankRollArray.set(Craps.betAmountArray.indexOf(bet), (Craps.bankRollArray.get(Craps.betAmountArray.indexOf(bet)) + (bet * 2)));
					//Set bet amount in array to 0
					Craps.betAmountArray.set(Craps.betAmountArray.indexOf(bet), 0);
					
				}
			});
			//For debug purposes while developing to determine accuracy of array changes.
//			printMessageln(Craps.bankRollArray.toString());
//			printMessageln(Craps.betAmountArray.toString());
			
		} else if (Craps.didShooterWin && !Craps.didShooterCrap) {// Shooter wins
			
			Craps.betAmountArray.forEach((bet) -> {
				
				if (Craps.betAmountArray.indexOf(bet) == Craps.shooterID) {
					//Award money
					Craps.bankRollArray.set(Craps.shooterID, (Craps.bankRollArray.get(Craps.shooterID) + (bet * 2)));
					//Set bet amount in array to 0
					Craps.betAmountArray.set(Craps.betAmountArray.indexOf(bet), 0);
				} else {
					//Set bet amount in array to 0
					Craps.betAmountArray.set(Craps.betAmountArray.indexOf(bet), 0);
				}
				
			});
			

			
		} else if (!Craps.didShooterCrap && !Craps.didShooterWin) {// This runs when shooting for point
			printMessageln("Rolling the dice again to try for your point...");
		}
		
		
	}
	
//===================================================================================	
	//checkForBust()
	/* This method checks if any players have bust, if so it runs the Player method of gameOver()
	 *  and sets the players isOut boolean to true. I check this value when determining whether a player
	 *  can interact with the game with betting or shooting.
	 */
	public static void checkForBust() {
		// check if any players are out of the game
			Craps.bankRollArray.forEach((account) -> {
				
				if (account == 0 && (Craps.betAmountArray.get(Craps.bankRollArray.indexOf(account)) == 0 )){
					
					Craps.playerArray.get(Craps.bankRollArray.indexOf(account)).gameOver();
				}
				
			});
	}
	
//===================================================================================	
	//checkForWinner()
	/* This method checks for a winner, if there is one it points Craps.winner at the winning player and 
	 *  returns the boolean value of winnerDecided. This gets applied to boolean gameIsDone which controls
	 *  whether the main game loop runs or not
	 */
	
	static boolean winnerDecided;
	static int totalMoney;
	
	public static boolean checkForWinner() {
		winnerDecided = false;
		
		totalMoney = Craps.numberOfPlayers * 100;
		
		Craps.bankRollArray.forEach((bankAmount) -> {
			if (bankAmount == totalMoney) {
				winnerDecided = true;
				Craps.winner = Craps.playerArray.get(Craps.bankRollArray.indexOf(bankAmount));
			} 
		});
		
		return winnerDecided;
		
	}

//===================================================================================
	
	static String passResponse;
	static boolean willPass;
	
	public static boolean queryPass() {
		System.out.println();
		willPass = true;
		Craps.input.nextLine();
		if (!Craps.playerArray.get(Craps.shooterID).hasLost()) {
			printMessage(Craps.playerArray.get(Craps.shooterID).getName() 
					+ ", do you want to roll again or pass the dice? "
					+ "Enter Y to shoot again or enter P to pass the dice to the next shooter: ");
			passResponse = Craps.input.nextLine();
			if (passResponse.toLowerCase().trim().equals("y")) {
				willPass = false;
			}
			System.out.println();
		}
		return willPass;
		
	}
	
//===================================================================================
	
	static int maxShooterID;
	public static void getNextShooter() {//aka find next available shooter
		maxShooterID = (Craps.numberOfPlayers - 1);
		int index;
		
		if ((Craps.shooterID + 1) > maxShooterID) {
			index = 0;
		} else {
			index = Craps.shooterID + 1;
		}
			
		
		for(int i = index; i <= maxShooterID; i++) {
			
			if (!Craps.playerArray.get(i).hasLost()) {
				Craps.shooterID = i;
				break;
			}
			
		}
		
	}
	
//===================================================================================	
	//getShooterBet()
	/* This method sets the actionAmount from user input based on some restrictions
	 * These restrictions include the shooters bank balance, whether the input is
	 *  divisible by 10, and that it is above 0
	 * 
	 * Instead of restricting the maximum actionAmount to the maximum amount of money
	 *  that the other players can bet, the game instead adjusts the shooters bet to
	 *  meet the coverage if it can't be fully covered. This is handled in the 
	 *  getOpponentBet() method underneath this one.
	 */
	
	
	static int shooterBetInput;
	static int shooterBankAmount;
	
	public static int getShooterBet() {
		// checking shooter bank balance will be covered by getNextShooter
		shooterBankAmount = Craps.bankRollArray.get(Craps.shooterID);
		shooterBetInput = 0;
		
		printMessage(Craps.playerArray.get(Craps.shooterID).getName());
		printMessageln(", you are the shooter!");
		do {
			printMessage("You have "); 
			printMessage("$" + shooterBankAmount);
			printMessage(" of life savings left, betting less than $10 is for chumps. Enter your bet amount: ");
			if(!CrapsUI.gameWindow.isVisible()) {
				try {
					shooterBetInput = Craps.input.nextInt();
				} catch (InputMismatchException ime) {
					printMessageln("Invalid input.");
				}
				
				if (!(shooterBetInput <= shooterBankAmount) || !((shooterBetInput % 10) == 0) || !(shooterBetInput >= 10) ) {
					printMessageln("Enter a bet that is less than $" + Craps.bankRollArray.get(Craps.shooterID) + ", is at least $10, and is a multiple of 10.");
					
				}
			} else {
				CrapsUI.betAmountSelect.removeAllItems();
				for(int i = 10; i <= Craps.bankRollArray.get(Craps.shooterID); i += 10) {
					
					CrapsUI.betAmountSelect.addItem(i);
				}
				CrapsUI.showActionAmountSelection();
				CrapsUI.waitForInput();
				shooterBetInput = CrapsUI.betAmountSelect.getItemAt(CrapsUI.betAmountSelect.getSelectedIndex());
				CrapsUI.clearTextOutput();
			}
		} while (shooterBetInput > shooterBankAmount || ((shooterBetInput % 10) != 0) || shooterBetInput < 10 );
		
		Craps.bankRollArray.set(Craps.shooterID, (Craps.bankRollArray.get(Craps.shooterID) - shooterBetInput));
		Craps.betAmountArray.set(Craps.shooterID, shooterBetInput);
		
		return shooterBetInput;
		
	}
	
//===================================================================================	
	//getOpponentBet()
	/* This method collects the bets from the other players. I've been instructed to assume that
	 *  the coverage will be met by the time all players are queried for coverage. As such I have 
	 *  not added functionality to re-query players to add to their bet. 
	 *  
	 * Any excess that cannot be covered by the players is returned from the bet amount made by the
	 *  shooter back into the shooters bankroll.
	 */
	static int playerBetInput;
	public static void getOpponentBet() {
		Craps.actionCoverage = 0;
		Craps.playerArray.forEach((player) -> {
			playerBetInput = 0;
			if (!player.hasLost() && Craps.playerArray.indexOf(player) != Craps.shooterID && Craps.actionCoverage != Craps.actionAmount && Craps.bankRollArray.get(player.getBankRollIndex()) != Craps.shooterID) {
				System.out.println();
				printMessage(player.getName());
				printMessageln(" how much of the action do you want?");
				printMessage("Enter your bet, minimum of $10 up to ");
				printMessage("$" + (Craps.actionAmount - Craps.actionCoverage));
				printMessage(" or your bank balance($");
				printMessage("" + Craps.bankRollArray.get(player.getBankRollIndex()));
				printMessage(") whichever is less: ");
				// add if statement to check bet array and if there is a bet present make a different message for adding to your bet
				do {
					try {
						playerBetInput = Craps.input.nextInt();
					} catch (InputMismatchException ime) {
						printMessageln("Invalid input.");
					}
					if (playerBetInput < 10 || playerBetInput > Craps.actionAmount || ((playerBetInput % 10) != 0) ) {
						printMessage("Your bet must be at least $10 or up to the remaining action");
						printMessage("$" + Craps.actionAmount);
						printMessage("less than or equal to your current bank amount $");
						printMessage("" + (Craps.bankRollArray.get(player.getBankRollIndex()) - Craps.betAmountArray.get(player.getBankRollIndex()))); 
						printMessageln("and must be a multiple of 10.");
					}
					
				} while (playerBetInput < 10 || playerBetInput > (Craps.actionAmount - Craps.actionCoverage) || ((playerBetInput % 10) != 0));
				
				Craps.betAmountArray.set(player.getBankRollIndex(), playerBetInput);
				Craps.bankRollArray.set(player.getBankRollIndex(), (Craps.bankRollArray.get(player.getBankRollIndex()) - playerBetInput));
				Craps.actionCoverage += playerBetInput;
				if (Craps.actionCoverage < Craps.actionAmount) {
					int leftOver = (Craps.actionAmount - Craps.actionCoverage);
					Craps.bankRollArray.set(Craps.shooterID, (Craps.bankRollArray.get(Craps.shooterID) + leftOver));
					Craps.betAmountArray.set(Craps.shooterID, (Craps.betAmountArray.get(Craps.shooterID) - leftOver));
				}
			}
		});
		printMessageln("The shooter's bet has been covered. NO MORE BETS!.");
		
		/* This is a debug print I used to ensure correct changes were made to the arrays 
		 *  while developing.
		 */
//		printMessageln("These are the betting and bank arrays:");
//		printMessageln(Craps.bankRollArray.toString());
//		printMessageln(Craps.betAmountArray.toString());
		System.out.println();// This is for formatting
	}
	
}//End of class
 