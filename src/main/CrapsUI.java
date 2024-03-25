
/**
 * This is my CrapsUI class
 * This class handles all of the UI related functions of my game.
 */


package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
 

public class CrapsUI implements ActionListener {
	
	/*
	 * I declare all of these UI elements as public static so that they
	 *  can be accessed from anywhere in the program.
	 */
	
	
	//The event listener
	public static CrapsUI eventListener = new CrapsUI();;
	
	//Game window and layered pane
	public static JFrame gameWindow = new JFrame("Alley Craps");
	public static JLayeredPane gamePane;
	
	//BankUI display
	public static JTextArea bankAmountDisplay;
	
	//Output text area that outputs console text
	public static JTextArea outputText;
	
	//Player selection combo box and button
	public static JComboBox<Integer> playerNumberSelect;
	public static JButton playerNumberSelectButton;
	
	//Player name input section
	public static PromptTextField player1NameInput, player2NameInput, player3NameInput, player4NameInput, player5NameInput, player6NameInput;
	public static JButton submitNamesButton;
	
	//Do you know the rules buttons
	public static JButton noButton;
	public static JButton yesButton;
	
	//Rules display
	public static JTextArea rulesText;
	public static JScrollPane rulesTextScrollPane;
	public static JButton acceptRules;
	
	//Betting UI elements
	public static JComboBox<Integer> betAmountSelect;
	public static JButton betAmountSubmit;
	
	//Shoot or pass buttons
	public static JButton shootAgainButton;
	public static JButton passDiceButton;
	
	//Styling variables
	public static Font mainFont = new Font("Courier New", Font.BOLD, 16);
	public static Color darkGrey = new Color(42,42,42);
	public static Color mediumGrey = new Color(84,84,84);
	public static Color ivory = new Color(255,255,240);
	
	
	
	/**
	Name: 		initGameWindow
	Parameters:	N/A
	Return:		void
	Purpose:	This loads the gameUI
	*/
	
	//Main method to load the games UI
	public static void initGameWindow() {
		
		//Window frame and pane
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setPreferredSize(new Dimension(800, 600));
		gameWindow.setAutoRequestFocus(true);
		gameWindow.setUndecorated(false);
		gameWindow.setAlwaysOnTop(true);
		
		gamePane = new JLayeredPane();
		gamePane.setOpaque(true);
		gamePane.setBackground(darkGrey);
		gamePane.setBorder(BorderFactory.createLineBorder(ivory, 3));
		
		
		
		//Bank display UI
		bankAmountDisplay = new JTextArea("");
		bankAmountDisplay.setBackground(darkGrey);
		bankAmountDisplay.setForeground(ivory);
		bankAmountDisplay.setFont(mainFont);
		bankAmountDisplay.setBorder(BorderFactory.createLineBorder(ivory, 2));
		bankAmountDisplay.setBounds(7, 7, 120, 50);
		bankAmountDisplay.setVisible(false);
		
		
		
		//Output text area
		outputText = new JTextArea();
		outputText.setEditable(false);
		outputText.setWrapStyleWord(true);
		outputText.setLineWrap(true);
		outputText.setBackground(darkGrey);
		outputText.setForeground(ivory);
		outputText.setFont(mainFont);
		outputText.setBounds(200,100,400,320);
		outputText.setBorder(BorderFactory.createEmptyBorder());
		
		
		
		//Number of players selection
		playerNumberSelect = new JComboBox<Integer>();
		playerNumberSelect.setEditable(false);
		playerNumberSelect.setBackground(darkGrey);
		playerNumberSelect.setForeground(ivory);
		playerNumberSelect.setBorder(BorderFactory.createLineBorder(ivory, 1));
		playerNumberSelect.setBounds(450, 430, 40, 30);
		playerNumberSelect.setFont(mainFont);
		playerNumberSelect.addItem(2);
		playerNumberSelect.addItem(3);
		playerNumberSelect.addItem(4);
		playerNumberSelect.addItem(5);
		playerNumberSelect.addItem(6);
		playerNumberSelect.setVisible(false);
	
		playerNumberSelectButton = new JButton("Select # Of Players");
		playerNumberSelectButton.addActionListener(eventListener);
		playerNumberSelectButton.setFont(mainFont);
		playerNumberSelectButton.setBounds(245, 430, 200, 30);
		playerNumberSelectButton.setBackground(mediumGrey);
		playerNumberSelectButton.setForeground(ivory);
		playerNumberSelectButton.setBorder(BorderFactory.createLineBorder(ivory,1));
		playerNumberSelectButton.setVisible(false);
		
		
		
		// Name input section
		player1NameInput = new PromptTextField("Player1 Name");
		player1NameInput.setFont(mainFont);
		player1NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player1NameInput.setBackground(darkGrey);
		player1NameInput.setForeground(ivory);
		player1NameInput.setBounds(325, 100, 150, 30);
		player1NameInput.setCaretColor(ivory);
		player1NameInput.setVisible(false);
		
		player2NameInput = new PromptTextField("Player2 Name");
		player2NameInput.setFont(mainFont);
		player2NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player2NameInput.setBackground(darkGrey);
		player2NameInput.setForeground(ivory);
		player2NameInput.setBounds(325, 135, 150, 30);
		player2NameInput.setCaretColor(ivory);
		player2NameInput.setVisible(false);
		
		player3NameInput = new PromptTextField("Player3 Name");
		player3NameInput.setFont(mainFont);
		player3NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player3NameInput.setBackground(darkGrey);
		player3NameInput.setForeground(ivory);
		player3NameInput.setBounds(325, 170, 150, 30);
		player3NameInput.setCaretColor(ivory);
		player3NameInput.setVisible(false);
		
		player4NameInput = new PromptTextField("Player4 Name");
		player4NameInput.setFont(mainFont);
		player4NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player4NameInput.setBackground(darkGrey);
		player4NameInput.setForeground(ivory);
		player4NameInput.setBounds(325,205, 150, 30);
		player4NameInput.setCaretColor(ivory);
		player4NameInput.setVisible(false);
		
		player5NameInput = new PromptTextField("Player5 Name");
		player5NameInput.setFont(mainFont);
		player5NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player5NameInput.setBackground(darkGrey);
		player5NameInput.setForeground(ivory);
		player5NameInput.setBounds(325,240, 150, 30);
		player5NameInput.setCaretColor(ivory);
		player5NameInput.setVisible(false);
		
		player6NameInput = new PromptTextField("Player6 Name");
		player6NameInput.setFont(mainFont);
		player6NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player6NameInput.setBackground(darkGrey);
		player6NameInput.setForeground(ivory);
		player6NameInput.setBounds(325,275, 150, 30);
		player6NameInput.setCaretColor(ivory);
		player6NameInput.setVisible(false);
		
		submitNamesButton = new JButton("Submit Names");
		submitNamesButton.addActionListener(eventListener);
		submitNamesButton.setFont(mainFont);
		submitNamesButton.setBorder(BorderFactory.createLineBorder(ivory,1));
		submitNamesButton.setBackground(mediumGrey);
		submitNamesButton.setForeground(ivory);
		submitNamesButton.setBounds(325,310, 150, 30);
		submitNamesButton.setVisible(false);
		
		
		
		// Yes/No buttons
		yesButton = new JButton("Yes");
		yesButton.addActionListener(eventListener);
		yesButton.setFont(mainFont);
		yesButton.setBorder(BorderFactory.createLineBorder(ivory,1));
		yesButton.setBackground(mediumGrey);
		yesButton.setForeground(ivory);
		yesButton.setBounds(325,400, 75, 30);
		yesButton.setVisible(false);
		
		noButton = new JButton("No");
		noButton.addActionListener(eventListener);
		noButton.setFont(mainFont);
		noButton.setBorder(BorderFactory.createLineBorder(ivory,1));
		noButton.setBackground(mediumGrey);
		noButton.setForeground(ivory);
		noButton.setBounds(405,400, 75, 30);
		noButton.setVisible(false);
		
		
		
		//Rules display
		rulesText = new JTextArea();
		rulesText.setBackground(darkGrey);
		rulesText.setForeground(ivory);
		rulesText.setFont(mainFont);
		rulesText.setEditable(false);
		rulesText.setLineWrap(true);
		rulesText.setWrapStyleWord(true);
		fillRulesText();//This simply fills the rules text area
		
		rulesTextScrollPane = new JScrollPane(rulesText);
		rulesTextScrollPane.setBorder(BorderFactory.createLineBorder(ivory,1));
		rulesTextScrollPane.getHorizontalScrollBar().setVisible(false);
		rulesTextScrollPane.setBounds(75, 50, 625, 450);
		rulesTextScrollPane.setVisible(false);
		
		acceptRules = new JButton("Accept");
		acceptRules.addActionListener(eventListener);
		acceptRules.setBackground(mediumGrey);
		acceptRules.setForeground(ivory);
		acceptRules.setFont(mainFont);
		acceptRules.setBorder(BorderFactory.createLineBorder(ivory,1));
		acceptRules.setBounds(702, 470, 80, 30);
		acceptRules.setVisible(false);
		
		
		
		//Bet amount combo box
		betAmountSelect = new JComboBox<Integer>();
		betAmountSelect.setEditable(false);
		betAmountSelect.setBackground(darkGrey);
		betAmountSelect.setForeground(ivory);
		betAmountSelect.setFont(mainFont);
		betAmountSelect.setBorder(BorderFactory.createLineBorder(ivory, 1));
		betAmountSelect.setBounds(330, 300, 60, 30);
		betAmountSelect.setVisible(false);
		
		betAmountSubmit = new JButton("Submit");
		betAmountSubmit.addActionListener(eventListener);
		betAmountSubmit.setFont(mainFont);
		betAmountSubmit.setBorder(BorderFactory.createLineBorder(ivory,1));
		betAmountSubmit.setBackground(mediumGrey);
		betAmountSubmit.setForeground(ivory);
		betAmountSubmit.setBounds(395, 300, 75, 30);
		betAmountSubmit.setVisible(false);
		
		
		
		//Shoot or pass buttons
		shootAgainButton = new JButton("Shoot Again");
		shootAgainButton.addActionListener(eventListener);
		shootAgainButton.setFont(mainFont);
		shootAgainButton.setBorder(BorderFactory.createLineBorder(ivory,1));
		shootAgainButton.setBackground(mediumGrey);
		shootAgainButton.setForeground(ivory);
		shootAgainButton.setBounds(280,400, 120, 30);
		shootAgainButton.setVisible(false);
		
		passDiceButton = new JButton("Pass");
		passDiceButton.addActionListener(eventListener);
		passDiceButton.setFont(mainFont);
		passDiceButton.setBorder(BorderFactory.createLineBorder(ivory,1));
		passDiceButton.setBackground(mediumGrey);
		passDiceButton.setForeground(ivory);
		passDiceButton.setBounds(405,400, 80, 30);
		passDiceButton.setVisible(false);
		
		
		
		//Layered Pane addition area
		gamePane.add(outputText,Integer.valueOf(1));
		gamePane.add(playerNumberSelect,Integer.valueOf(2));
		gamePane.add(playerNumberSelectButton,Integer.valueOf(2));
		
		gamePane.add(bankAmountDisplay,Integer.valueOf(3));
		
		gamePane.add(player1NameInput,Integer.valueOf(2));
		gamePane.add(player2NameInput,Integer.valueOf(2));
		gamePane.add(player3NameInput,Integer.valueOf(2));
		gamePane.add(player4NameInput,Integer.valueOf(2));
		gamePane.add(player5NameInput,Integer.valueOf(2));
		gamePane.add(player6NameInput,Integer.valueOf(2));
		gamePane.add(submitNamesButton,Integer.valueOf(2));
		
		gamePane.add(yesButton,Integer.valueOf(2));
		gamePane.add(noButton,Integer.valueOf(2));
		
		gamePane.add(rulesTextScrollPane,Integer.valueOf(10));
		gamePane.add(acceptRules,Integer.valueOf(10));
		
		gamePane.add(betAmountSelect,Integer.valueOf(2));
		gamePane.add(betAmountSubmit,Integer.valueOf(2));
		
		gamePane.add(shootAgainButton,Integer.valueOf(2));
		gamePane.add(passDiceButton,Integer.valueOf(2));
		
		
		//Get the game window frame ready and display it
		gameWindow.add(gamePane);
		
		gameWindow.pack();
		
		gameWindow.setVisible(true);
		
	}

//===================================================================================
	
	/**
	Name: 		clearTextOutput
	Parameters:	N/A
	Return:		void
	Purpose:	This method clears the outputText textArea
	*/

	public static void clearTextOutput() {
		outputText.setText("");
	}
	
//===================================================================================
	/*
	 * Below are methods used to hide and show groups
	 *  of UI elements depending on the stage of the 
	 *  game. I decided not to provide the usual naming
	 *  standard for these methods since they are 
	 *  extra-curricular, and it would only serve
	 *  to clutter the code in this case.
	 */
	//Bank display
	public static void showBankDisplay() {
		bankAmountDisplay.setVisible(true);
	}
	public static void hideBankDisplay() {
		bankAmountDisplay.setVisible(false);
	}
	
	//Player number select UI element methods
	public static void showPlayerSelect() {
		playerNumberSelect.setVisible(true);
		playerNumberSelectButton.setVisible(true);
	}
	public static void hidePlayerSelect() {
		playerNumberSelect.setVisible(false);
		playerNumberSelectButton.setVisible(false);
	}
	
	//Player name input UI element methods
	public static void showPlayerNameInput() {
		player1NameInput.setVisible(true);
		player2NameInput.setVisible(true);
		player3NameInput.setVisible(true);
		player4NameInput.setVisible(true);
		player5NameInput.setVisible(true);
		player6NameInput.setVisible(true);
		submitNamesButton.setVisible(true);
	}
	public static void hidePlayerNameInput() {
		player1NameInput.setVisible(false);
		player2NameInput.setVisible(false);
		player3NameInput.setVisible(false);
		player4NameInput.setVisible(false);
		player5NameInput.setVisible(false);
		player6NameInput.setVisible(false);
		submitNamesButton.setVisible(false);
	}
	
	//YesNo buttons UI element methods
	public static void showYesNoButtons() {
		yesButton.setVisible(true);
		noButton.setVisible(true);
	}
	public static void hideYesNoButtons() {
		yesButton.setVisible(false);
		noButton.setVisible(false);
	}
	
	//Rules window UI element methods
	public static void showRulesWindow() {
		rulesTextScrollPane.setVisible(true);
		acceptRules.setVisible(true);
	}
	public static void hideRulesWindow() {
		rulesTextScrollPane.setVisible(false);
		acceptRules.setVisible(false);
	}
	
	//Bet amount UI element methods
	public static void showBetAmountSelection() {
		betAmountSelect.setVisible(true);
		betAmountSubmit.setVisible(true);
	}
	public static void hideBetAmountSelect() {
		betAmountSelect.setVisible(false);
		betAmountSubmit.setVisible(false);
	}
	
	//Query pass buttons UI element methods
	public static void showQueryPassButtons() {
		shootAgainButton.setVisible(true);
		passDiceButton.setVisible(true);
	}
	public static void hideQueryPassButtons() {
		shootAgainButton.setVisible(false);
		passDiceButton.setVisible(false);
	}
	
//===================================================================================
	//Wait for input methods
	
	/**
	Name: 		waitForInput
	Parameters:	N/A
	Return:		void
	Purpose:	This makes the program wait until inputSubmitted() is called.
				I use this to have the program wait until the user clicks a button.
	*/
	
	private static volatile boolean waitingForInput;
	public static void waitForInput() {
		
		waitingForInput = true;
		while(waitingForInput);
		
	}
	
	/**
	Name: 		inputSubmitted
	Parameters:	N/A
	Return:		void
	Purpose:	This method releases the while loop in waitForInput() and resumes
				 program execution
	*/
	
	public static void inputSubmitted() {
		waitingForInput = false;
	}
	
//===================================================================================
	/**
	Name: 		actionPerformed
	Parameters:	ActionEvent event
	Return:		void
	Purpose:	This handles all of the action events created by the JButtons
	 			 with a switch statement
	*/
	
	public void actionPerformed(ActionEvent event) {
		String submission = event.getActionCommand();
		switch(submission) {
			case("Select # Of Players"): {
				Craps.numberOfPlayers = playerNumberSelect.getItemAt(playerNumberSelect.getSelectedIndex());
				inputSubmitted();
				break;
			}
			case("Submit Names"):{
				if(player1NameInput.isVisible() && player2NameInput.isVisible()) {
					CrapsHelper.player1.setName(player1NameInput.getText().trim());
					CrapsHelper.player2.setName(player2NameInput.getText().trim());
				}
				if(player3NameInput.isVisible()) {
					CrapsHelper.player3.setName(player3NameInput.getText().trim());
				}
				if(player4NameInput.isVisible()) {
					CrapsHelper.player4.setName(player4NameInput.getText().trim());
				}
				if(player5NameInput.isVisible()) {
					CrapsHelper.player5.setName(player5NameInput.getText().trim());
				}
				if(player6NameInput.isVisible()) {
					CrapsHelper.player6.setName(player6NameInput.getText().trim());
				}
				inputSubmitted();
				break;
			}
			case("Yes"):{
				CrapsHelper.yesNo = "yes";
				inputSubmitted();
				break;
			}
			case("No"): {
				CrapsHelper.yesNo = "no";
				inputSubmitted();
				break;
			}
			case("Submit"): {
				inputSubmitted();
				break;
			}
			case("Accept"): {
				inputSubmitted();
				break;
			}
			case("Shoot Again"): {
				CrapsHelper.willPass = false;
				inputSubmitted();
				break;
			}
			case("Pass"): {
				CrapsHelper.willPass = true;
				inputSubmitted();
				break;
			}
		
		}// End of submission switch
	}// End of actionPeformed

//===================================================================================
	
	/**
	Name: 		fillRulesText
	Parameters:	N/A
	Return:		void
	Purpose:	This method fills the rules textArea when initializing UI
	*/

	public static void fillRulesText() {
		rulesText.setText("Objective: 2-6 players place bets and roll two six sided dice to determine the outcome.\r\n"
				+ "\r\n"
				+ "1. Setup: A “shooter” is designated. The shooter will roll the dice each round until they either: decide to pass the dice to the next player at the end of the round, or lose by running out of money. A round ends once the shooter wins or loses.\r\n"
				+ "\r\n"
				+ "2. Betting: The shooter places a starting bet amount called the “action amount” into the  place where the bets are held or the “pot”. Once the action amount is set, each player in turn places their own bets in the pot in order to “cover” or meet the action amount. (eg. If an action amount of $100 is placed, the next non-shooter player can only  put forwards up to $100, if they do so they will cover the bet and no other players can place bets. If they only put $50 in, then the next players must cover the rest of the bet if able.) If the players are incapable of covering the entire action amount, the action that is not covered will be returned to the shooter before rolling the “come out” roll.\r\n"
				+ "\r\n"
				+ "3. Rolling: Once bets are placed, the shooter rolls what is called the “come out” roll. It is important to note that the dice must be rolled and bounced off of a wall to ensure a fair roll has been madel. If the shooter rolls a total of 7 or 11, the shooter wins and gets all of the money in the pot. If the shooter rolls a 2, 3, or 12 then the shooter loses, and each player gets twice their bet amount from the pot. If the shooter rolls a 4, 5, 6, 8, 9 or 10, said roll is set as the “point”, and the point phase is entered.\r\n"
				+ "\r\n"
				+ "4. Point Phase: If the point phase is entered, the shooter continuously rolls the dice until they either roll the point set in the come out, or a 7. If the shooter rolls the point then they win the bets, and if they roll a 7 then they lose the bet.\r\n"
				+ "\r\n"
				+ "5. Next Shooter: After the bets have been settled and the shooter either wins or loses, as long as the shooter still has money left they can choose to shoot again. Alternatively they can choose to pass the dice to the next player, and that player is now the shooter. As long as there is more than one player with money left, the game continues and the shooter then rolls a come out roll.\r\n"
				+ "\r\n"
				+ "6. Winning or Losing: If a player runs out of money, then they lose and are out of the game. Once a player has acquired all of the money from the other players then they win!");
	}
	
//===================================================================================
	/**
	Name: 		configureBankDisplay
	Parameters:	N/A
	Return:		void
	Purpose:	This method sets up the bank display in the top left,
	 			 configuring the size for name length and amount of players.
	*/
	
	private static int nameLengthMax = 0;
	public static void configureBankDisplay() {
		
		ArrayList<Integer> nameLengthHolder = new ArrayList<Integer>();
		
		Craps.playerArray.forEach((player) -> {
			nameLengthHolder.add(player.getName().length());
		});
		
		
		nameLengthHolder.forEach((nameLength) -> {
			if(nameLength > nameLengthMax) {
				nameLengthMax = nameLength;
			}
		});
		
		
		/* This cuts off the size to ensure the
		 *  bank display doesn't overlap the
		 *  output text, as well as to ensure
		 *  that it is large enough to hold the
		 *  label line of "Name | Bank"
		 */
		if(nameLengthMax > 11) {
			nameLengthMax = 11;
		} else if (nameLengthMax < 5) {
			nameLengthMax = 5;
		}
		
		
		
		int bankDisplayHeight = (Craps.numberOfPlayers * 20) + 20;
		int bankDisplayWidth = (nameLengthMax * 10) + 70;
		
		CrapsUI.bankAmountDisplay.setBounds(7, 7, bankDisplayWidth, bankDisplayHeight);
		
		
	}
	
//===================================================================================
	/**
	Name: 		updateBankDisplay
	Parameters:	N/A
	Return:		void
	Purpose:	This method updates the bank amount display in the top left of the screen
	*/

	public static void updateBankDisplay() {
		bankAmountDisplay.setText("Name | Bank\n");
		Craps.playerArray.forEach((player) -> {
			String subStringHolder;
			if (player.getName().length() <= 11) {
				bankAmountDisplay.setText(bankAmountDisplay.getText() + player.getName()
				+ " | $" 
				+ Craps.bankRollArray.get(player.getBankRollIndex())
				+ "\n");
			} else {
				subStringHolder = player.getName().substring(0,9) + "..";
				bankAmountDisplay.setText(bankAmountDisplay.getText() + subStringHolder
						+ " | $" 
						+ Craps.bankRollArray.get(player.getBankRollIndex())
						+ "\n");
			}
		});
	}
	
	
//===================================================================================
	/**
	Name: 		celebrateUntilExit
	Parameters:	N/A
	Return:		void
	Purpose:	This celebrates by changing the outputText foreground
				 color until the program is terminated.
	*/
	
	
	//These color variables are exclusively for the celebrateUntilExit() method
	private static Color red = new Color(255,0,0);
	private static Color orange = new Color(255, 123, 0);
	private static Color yellow = new Color(255,255,0);
	private static Color green = new Color(0,255,0);
	private static Color blue = new Color(0,0,255);
	private static Color violet = new Color(125, 0, 255);
	
	private static int celebrateTime = 100;
	public static void celebrateUntilExit() {
		//I finally found a use for an infinite loop!
		
		
		while(true) {
			
			outputText.setForeground(red);
			CrapsHelper.sleep(celebrateTime);
			outputText.setForeground(orange);
			CrapsHelper.sleep(celebrateTime);
			outputText.setForeground(yellow);
			CrapsHelper.sleep(celebrateTime);
			outputText.setForeground(green);
			CrapsHelper.sleep(celebrateTime);
			outputText.setForeground(blue);
			CrapsHelper.sleep(celebrateTime);
			outputText.setForeground(violet);
			CrapsHelper.sleep(celebrateTime);
			
		}
	}
//===================================================================================
	
}// End of class
 