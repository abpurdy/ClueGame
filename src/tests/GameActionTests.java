package tests;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.Solution;

public class GameActionTests {

	private static Board board;

	@BeforeClass
	public static void setup() {
		//TODO setup board object
		board = Board.getInstance();
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();
	}

	@Test
	public void testCPUTargetSelection() {
		ComputerPlayer compPlayer = new ComputerPlayer(4,4,"Test","white");
		board.calcTargets(7, 3, 1);
		//First case, no rooms in list
		//resets the cell count map
		Map <BoardCell, Integer> cellCount = new HashMap <BoardCell, Integer>();
	
		//test random target selection
		for(int i = 0; i < 100000; i++) {
			//selects the targets 100000 times and keeps track of how many times each target is chosen
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, 0);
			cellCount.put(chosen, cellCount.get(chosen)+1);
		}

		//ensures that each cell was chosen at least once
		assertTrue(cellCount.size() == 3);
		//makes sure that each target was chosen a significant number of times
		for(int count : cellCount.values()) {
			assertTrue(count >= 5000);
		}
		
		board.calcTargets(7, 3, 2);
		cellCount = new HashMap <BoardCell, Integer>();
		//second case room is in list and not visited previously
		//test random target selection
		for(int i = 0; i < 100; i++) {
			//selects the targets 100 times and keeps track of how many times each target is chosen
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, 0);
			cellCount.put(chosen, cellCount.get(chosen)+1);
		}
		//tests that the doorway is chosen every time
		for(BoardCell cell : cellCount.keySet()) {
			if(cell.isDoorway()) {
				//there is only one doorway as a target option so it should be chosen every time
				assertTrue(cellCount.get(cell) == 100);
			}
			else {
				//none of the other options should ever be chosen
				assertTrue(cellCount.get(cell) == 0);
			}
		}
		
		
		//third case, room is in list and visited previously
		//resets the cell count map
		cellCount = new HashMap <BoardCell, Integer>();
		//sets the previous cell to the one doorway in the target options
		//so selection should now be random
		compPlayer.setPreviousCell(board.getCellAt(6, 2));

		//test random target selection
		for(int i = 0; i < 100000; i++) {
			//selects the targets 100000 times and keeps track of how many times each target is chosen
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, 0);
			cellCount.put(chosen, cellCount.get(chosen)+1);
		}

		//ensures that each cell was chosen at least once
		assertTrue(cellCount.size() == 7);
		//makes sure that each target was chosen a significant number of times
		for(int count : cellCount.values()) {
			assertTrue(count >= 1000);
		}

	}

	@Test
	public void testAccusationCheck() {
		//case where accusation is correct
		Solution accusation = board.getSolution();
		assertTrue(board.checkAccusation(accusation));
		//case where weapon is incorrect
		accusation.weapon = "wrong";
		assertFalse(board.checkAccusation(accusation));
		//case where person is incorrect
		accusation = board.getSolution();
		accusation.person = "wrong";
		assertFalse(board.checkAccusation(accusation));
		//case where room in incorrect
		accusation = board.getSolution();
		accusation.room = "wrong";
		assertFalse(board.checkAccusation(accusation));
	}

	@Test
	public void testDisproveSuggestion() {

	}

	@Test
	public void testSuggestionHandling() {

	}

	@Test
	public void testCreateSuggestion() {

	}

}
