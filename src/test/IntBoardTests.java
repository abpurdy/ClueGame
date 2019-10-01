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
		
		board = new IntBoard();
		
	}
	
	
	//adjacency tests
	//TODO create bodies for all adjacency tests
	
	@Test
	/**Test adjacent cells for the top left cell.*/
	public void testAdjacencyTopLeft() {
		
		//TODO add functionality for these methods in BoardCell and IntBoard
		
		/*BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());*/
		
	}
	
	@Test
	/**Test adjacent cells for a cell on the right edge of the board..*/
	public void testAdjacencyRightEdge() {
		
	}
	
	@Test
	/**Test adjacent cells for a cell on the left edge of the board.*/
	public void testAdjacencyLeftEdge() {
		
	}
	
	@Test
	/**Test adjacent cells for the cell in the middle of the second column.*/
	public void testAdjacencySecondColMid() {
		
	}
	
	@Test
	/**Test adjacent cells for the cell in the middle of the second to last column..*/
	public void testAdjacencySecondLastColMid() {
		
	}
	
	
	//target tests
	//TODO add bodies for target tests
	
	@Test
	/**Test target cells for the cell (0,0) that are 1 move away.*/
	public void testTargets0_0_1() {
		
		//TODO add functionality for methods
		
		/*BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));*/
		
	}

}
