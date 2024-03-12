package main;

import java.util.InputMismatchException;

public class CrapsHelper {
	
//===================================================================================	
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
	
//===================================================================================	
	
	static String nameHolder;
	
	public static void configurePlayerArray() {
		
		
		printMessage("Enter the number of players for this game (minimum of 2 to maximum of 6): ");
		while(Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
			
			try {
				Craps.numberOfPlayers = Craps.scnr.nextInt();
			} catch(InputMismatchException ime) {
				printMessageln("Invalid input");
				Craps.scnr.nextLine();
			}
			if (Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
				printMessageln("Invalid input.");
				printMessage("Enter the number of players for this game (minimum of 2 to maximum of 6):");
			}
			
		} 
		
		setupPlayerArray();
		//System.out.println(playerArray.toString());
		Craps.scnr.nextLine();
		Craps.playerArray.forEach((player) -> {
			do {
				System.out.println();
				printMessage("Enter the first name of Player" 
						+ (Craps.playerArray.indexOf(player) + 1) 
						+ " and press ENTER: ");
				
				nameHolder = Craps.scnr.nextLine();
			}while(!inputCheck(nameHolder));
			
			player.setName(nameHolder);
			player.setBankRollIndex(Craps.playerArray.indexOf(player));
			
			
		});
		
	}
	
//===================================================================================	
	
	public static void configureBankRollArray(){
		
		Craps.playerArray.forEach((player) -> {
			Craps.bankRollArray.add(player.getBankRollIndex(), 100);
			Craps.betAmountArray.add(player.getBankRollIndex(), 0);
		});
		
	}
	
//===================================================================================	
	
	public static void printPlayerBankBalances() {
		printMessageln("\nThese are the players:");
		Craps.playerArray.forEach((player) -> {
			printMessageln("Player" + (Craps.playerArray.indexOf(player) + 1) 
					+ " - " + player.getName()
					+ " - Money in bank: $" 
					+ Craps.bankRollArray.get(player.getBankRollIndex()));
		});
	}
	
//===================================================================================	
	
	public static boolean inputCheck(String inputString) {
		String userResponse;
		printMessageln("Yor input: " + inputString.trim());
		printMessage("Is this correct? y/n: ");
		userResponse = Craps.scnr.nextLine();
		if(userResponse.toLowerCase().trim().equals("y")) {
			return true;
		} else {
			return false;
		}
		
	}
	
//===================================================================================	
	
	public static void setupPlayerArray() {
		
		if(Craps.numberOfPlayers == 2 || Craps.numberOfPlayers > 1 ) {
			Craps.player1 = new Player();
			Craps.playerArray.add(Craps.player1);
			Craps.player2 = new Player();
			Craps.playerArray.add(Craps.player2);
		}
		if(Craps.numberOfPlayers == 3 || Craps.numberOfPlayers > 2) {
			Craps.player3 = new Player();
			Craps.playerArray.add(Craps.player3);
		}
		if(Craps.numberOfPlayers == 4 || Craps.numberOfPlayers > 3) {
			Craps.player4 = new Player();
			Craps.playerArray.add(Craps.player4);
		}
		if(Craps.numberOfPlayers == 5 || Craps.numberOfPlayers > 4) {
			Craps.player5 = new Player();
			Craps.playerArray.add(Craps.player5);
		}
		if(Craps.numberOfPlayers == 6 || Craps.numberOfPlayers > 5) {
			Craps.player6 = new Player();
			Craps.playerArray.add(Craps.player6);
		}
		
	}
	
//===================================================================================	
	
	public static void queryRules() {
		System.out.println();
		printMessage("Would you like a brief explanation of the rules of the game? Enter Y for yes, or N for no: ");
		String userInput = Craps.scnr.nextLine();
		switch(userInput.toLowerCase().trim()) {
			case"y":{
				printRules();
				break;
			}
			case"n":{
				printMessageln("OK, you know the rules...let's play some craps!");
				System.out.println();
			}
			default: {
				
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
	
	static int dramaticPause = 750;
	public static void rollComeOut() {
		
		Craps.comeOut = rollDice();
		printMessage("***** Rolling the dice");
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
	}
	
//===================================================================================	
	
	public static void comeOutResult() {
		switch(Craps.comeOut) {
			case 2: {
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a two. You lose...");
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			} 
			case 3: {
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a three. You lose...");
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			}
			case 12: {
				printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a twelve. You lose...");
				Craps.didShooterWin = false;
				Craps.didShooterCrap = true;
				Craps.shootingForPoint = false;
				break;
			}
			case 11:
			case 7: {
				printMessageln("Congratulations " + Craps.playerArray.get(Craps.shooterID).getName() 
						+ "! You have rolled a natural. You win!");
				Craps.didShooterWin = true;
				Craps.didShooterCrap = false;
				Craps.shootingForPoint = false;
				break;
			}
			default: {
				Craps.pointGoal = Craps.comeOut;
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
			
			printMessageln(Craps.bankRollArray.toString());
			printMessageln(Craps.betAmountArray.toString());
			
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
			
			printMessageln(Craps.bankRollArray.toString());
			printMessageln(Craps.betAmountArray.toString());
			
		} else if (!Craps.didShooterCrap && !Craps.didShooterWin) {
			printMessageln("Rolling the dice again to try for your point...");
		}
		// check if any players are out of the game
		Craps.bankRollArray.forEach((account) -> {
			
			if (account == 0 && (Craps.betAmountArray.get(Craps.bankRollArray.indexOf(account)) == 0 ) && !Craps.playerArray.get(Craps.bankRollArray.indexOf(account)).hasLost() ){
				
				Craps.playerArray.get(Craps.bankRollArray.indexOf(account)).gameOver();
				printMessageln(Craps.playerArray.get(Craps.bankRollArray.indexOf(account)).getName() + "Is out");
			}
			
		});
		
	}
	
//===================================================================================	
	
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
		willPass = true;
		if (!Craps.playerArray.get(Craps.shooterID).hasLost()) {
			printMessage(Craps.playerArray.get(Craps.shooterID).getName() 
					+ ", do you want to roll again or pass the dice? "
					+ "Enter Y to shoot again or enter P to pass the dice to the next shooter: ");
			passResponse = Craps.scnr.nextLine();
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
			printMessage(" in your bank roll, and the minimum bet is $10 Enter your bet amount: ");
			try {
				shooterBetInput = Craps.scnr.nextInt();
			} catch (InputMismatchException ime) {
				printMessageln("Invalid input.");
			}
			
			if (!(shooterBetInput <= shooterBankAmount) || !((shooterBetInput % 10) == 0) || !(shooterBetInput >= 10) ) {
				printMessageln("You must enter a bet that is less than your current bank balance, is at least $10, and is a multiple of 10.");
				
			}
			
		} while (shooterBetInput > shooterBankAmount || ((shooterBetInput % 10) != 0) || shooterBetInput < 10 );
		
		Craps.bankRollArray.set(Craps.shooterID, (Craps.bankRollArray.get(Craps.shooterID) - shooterBetInput));
		Craps.betAmountArray.set(Craps.shooterID, shooterBetInput);
		
		return shooterBetInput;
		
	}
	static int playerBetInput;
	
//===================================================================================	
	
	public static void queryActionEnguagement() {
		Craps.actionCoverage = 0;
		Craps.playerArray.forEach((player) -> {
			playerBetInput = 0;
			if (!player.hasLost() && Craps.playerArray.indexOf(player) != Craps.shooterID && Craps.actionCoverage != Craps.actionAmount && Craps.bankRollArray.get(player.getBankRollIndex()) != 0) {
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
						playerBetInput = Craps.scnr.nextInt();
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
			}
		});
		printMessageln("The shooter's bet has been completely covered. NO MORE BETS!.");
		printMessageln(Craps.bankRollArray.toString());
		printMessageln(Craps.betAmountArray.toString());
		System.out.println();
	}
	
}//End of class
 