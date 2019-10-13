package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.BoardCell.DoorDirection;

/**Class to test the room configuration that was loaded from the files.
 * @author Tanner Lorenz
 * @author Austin Purdy*/
public class FileInitTests {
	
	/**The test board.*/
	private Board board;

	/**Setup the board for testing.*/
	@Before
	public void setUp() throws Exception {
		
		board = Board.getInstance();
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt");
		board.initialize();
		
	}
	
	
	//tests
	
	/**Test that the legend was loaded correctly.*/
	@Test
	public void testRooms() {
		
		Map<Character, String> legend = board.getLegend();
		
		assertEquals(legend.size(), 11);
		
		assertEquals(legend.get('I'), "Library");
		assertEquals(legend.get('T'), "Bathroom");
		assertEquals(legend.get('V'), "Living Room");
		assertEquals(legend.get('X'), "Closet");
		assertEquals(legend.get('R'), "War Room");
		
	}
	
	/**Test that the board is the correct size.*/
	@Test
	public void testBoardDimensions() {
		
		assertEquals(board.getNumRows(), 25);
		assertEquals(board.getNumColumns(), 24);
		
	}
	
	/**Test cells with doors on them to make sure they are facing in the intended direction, and test one cell without a door to make sure it has no door direction.*/
	@Test
	public void testDoorDirections() {
		
		BoardCell doorCell = board.getCellAt(6, 2);
		assertTrue(doorCell.isDoorway());
		assertEquals(doorCell.getDoorDirection(), DoorDirection.DOWN);
		
		doorCell = board.getCellAt(11, 1);
		assertTrue(doorCell.isDoorway());
		assertEquals(doorCell.getDoorDirection(), DoorDirection.RIGHT);
		
		doorCell = board.getCellAt(17, 2);
		assertTrue(doorCell.isDoorway());
		assertEquals(doorCell.getDoorDirection(), DoorDirection.UP);
		
		doorCell = board.getCellAt(14, 5);
		assertTrue(doorCell.isDoorway());
		assertEquals(doorCell.getDoorDirection(), DoorDirection.LEFT);
		
		doorCell = board.getCellAt(8, 8);
		assertFalse(doorCell.isDoorway());
		assertEquals(doorCell.getDoorDirection(), DoorDirection.NONE);
		
	}
	
	/**Test that the number of doors present in the board matches the expected number.*/
	@Test
	public void testNumDoors() {
		
		int doors = 0; //number of doors
		
		//add up all door cells in board
		for(int x = 0; x < board.getNumRows(); x++)
			for(int y = 0; y < board.getNumColumns(); y++)
				if(board.getCellAt(x, y).isDoorway())
					doors++;
		
		assertEquals(doors, 17);
		
	}
	
	/**Test some cells to make sure they have the correct room types.*/
	@Test
	public void testRoomTypes() {
		
		assertEquals('I', board.getCellAt(0, 0).getInitial());
		assertEquals('B', board.getCellAt(0, 7).getInitial());
		assertEquals('X', board.getCellAt(10, 12).getInitial());
		assertEquals('K', board.getCellAt(24, 23).getInitial());
		assertEquals('V', board.getCellAt(24, 12).getInitial());
		
	}

}
