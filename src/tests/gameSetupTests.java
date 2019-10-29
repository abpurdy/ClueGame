package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

public class gameSetupTests {
	
	private static Board board;
	
	@Before
	public void init() {
		
		board = Board.getInstance();
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();
		
	}

	@Test
	public void testPlayerLoading() {
		
		//create 3 player objects to test the first, third and last players in config file
		
		Player player1 = new HumanPlayer(0, 0, "Jotaro", "black");
		Player player2 = new ComputerPlayer(0, 0, "Okuyasu", "blue");
		Player player3 = new ComputerPlayer(0, 0, "Josuke", "pink");
		
		assertTrue(player1.equals(board.getPlayers().get(0)));
		assertTrue(player2.equals(board.getPlayers().get(2)));
		assertTrue(player3.equals(board.getPlayers().get(board.getPlayers().size()-1)));
		
	}
	
	@Test
	public void testDeckLoading() {
		
		
		
	}
	
	@Test
	public void testCardDealing() {
		
		
		
	}

}
