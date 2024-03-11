package main;

import java.util.Random;

public class Die {
	int sidesOnDie;
	Random randomGenerator = new Random();
	
	Die(int sidesOnDieInput){
		sidesOnDie = sidesOnDieInput;
	}
	
	public int roll() {
		
		int result = randomGenerator.nextInt(sidesOnDie) + 1;
		
		return result;
		
	}
	
	
	
} //End of class
 