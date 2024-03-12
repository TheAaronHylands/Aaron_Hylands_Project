package main;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class CrapsHelper {
	
	
	static String nameHolder;
	
	public static void configurePlayerArray() {
		
		
		Craps.printMessage("Enter the number of players for this game (minimum of 2 to maximum of 6): ");
		while(Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
			
			try {
				Craps.numberOfPlayers = Craps.scnr.nextInt();
			} catch(InputMismatchException ime) {
				Craps.printMessageln("Invalid input");
				Craps.scnr.nextLine();
			}
			if (Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
				Craps.printMessageln("Invalid input.");
				Craps.printMessage("Enter the number of players for this game (minimum of 2 to maximum of 6):");
			}
			
		} 
		
		setupPlayerArray();
		//System.out.println(playerArray.toString());
		Craps.scnr.nextLine();
		Craps.playerArray.forEach((player) -> {
			do {
				System.out.println();
				Craps.printMessage("Enter the first name of Player" 
						+ (Craps.playerArray.indexOf(player) + 1) 
						+ " and press ENTER: ");
				
				nameHolder = Craps.scnr.nextLine();
			}while(!inputCheck(nameHolder));
			
			player.setName(nameHolder);
			player.setBankRollIndex(Craps.playerArray.indexOf(player));
			
			
		});
		
	}
	
	
	public static void configureBankRollArray(){
		
		Craps.playerArray.forEach((player) -> {
			Craps.bankRollArray.add(player.getBankRollIndex(), 100);
			Craps.betAmountArray.add(player.getBankRollIndex(), 0);
		});
		
	}
	
	public static void printPlayerBankBalances() {
		Craps.printMessageln("\nThese are the players:");
		Craps.playerArray.forEach((player) -> {
			Craps.printMessageln("Player" + (Craps.playerArray.indexOf(player) + 1) 
					+ " - " + player.getName()
					+ " - Money in bank: $" 
					+ Craps.bankRollArray.get(player.getBankRollIndex()));
		});
	}
	
	public static boolean inputCheck(String inputString) {
		String userResponse;
		Craps.printMessageln("Yor input: " + inputString.trim());
		Craps.printMessage("Is this correct? y/n: ");
		userResponse = Craps.scnr.nextLine();
		if(userResponse.toLowerCase().trim().equals("y")) {
			return true;
		} else {
			return false;
		}
		
	}
	
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
	
	public static void queryRules() {
		System.out.println();
		Craps.printMessage("Would you like a brief explanation of the rules of the game? Enter Y for yes, or N for no: ");
		String userInput = Craps.scnr.nextLine();
		switch(userInput.toLowerCase().trim()) {
			case"y":{
				printRules();
				break;
			}
			case"n":{
				Craps.printMessageln("OK, you know the rules...let's play some craps!");
				System.out.println();
			}
			default: {
				
			}
	
		}
		
	}
	
	public static void printRules() {
		Craps.printMessageln("Rules");
	}
	
	public static int rollDice() {
		int rollTotal = Craps.firstDie.roll() + Craps.secondDie.roll();
		return rollTotal;
	}
	
	public static void rollComeOut() {
		int dramaticPause = 750;
		Craps.comeOut = rollDice();
		Craps.printMessage("***** Rolling the dice");
		Craps.sleep(dramaticPause);
		Craps.printMessage(".");
		Craps.sleep(dramaticPause);
		Craps.printMessage(".");
		Craps.sleep(dramaticPause);
		Craps.printMessage(".");
		Craps.sleep(dramaticPause);
		Craps.printMessageln("and the result is: " + Craps.comeOut + "! *****");
		System.out.println();
		
	}
	
	public static void comeOutResult() {
		switch(Craps.comeOut) {
			case 2: {
				Craps.printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a two. You lose...");
				break;
			} 
			case 3: {
				Craps.printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a three. You lose...");
				break;
			}
			case 12: {
				Craps.printMessageln("Sorry, " + Craps.playerArray.get(Craps.shooterID).getName() +
						", you rolled a twelve. You lose...");
				break;
			}
			case 11:
			case 7: {
				Craps.printMessageln("Congratulations" + Craps.playerArray.get(Craps.shooterID).getName() 
						+ "! You have rolled a natural. You win!");
				break;
			}
			default: {
				Craps.printMessageln("OK " + Craps.playerArray.get(Craps.shooterID).getName() + ", your point is " 
						+ Craps.comeOut + ". To win, you need to roll your point again, but if you roll a seven, you lose.");
				break;
			}
			
		}
	}
	
	static boolean winnerDecided;
	static int totalMoney;
	
	public static boolean checkForWinner(ArrayList<Integer> inputBankRollArray) {
		winnerDecided = false;
		
		totalMoney = Craps.numberOfPlayers * 100;
		
		inputBankRollArray.forEach((bankAmount) -> {
			if (bankAmount == totalMoney) {
				winnerDecided = true;
			} 
		});
		
		return winnerDecided;
		
	}
	
	static int shooterBetInput;
	static int shooterBankAmount;
	
	public static int getShooterBet() {
		
		shooterBankAmount = Craps.bankRollArray.get(Craps.shooterID);
		shooterBetInput = 0;
		
		Craps.printMessage(Craps.playerArray.get(Craps.shooterID).getName());
		Craps.printMessageln(", you are the shooter!");
		do {
			Craps.printMessage("You have "); 
			Craps.printMessage("$" + shooterBankAmount);
			Craps.printMessage(" in your bank roll, and the minimum bet is $10 Enter your bet amount: ");
			try {
				shooterBetInput = Craps.scnr.nextInt();
			} catch (InputMismatchException ime) {
				Craps.printMessageln("Invalid input.");
			}
			
			if (!(shooterBetInput <= shooterBankAmount) || !((shooterBetInput % 10) == 0) || !(shooterBetInput >= 10) ) {
				Craps.printMessageln("You must enter a bet that is less than your current bank balance, is at least $10, and is a multiple of 10.");
				
			}
			
		} while (shooterBetInput > shooterBankAmount || ((shooterBetInput % 10) != 0) || shooterBetInput < 10 );
		
		return shooterBetInput;
		
	}
	static int playerBetInput;
	
	public static void queryActionEnguagement() {
		Craps.actionCoverage = 0;
		Craps.playerArray.forEach((player) -> {
			playerBetInput = 0;
			if (Craps.playerArray.indexOf(player) != Craps.shooterID && Craps.actionCoverage != Craps.actionAmount && Craps.bankRollArray.get(player.getBankRollIndex()) != 0) {
				System.out.println();
				Craps.printMessage(player.getName());
				Craps.printMessageln(" how much of the action do you want?");
				Craps.printMessage("Enter your bet, minimum of $10 up to ");
				Craps.printMessage("$" + (Craps.actionAmount - Craps.actionCoverage));
				Craps.printMessage(" or your bank balance($");
				Craps.printMessage("" + Craps.bankRollArray.get(player.getBankRollIndex()));
				Craps.printMessage(") whichever is less: ");
				// add if statement to check bet array and if there is a bet present make a different message for adding to your bet
				do {
					try {
						playerBetInput = Craps.scnr.nextInt();
					} catch (InputMismatchException ime) {
						Craps.printMessageln("Invalid input.");
					}
					if (playerBetInput < 10 || playerBetInput > Craps.actionAmount || ((playerBetInput % 10) != 0) ) {
						Craps.printMessage("Your bet must be at least $10 or up to the remaining action");
						Craps.printMessage("$" + Craps.actionAmount);
						Craps.printMessage("less than or equal to your current bank amount $");
						Craps.printMessage("" + (Craps.bankRollArray.get(player.getBankRollIndex()) - Craps.betAmountArray.get(player.getBankRollIndex()))); 
						Craps.printMessageln("and must be a multiple of 10.");
					}
					
				} while (playerBetInput < 10 || playerBetInput > (Craps.actionAmount - Craps.actionCoverage) || ((playerBetInput % 10) != 0));
				
				Craps.betAmountArray.set(player.getBankRollIndex(), playerBetInput);
				Craps.actionCoverage += playerBetInput;
			}
		});
		Craps.printMessageln("The shooter's bet has been completely covered. NO MORE BETS!.");
		System.out.println();
		//Craps.printMessageln(Craps.betAmountArray.toString());
	}
	
}//End of class
 