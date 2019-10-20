package tests;

/*
 * This program tests that adjacencies and targets are calculated correctly.
 */

import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(9, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(1, 10);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(17, 12);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(2, 21);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(21, 21);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(11, 1);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 2)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(13, 19);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 18)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(8, 12);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(9, 12)));
		//TEST DOORWAY UP
		testList = board.getAdjList(17, 2);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(16, 2)));
		//TEST DOORWAY DOWN, WHERE THERE'S A WALKWAY TO THE LEFT
		testList = board.getAdjList(5, 19);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 19)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(11, 2);
		assertTrue(testList.contains(board.getCellAt(11, 3)));
		assertTrue(testList.contains(board.getCellAt(11, 1)));
		assertTrue(testList.contains(board.getCellAt(10, 2)));
		assertTrue(testList.contains(board.getCellAt(12, 2)));
		assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(9, 12);
		assertTrue(testList.contains(board.getCellAt(9, 13)));
		assertTrue(testList.contains(board.getCellAt(9, 11)));
		assertTrue(testList.contains(board.getCellAt(8, 12)));
		assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(13, 18);
		assertTrue(testList.contains(board.getCellAt(13, 19)));
		assertTrue(testList.contains(board.getCellAt(13, 17)));
		assertTrue(testList.contains(board.getCellAt(14, 18)));
		assertTrue(testList.contains(board.getCellAt(12, 18)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(16, 2);
		assertTrue(testList.contains(board.getCellAt(15, 2)));
		assertTrue(testList.contains(board.getCellAt(17, 2)));
		assertTrue(testList.contains(board.getCellAt(16, 3)));
		assertTrue(testList.contains(board.getCellAt(16, 1)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, three walkway pieces
		Set<BoardCell> testList = board.getAdjList(0, 5);
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(testList.contains(board.getCellAt(0, 4)));
		assertTrue(testList.contains(board.getCellAt(0, 6)));
		assertEquals(3, testList.size());
		
		// Test on left edge of board, two walkway pieces
		testList = board.getAdjList(7, 0);
		assertTrue(testList.contains(board.getCellAt(8, 0)));
		assertTrue(testList.contains(board.getCellAt(7, 1)));
		assertEquals(2, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(9, 10);
		assertTrue(testList.contains(board.getCellAt(9, 11)));
		assertTrue(testList.contains(board.getCellAt(9, 9)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(11,16);
		assertTrue(testList.contains(board.getCellAt(11, 15)));
		assertTrue(testList.contains(board.getCellAt(11, 17)));
		assertTrue(testList.contains(board.getCellAt(12, 16)));
		assertTrue(testList.contains(board.getCellAt(10, 16)));
		assertEquals(4, testList.size());
		
		// Test on bottom edge of board, next to 1 walkway piece
		testList = board.getAdjList(24, 7);
		assertTrue(testList.contains(board.getCellAt(23, 7)));
		assertEquals(1, testList.size());
		
		// Test on right edge of board, next to 2 walkway pieces
		testList = board.getAdjList(17, 23);
		assertTrue(testList.contains(board.getCellAt(16, 23)));
		assertTrue(testList.contains(board.getCellAt(17, 22)));
		assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(5, 18);
		assertTrue(testList.contains(board.getCellAt(5, 17)));
		assertTrue(testList.contains(board.getCellAt(6, 18)));
		assertTrue(testList.contains(board.getCellAt(4, 18)));
		assertEquals(3, testList.size());
	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(24, 15, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(24, 16)));
		assertTrue(targets.contains(board.getCellAt(23, 15)));	
		
		board.calcTargets(4, 4, 1);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 5)));
		assertTrue(targets.contains(board.getCellAt(5, 4)));	
		assertTrue(targets.contains(board.getCellAt(3, 4)));			
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(24, 15, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(22, 15)));
		assertTrue(targets.contains(board.getCellAt(24, 17)));
		assertTrue(targets.contains(board.getCellAt(23, 16)));
		
		board.calcTargets(4, 4, 2);
		targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 6)));
		assertTrue(targets.contains(board.getCellAt(6, 4)));	
		assertTrue(targets.contains(board.getCellAt(2, 4)));
		assertTrue(targets.contains(board.getCellAt(3, 5)));	
		assertTrue(targets.contains(board.getCellAt(5, 5)));
	}
	
	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(24, 15, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(21, 16)));
		assertTrue(targets.contains(board.getCellAt(22, 17)));
		assertTrue(targets.contains(board.getCellAt(23, 16)));
		assertTrue(targets.contains(board.getCellAt(22, 15)));
		assertTrue(targets.contains(board.getCellAt(24, 17)));
		assertTrue(targets.contains(board.getCellAt(20, 15)));
		
		board.calcTargets(4, 4, 4);
		targets= board.getTargets();
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 5)));
		assertTrue(targets.contains(board.getCellAt(4, 6)));	
		assertTrue(targets.contains(board.getCellAt(6, 6)));
		assertTrue(targets.contains(board.getCellAt(6, 4)));	
		assertTrue(targets.contains(board.getCellAt(8, 4)));	
		assertTrue(targets.contains(board.getCellAt(2, 6)));
		assertTrue(targets.contains(board.getCellAt(7, 5)));	
		assertTrue(targets.contains(board.getCellAt(3, 5)));
		assertTrue(targets.contains(board.getCellAt(5, 5)));	
		assertTrue(targets.contains(board.getCellAt(8, 4)));	
		assertTrue(targets.contains(board.getCellAt(2, 4)));
		assertTrue(targets.contains(board.getCellAt(7, 3)));	
		assertTrue(targets.contains(board.getCellAt(0, 4)));	
		assertTrue(targets.contains(board.getCellAt(3, 7)));	
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(24, 15, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 17)));
		assertTrue(targets.contains(board.getCellAt(22, 17)));	
		assertTrue(targets.contains(board.getCellAt(22, 15)));	
		assertTrue(targets.contains(board.getCellAt(20, 15)));	
		assertTrue(targets.contains(board.getCellAt(21, 16)));	
		assertTrue(targets.contains(board.getCellAt(18, 15)));	
		assertTrue(targets.contains(board.getCellAt(24, 17)));	
		assertTrue(targets.contains(board.getCellAt(23, 16)));	
		assertTrue(targets.contains(board.getCellAt(19, 16)));	
	}	
	
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(18, 16, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(7, targets.size());
		// directly right (can't go left 2 steps)
		assertTrue(targets.contains(board.getCellAt(18, 18)));
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(16, 16)));
		assertTrue(targets.contains(board.getCellAt(20, 16)));
		// one up/down, one left/right
		assertTrue(targets.contains(board.getCellAt(19, 17)));
		assertTrue(targets.contains(board.getCellAt(19, 15)));
		assertTrue(targets.contains(board.getCellAt(17, 17)));
		assertTrue(targets.contains(board.getCellAt(17, 15)));
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(17, 9, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(10, targets.size());
		// directly up and down (includes room)
		assertTrue(targets.contains(board.getCellAt(14, 9)));
		assertTrue(targets.contains(board.getCellAt(18, 9)));
		// directly right  (includes room)
		assertTrue(targets.contains(board.getCellAt(16, 11)));
		//others
		assertTrue(targets.contains(board.getCellAt(15, 10)));
		assertTrue(targets.contains(board.getCellAt(17, 10)));
		assertTrue(targets.contains(board.getCellAt(16, 9)));
		assertTrue(targets.contains(board.getCellAt(15, 8)));
		assertTrue(targets.contains(board.getCellAt(18, 7)));
		assertTrue(targets.contains(board.getCellAt(16, 7)));
		assertTrue(targets.contains(board.getCellAt(17, 8)));	
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(3, 7, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(3, 6)));
		// Take two steps
		board.calcTargets(3, 7, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(3, 5)));
		assertTrue(targets.contains(board.getCellAt(2, 6)));
		assertTrue(targets.contains(board.getCellAt(4, 6)));
	}

}
