package clueGame;

import java.util.Set;

import javax.swing.JOptionPane;

import gui.GUIFrame;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * A class that controls the human player.*/
public class HumanPlayer extends Player {
	
	//constructors

	/**Create a new human player object.
	 * @param row This player's starting row.
	 * @param column This player's starting column.
	 * @param name This player's name.
	 * @param color This player's color as a String, eg. "white"*/
	public HumanPlayer(int row, int column, String name, String color) {
		super(true, row, column, name, color);
	}
	
	public void movePlayer(BoardCell newCell) {
		
		this.row = newCell.getRow();
		this.column = newCell.getColumn();
		
		if(newCell.isRoom()) {
			lastRoom = newCell.getRoomType().charAt(0);
			GUIFrame.doPlayerSuggestion(Board.getInstance().getLegend().get(newCell.getRoomType().charAt(0)));
		}
		
	}
	
	public static void handleAccusation(Solution accusation) {
		if(accusation.equals(Board.getInstance().getSolution())) {
			JOptionPane.showMessageDialog(Board.getInstance(), "You" + " made an accusation of " 
					+ accusation.person + " with the " +
					accusation.weapon + " in the " +
					accusation.room + ".\n" +
					"this is correct and " + "you" + " have won!");
		}
		else {
			JOptionPane.showMessageDialog(Board.getInstance(), "You" + " made an accusation of " 
					+ accusation.person + " with the " +
					accusation.weapon + " in the " +
					accusation.room + ".\n" +
					"this is incorrect so unfortuanately you have lost.");
		}
		
	}

}
