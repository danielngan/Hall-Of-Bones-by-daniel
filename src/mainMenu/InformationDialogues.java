package mainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The dialogue box of the instructions;
 * @author Yi Ngan
 *
 */


public class InformationDialogues  {
	
	
	/**
	 * Method that prints out the dialogue of the game information.
	 * @param parent
	 */
	public static void showInstructions(Component parent) {
		JOptionPane.showMessageDialog(parent, 
	    		"<html>" + 
		    "<h2>Dungeon:</h2>" + 
	    		"<p>- Use the left and right arrow keys to move</p>" + 
	    		"<p></p>" + 
	    		"<h2>Battle:</h1>" + 
	    		"<p>- Use your mouse to select one of the moves on the bottom of the screen</p>" + 
	    		"<p>- Watch your health bar (green) and ability points bar (blue). If you run out of health, you lose!</p>" + 
	    		"<p>- Deplete the enemy party's health points to win</p>" + 
	    		"<p></p>" + 
	    		"</html>", "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Method that prints out the dialogue the people who created this game.
	 * @param parent
	 */
	public static void showCredits(Component parent) {
		JOptionPane.showMessageDialog(parent, 
	    		"<html>" + 
	    		"<h2>Hall of Bones</h2>" + 
	    		"<p>A game by Kevin Joseph, Andrew Fuoco, Anton Thomas, Alan Tang, and Yi Ngan for EECS2030</p>" + 
	    		"</html>", "Credits", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Instruction");
		frame.getContentPane().setLayout(new FlowLayout());
		JButton instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformationDialogues.showInstructions(frame);
			}
		});
		JButton creditsButton = new JButton("Credits");
		creditsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformationDialogues.showCredits(frame);
			}
		});
		frame.getContentPane().add(instructionsButton);
		frame.getContentPane().add(creditsButton);
		frame.pack();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	

}

