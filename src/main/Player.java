package main;


public class Player {
	String name;
	int bankIndex;
	
	public int getBankRollIndex(){
		return this.bankIndex;
	}
	
	public void setBankRollIndex(int newIndex) {
		this.bankIndex = newIndex;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
}//End of class
 