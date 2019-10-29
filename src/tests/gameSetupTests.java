package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;

public class gameSetupTests {
	
	private static Board board;
	
	@Before
	public void init() {
		
		board = Board.getInstance();
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt");
		board.initialize();
		
	}

	@Test
	public void testPlayerLoading() {
		
		
		
	}
	
	@Test
	public void testDeckLoading() {
		
		
		
	}
	
	@Test
	public void testCardDealing() {
		
		
		
	}

}
