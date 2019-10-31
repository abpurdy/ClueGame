package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;

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
		board.calcTargets(4, 4, 4);
		Map <BoardCell, Integer> cellCount = new HashMap <BoardCell, Integer>();
		
		for(int i = 0; i < 1000; i++) {
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, cellCount.get(chosen)+1);
			cellCount.put(chosen, cellCount.get(chosen)+1);
			
		}
		
	}
	
	@Test
	public void testAccusationCheck() {
		
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
