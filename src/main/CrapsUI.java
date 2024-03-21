package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class CrapsUI implements ActionListener {
	
	public static CrapsUI eventListener = new CrapsUI();;
	
	//Game window and layered pane
	public static JFrame gameWindow = new JFrame("Alley Craps");
	public static JLayeredPane gamePane;
	
	//Output text area that outputs console text
	public static JTextArea outputText;
	
	//Player selection combo box and button
	public static JComboBox<Integer> playerNumberSelect;
	public static JButton playerNumberSelectButton;
	
	//PLayer name input section
	public static JTextField player1NameInput, player2NameInput, player3NameInput, player4NameInput, player5NameInput, player6NameInput;
	public static JButton submitNamesButton;
	
	//Do you know the rules buttons
	public static JButton noButton;
	public static JButton yesButton;
	
	//Betting UI elements
	public static JComboBox<Integer> betAmountSelect;
	public static JButton betAmountSubmit;
	
	//Styling variables
	public static Font mainFont = new Font("Courier New", Font.BOLD, 16);
	public static Color darkGrey = new Color(42,42,42);
	public static Color mediumGrey = new Color(84,84,84);
	public static Color ivory = new Color(255,255,240);
	    
	public static void initGameWindow() {
		
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
		player1NameInput = new JTextField("Player1 Name");
		player1NameInput.setFont(mainFont);
		player1NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player1NameInput.setBackground(darkGrey);
		player1NameInput.setForeground(ivory);
		player1NameInput.setBounds(325, 100, 150, 30);
		player1NameInput.setCaretColor(ivory);
		player1NameInput.setVisible(false);
		
		player2NameInput = new JTextField("Player2 Name");
		player2NameInput.setFont(mainFont);
		player2NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player2NameInput.setBackground(darkGrey);
		player2NameInput.setForeground(ivory);
		player2NameInput.setBounds(325, 135, 150, 30);
		player2NameInput.setCaretColor(ivory);
		player2NameInput.setVisible(false);
		
		player3NameInput = new JTextField("Player3 Name");
		player3NameInput.setFont(mainFont);
		player3NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player3NameInput.setBackground(darkGrey);
		player3NameInput.setForeground(ivory);
		player3NameInput.setBounds(325, 170, 150, 30);
		player3NameInput.setCaretColor(ivory);
		player3NameInput.setVisible(false);
		
		player4NameInput = new JTextField("Player4 Name");
		player4NameInput.setFont(mainFont);
		player4NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player4NameInput.setBackground(darkGrey);
		player4NameInput.setForeground(ivory);
		player4NameInput.setBounds(325,205, 150, 30);
		player4NameInput.setCaretColor(ivory);
		player4NameInput.setVisible(false);
		
		player5NameInput = new JTextField("Player5 Name");
		player5NameInput.setFont(mainFont);
		player5NameInput.setBorder(BorderFactory.createLineBorder(ivory,1));
		player5NameInput.setBackground(darkGrey);
		player5NameInput.setForeground(ivory);
		player5NameInput.setBounds(325,240, 150, 30);
		player5NameInput.setCaretColor(ivory);
		player5NameInput.setVisible(false);
		
		player6NameInput = new JTextField("Player6 Name");
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
		
		
		//Layered Pane addition area
		gamePane.add(outputText,1);
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
		
		gamePane.add(betAmountSelect,Integer.valueOf(2));
		gamePane.add(betAmountSubmit,Integer.valueOf(2));
		
		
		//Game window init area
		gameWindow.add(gamePane);
		
		gameWindow.pack();
		
		gameWindow.setVisible(true);
		
	}
	
	public static void clearTextOutput() {
		outputText.setText("");
	}
	
	public static void showPlayerSelect() {
		playerNumberSelect.setVisible(true);
		playerNumberSelectButton.setVisible(true);
	}
	
	public static void hidePlayerSelect() {
		playerNumberSelect.setVisible(false);
		playerNumberSelectButton.setVisible(false);
	}
	
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
	
	public static void showYesNoButtons() {
		yesButton.setVisible(true);
		noButton.setVisible(true);
	}
	
	public static void hideYesNoButtons() {
		yesButton.setVisible(false);
		noButton.setVisible(false);
	}
	
	public static void showActionAmountSelection() {
		betAmountSelect.setVisible(true);
		betAmountSubmit.setVisible(true);
	}
	
	public static void hideActionAmountSelect() {
		betAmountSelect.setVisible(false);
		betAmountSubmit.setVisible(false);
	}
	
	
	public static volatile boolean waitingForInput;
	public static void waitForInput() {
		
		waitingForInput = true;
		while(waitingForInput);
		
	}
	
	public static void inputSubmitted() {
		waitingForInput = false;
	}
	
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
		
		}
	}
}
 