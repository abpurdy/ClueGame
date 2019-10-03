package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {
	
	private IntBoard board;

	@Before
	/**Setup game board to perform tests.*/
	public void setupBoard(){
		
		board = new IntBoard(true);
		
	}
	
	/*@Test
	public void testNearby() {
		
		assertEquals()
		
	}*/
	
	
	//adjacency tests
	//TODO create bodies for all adjacency tests
	
	@Test
	/**Test adjacent cells for the top left cell.*/
	public void testAdjacencyTopLeft() {
		
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	/**Test adjacent cells for a cell on the right edge of the board..*/
	public void testAdjacencyRightEdge() {
		
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	/**Test adjacent cells for a cell on the left edge of the board.*/
	public void testAdjacencyLeftEdge() {
		
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	/**Test adjacent cells for the cell in the middle of the second column.*/
	public void testAdjacencySecondColMid() {
		
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
	}
	
	@Test
	/**Test adjacent cells for the cell in the middle of the second to last column..*/
	public void testAdjacencySecondLastColMid() {
		
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
	}
	
	
	//target tests
	//TODO add bodies for target tests
	
	@Test
	/**Test target cells for the cell (0,0) that are 1 move away.*/
	public void testTargets0_0_1() {
		
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		
	}
	
	@Test
	/**Test target cells for the cell (0,0) that are 2 moves away.*/
	public void testTargets0_0_2() {
		
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		
	}
	
	@Test
	/**Test target cells for the cell (1,1) that are 1 move away.*/
	public void testTargets1_1_1() {
		
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		
	}
	
	@Test
	/**Test target cells for the cell (1,1) that are 2 moves away.*/
	public void testTargets1_1_2() {
		
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		
	}
	
	@Test
	/**Test target cells for the cell (3,3) that are 1 move away.*/
	public void testTargets3_3_1() {
		
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		
	}

}
