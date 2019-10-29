package clueGame;

import java.util.Set;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * A class to control the computer players.*/
public class ComputerPlayer extends Player {
	
	//constructors

	/**Create a new computer player object.
	 * @param row This player's starting row.
	 * @param column This player's starting column.
	 * @param name This player's name.
	 * @param color This player's color as a String, eg. "white"*/
	public ComputerPlayer(int row, int column, String name, String color) {
		super(false, row, column, name, color);
	}
	
	
	//class methods
	
	/**Pick a location on the board to move to from the list of available targets.
	 * @param targets The target list.*/
	public BoardCell pickLocation(Set<BoardCell> targets) {
		
		return null;
		
	}
	
	/**Make an accusation about the murderer.*/
	public void makeAccusation() {}
	
	/**Create a new suggestion for the solution.*/
	public void createSuggestion() {}

}
