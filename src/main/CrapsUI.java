package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class CrapsUI {
	
	public static JFrame gameWindow;
	public static JLayeredPane gamePane;
	
	public static JTextArea outputText;
	public static JScrollPane outputTextScrollPane;
	
	public static Font mainFont = new Font("Courier New", Font.PLAIN, 16);
	public static Color darkGrey = new Color(42,42,42);
	public static Color ivory = new Color(255,255,240);
	    
	public static void initGameWindow() {
		
		gameWindow = new JFrame("Alley Craps");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setPreferredSize(new Dimension(800, 600));
		gameWindow.setFont(mainFont);
		
		gamePane = new JLayeredPane();
		gamePane.setOpaque(true);
		gamePane.setBackground(darkGrey);
		gamePane.setFont(mainFont);
		
		gameWindow.add(gamePane);
		
		gameWindow.pack();
		
		gameWindow.setVisible(true);
		
	}
	
}
 