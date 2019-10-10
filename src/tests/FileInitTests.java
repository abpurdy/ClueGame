package tests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class FileInitTests {
	
	private Board board;

	@Before
	public void setUp() throws Exception {
		
		board = Board.getInstance();
		
	}

}
