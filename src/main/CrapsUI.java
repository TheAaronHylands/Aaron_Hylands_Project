
/**
 * This is my CrapsUI class
 * This class handles all of the UI related functions of my game.
 */


package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 

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
	    
	//Main method to load the games UI
	public static void initGameWindow() {
		
		//Window frame and pane
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setPreferredSize(new Dimension(800, 600));
		gameWindow.setAutoRequestFocus(true);
		
		gamePane = new JLayeredPane();
		gamePane.setOpaque(true);
		gamePane.setBackground(darkGrey);
		
		
		
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
		playerNumberSelectButton.setBounds(245, 432, 200, 26);
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
		acceptRules.setBounds(705, 470, 80, 30);
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
	
	//Method to clear the outputText text area
	public static void clearTextOutput() {
		outputText.setText("");
	}
	
	/*
	 * Below are methods used to hide and show groups
	 *  of UI elements depending on the stage of the 
	 *  game.
	 */
	
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
	
	/*
	 * These two methods below allow me to pause the execution
	 *  of the program while I want it to wait for the user
	 *  to interact with a UI element, usually a button.
	 */
	
	//Wait for input methods
	private static volatile boolean waitingForInput;
	public static void waitForInput() {
		
		waitingForInput = true;
		while(waitingForInput);
		
	}
	
	public static void inputSubmitted() {
		waitingForInput = false;
	}
	
	/*
	 * This is the actionPerformed() method and it handles
	 *  all of the action events created by the JButtons
	 */
	
	//ActionPerformed switch 
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
					Craps.player1.setName(player1NameInput.getText().trim());
					Craps.player2.setName(player2NameInput.getText().trim());
				}
				if(player3NameInput.isVisible()) {
					Craps.player3.setName(player3NameInput.getText().trim());
				}
				if(player4NameInput.isVisible()) {
					Craps.player4.setName(player4NameInput.getText().trim());
				}
				if(player5NameInput.isVisible()) {
					Craps.player5.setName(player5NameInput.getText().trim());
				}
				if(player6NameInput.isVisible()) {
					Craps.player6.setName(player6NameInput.getText().trim());
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
	
	//Method to fill the rules textArea
	public static void fillRulesText() {
		rulesText.setText("Objective: Players place bets and roll two six sided dice to determine the outcome.\r\n"
				+ "\r\n"
				+ "1. Setup: A “shooter” is designated, this shooter will roll the dice each round until they either lose by running out of money, or decide to pass the dice to the next player at the end of the round. A round ends once the shooter wins or loses.\r\n"
				+ "\r\n"
				+ "2. Betting: The shooter places a starting bet amount called the “action amount” into the “pot”(The place where the bets are held). After the action amount is set, each player in turn places their own bets in the pot in order to “cover” or meet the action amount. (eg. If an action amount of $100 is placed, the next non-shooter player can only  put forwards up to $100, if they do so they will cover the bet and no other players can place bets. If they only put $50 in, then the rest of the players must cover the rest of the bet if able.) If the players cannot cover the entire action amount, the action that is not covered will be returned to the shooter before rolling the “come out”.\r\n"
				+ "\r\n"
				+ "3. Rolling: Once bets are placed, the shooter rolls what is called the “come out” roll. It is important to note that the dice must be rolled and bounced off of a wall to ensure fair rolling. If the shooter rolls a total of 7 or 11, the shooter wins and gets all of the money in the pot. If the shooter rolls a 2, 3, or 12 then the shooter loses, and each player gets twice their bet amount from the pot. If the shooter rolls a 4, 5, 6, 8, 9 or 10, said roll is set as the “point”, and the point phase is entered.\r\n"
				+ "\r\n"
				+ "4. Point Phase: If the point phase is entered, the shooter continuously rolls the dice until they either roll the point set in the come out, or a 7. If the shooter rolls the point then they win the bets, and if they roll a 7 then they lose the bet.\r\n"
				+ "\r\n"
				+ "5. Next Shooter: After the bets have been settled and the shooter either wins or loses, as long as the shooter still has money left they can choose to shoot again. Alternatively they can choose to pass the dice to the next player, and that player is now the shooter. As long as there is more than one player with money left, the game continues and the shooter then rolls a come out roll.\r\n"
				+ "\r\n"
				+ "6. Winning or Losing: If a player runs out of money, then they lose and are out of the game. Once a player has acquired all of the money from the other players then they win!\r\n"
				+ "");
	}
	
}// End of class
 