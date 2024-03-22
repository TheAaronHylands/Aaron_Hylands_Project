package main;

import java.util.Random;

public class Die {
	
	/* This is my Die class.
	 * Die objects hold the random number generator within them as well as 
	 *  the amount of sides the die has. Although only six sided dice are
	 *  needed for craps, I felt it would be interesting to be able to make
	 *  any sided dice that I wanted.
	 */
	
	int sidesOnDie;
	Random randomGenerator = new Random();
	
	Die(int sidesOnDieInput){// Constructor that requires the number of sides on the die when created
		sidesOnDie = sidesOnDieInput;
	}
	
	public int roll() {// Roll method that returns what the die has rolled
		
		int result = randomGenerator.nextInt(sidesOnDie) + 1;
		
		return result;
		
	}
	
	
	
} //End of class
 