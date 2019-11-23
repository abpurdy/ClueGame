package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import clueGame.Card.CardType;
import gui.GUIFrame;

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
		BoardCell currentCell = Board.getInstance().getCellAt(row,column);
		if(currentCell.isRoom() &&
				currentCell.getRoomType().equals(Board.getInstance().getSolution().room) &&
				Board.getInstance().getDeck().size() == seenCards.size() + 3) {
			
			JOptionPane.showMessageDialog(Board.getInstance(), name + " made an accusation of " 
					+ Board.getInstance().getSolution().person + " with the " +
					Board.getInstance().getSolution().weapon + " in the " +
					Board.getInstance().getSolution().room + ".\n" +
					"this is correct and " + name + " has won!");
		}
	}

	/**Create a new suggestion for the solution.*/
	public Solution createSuggestion(Board board) {
		//gets the name of the current room
		String room = board.getLegend().get(board.getCellAt(row, column).getRoomType().charAt(0));
		Random random = new Random(System.currentTimeMillis()); //create random generator
		//gets all weapons available for guessing
		ArrayList<Card> weapons = new ArrayList<Card>(board.getWeaponDeck());
		//removes seen weapons from guesses available
		for(Card seenWeapons:seenCards) {
			if(seenWeapons.getType().equals(CardType.WEAPON)) {
				weapons.remove(weapons.indexOf(seenWeapons));
			}
		}
		//removes my weapons from weapons available
		for(Card myWeapons:myCards) {
			if(myWeapons.getType().equals(CardType.WEAPON)) {
				weapons.remove(weapons.indexOf(myWeapons));
			}
		}
		//selects only weapon if there is one left
		String weapon = "";
		if(weapons.size() == 1) {
			weapon = weapons.get(0).getName();
		}
		else {
			//else returns a random available weapon
			weapon = weapons.get(random.nextInt(weapons.size())).getName();
		}
		
		//gets all people available for guessing
		ArrayList<Card> people = new ArrayList<Card>(board.getPersonDeck());
		//removes seen weapons from guesses available
		for(Card seenPeople:seenCards) {
			if(seenPeople.getType().equals(CardType.PERSON)) {
				people.remove(people.indexOf(seenPeople));
			}
		}
		//removes my weapons from weapons available
		for(Card myPeople:myCards) {
			if(myPeople.getType().equals(CardType.PERSON)) {
				people.remove(people.indexOf(myPeople));
			}
		}
		//selects only weapon if there is one left
		String person = "";
		if(people.size() == 1) {
			person = people.get(0).getName();
		}
		else {
			//else returns a random available weapon
			person = people.get(random.nextInt(people.size())).getName();
		}
		
		GUIFrame.gui.control.setCurrentGuess(person, room, weapon);
		return new Solution(person, room ,weapon);
	}

}
