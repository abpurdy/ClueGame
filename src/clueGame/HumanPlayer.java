package clueGame;

import java.util.Set;

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
	}

}
