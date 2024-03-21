package main;


public class Player {
	
	/* This is my Player class.
	 * Player objects hold their name, their bankIndex (which correlates with the other array indexes),
	 *  a boolean of isOut which tracks whether the player has run out of money or not, and the players 
	 *  number(player "1", player "2" etc.)
	 */
	
	String name;
	int bankIndex;
	boolean isOut = false;
	int playerNumber;
	
	
	Player(int newPlayerNumber, int newBankIndex){// Constructor to input the player number and bankIndex upon creation
		this.playerNumber = newPlayerNumber;
		this.bankIndex = newBankIndex;
	}
	
	public int getNumber() {// Get method for the players number
		return this.playerNumber;
	}
	
	public boolean hasLost() {// Get method to check isOut
		return this.isOut;
	}
	
	public int getBankRollIndex(){// Get method for the players bankIndex
		return this.bankIndex;
	}
	
	public String getName() {// Get method for the players name
		return this.name;
	}
	
	public void gameOver() {// Set method to make the player out of the game by setting isOut to true
		this.isOut = true;
	}
	
	public void setName(String newName) {// Set method for the players name
		this.name = newName;
	}
	
}//End of class
 