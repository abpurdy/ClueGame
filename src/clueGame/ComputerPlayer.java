package clueGame;

import java.util.Random;
import java.util.Set;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * A class to control the computer players.*/
public class ComputerPlayer extends Player {
	/**previous cell**/
	private BoardCell previousCell;
	
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
		//picks a room if it is an option and not where the player came from
		for(BoardCell cell : targets) {
			if(cell.isDoorway() && !cell.equals(previousCell)) {
				return cell;
			}
		}
		//randomly chooses a target otherwise
		Random random = new Random(System.currentTimeMillis()); //create random generator
		BoardCell targetArr[] = new BoardCell[targets.size()];
		targetArr = targets.toArray(targetArr);
	
		return targetArr[random.nextInt(targets.size())];
	}
	
	public BoardCell getPreviousCell() {
		return previousCell;
	}


	public void setPreviousCell(BoardCell previousCell) {
		this.previousCell = previousCell;
	}


	/**Make an accusation about the murderer.*/
	public void makeAccusation() {
		
	}
	
	/**Create a new suggestion for the solution.*/
	public void createSuggestion() {
		
	}

}
