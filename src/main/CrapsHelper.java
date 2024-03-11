package main;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class CrapsHelper {
	
	
	static String nameHolder;
	
	public static void configurePlayerArray() {
		
		
		System.out.print("Enter the number of players for this game (minimum of 2 to maximum of 6):");
		while(Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
			
			try {
				Craps.numberOfPlayers = Craps.scnr.nextInt();
			} catch(InputMismatchException ime) {
				System.out.println("Invalid input");
				Craps.scnr.nextLine();
			}
			if (Craps.numberOfPlayers < 2 || Craps.numberOfPlayers > 6) {
				System.out.println("Invalid input.");
				System.out.print("Enter the number of players for this game (minimum of 2 to maximum of 6):");
			}
			
		} 
		
		System.out.printf("The number of players will be %d\n", Craps.numberOfPlayers);//This is for debug purposes
		setupPlayerArray();
		//System.out.println(playerArray.toString());
		Craps.scnr.nextLine();
		Craps.playerArray.forEach((player) -> {
			do {
				System.out.printf("\nEnter the first name of player%d and press ENTER: ", Craps.playerArray.indexOf(player) + 1);
				nameHolder = Craps.scnr.nextLine();
			}while(!inputCheck(nameHolder));
			
			player.setName(nameHolder);
			player.setBankRollIndex(Craps.playerArray.indexOf(player));
			
			
		});
		
	}
	
	
	public static void configureBankRollArray(){
		
		Craps.playerArray.forEach((player) -> {
			Craps.bankRollArray.add(player.getBankRollIndex(), 100);
		});
		
	}
	
	public static void printPlayerBankBalances() {
		System.out.println("\nThese are the players:");
		Craps.playerArray.forEach((player) -> {
			System.out.printf("Player%d - %s - Money in bank: $%d\n", Craps.playerArray.indexOf(player)+1,player.getName(),Craps.bankRollArray.get(player.getBankRollIndex()));
		});
	}
	
	public static boolean inputCheck(String inputString) {
		String userResponse;
		System.out.printf("Yor input: %s\nIs this correct? y/n: ", inputString.trim());
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
		System.out.print("\nWould you like a brief explanation of the rules of the game? Enter Y for yes, or N for no: ");
		String userInput = Craps.scnr.nextLine();
		switch(userInput.toLowerCase().trim()) {
			case"y":{
				printRules();
				break;
			}
			case"n":{
				System.out.println("OK, you know the rules...let's play some craps!");
			}
			default: {
				
			}
	
		}
		
	}
	
	public static void printRules() {
		System.out.print("Rules");
	}
	
	public static int rollDice() {
		
		return (Craps.firstDie.roll() + Craps.secondDie.roll());
		
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
		
		System.out.printf("\n%s, you are the shooter!\n", Craps.playerArray.get(Craps.shooterID).getName());
		do {
			System.out.printf("You have $%d in your bank roll and the minimum bet is $10 Enter your bet amount: ", shooterBankAmount);
			try {
				shooterBetInput = Craps.scnr.nextInt();
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input.");
			}
			
			if (!(shooterBetInput <= shooterBankAmount) || !((shooterBetInput % 10) == 0) || !(shooterBetInput >= 10) ) {
				System.out.println("You must enter a bet that is less than your current bank balance, at least $10, and is a multiple of 10.");
				
			}
			
		} while (shooterBetInput > shooterBankAmount || ((shooterBetInput % 10) != 0) || shooterBetInput < 10 );
		
		return shooterBetInput;
		
	}
	
}//End of class
 