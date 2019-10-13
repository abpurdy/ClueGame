package experiment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**Class that holds functionality for the game board.*/
public class IntBoard {
	
	//class variables
	
	/**The amount of rows in the board.*/
	public final static int ROW_NUM = 25;
	/**The amount of columns in the board.*/
	public final static int COL_NUM = 24;
	/**The current list of cells that the player can move to.*/
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	/**The list of cells in the board.*/
	private BoardCell[][] grid;
	/**A map that stores a list of adjacent cells for each cell in the board.*/
	private Map<BoardCell, Set<BoardCell> > adjMtx = new HashMap<BoardCell, Set<BoardCell> >();
	
	
	//constructors
	
	/**Create a new game board.*/
	public IntBoard() {
		
		grid = new BoardCell[ROW_NUM][COL_NUM]; //create grid
		
		//initialize the grid with cells by reading in the game board file
		try {
			fillGrid();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find the file specified.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		calcAdjacencies(); //create adjacency matrix
		
	}
	
	/**Create a new 4x4 board for testing purposes.*/
	public IntBoard(boolean testBoard) {
		
		grid = new BoardCell[4][4]; //create grid
		
		//initialize cells in grid
		for(int x = 0; x < grid.length; x++)
			for(int y = 0; y < grid[x].length; y++)
				grid[x][y] = new BoardCell(x, y, "R");
		
		calcAdjacencies(); //create adjacency matrix
		
	}
	
	
	//class methods
	
	/**Calculates adjacent cells for each cell in the grid and stores them in the adjacency map.*/
	private void calcAdjacencies(){
		
	}
	
	/**Calculate the cells that the player can move to given a move length and a starting cell.
	 * @param startCell The cell to start from.
	 * @param pathLength The amount of spaces the player will move.*/
	public void calcTargets(BoardCell startCell, int pathLength){
		
		Set<BoardCell> visited = new HashSet<BoardCell>(); //list of already visited cells
		
		Set<BoardCell> options = new HashSet<BoardCell>(); //the current available list of cells to move to
		
		targets = calcAllTargets(startCell, pathLength, visited, options); //recursively calculate targets using each cell in range
		
	}
	
	/**Recursively calculate cells that the player can move to.
	 * @param startCell The cell to check around.
	 * @param pathLength The remaining distance the player can move.
	 * @param visited The list of cells that have already been visited.
	 * @param options The current list of targets the player can move to.
	 * @return A set of cells that the player can move to in the given distance from the given cell.*/
	private Set<BoardCell> calcAllTargets(BoardCell startCell, int pathLength, Set<BoardCell> visited, Set<BoardCell> options){
		
		return options;
		
	}
	
	/**Initialize the grid of cells.
	 * @throws FileNotFoundException If the game board file does not exist.*/
	private void fillGrid() throws FileNotFoundException, IOException {
		
	}
	
	
	//getters and setters
	
	public Set<BoardCell> getAdjList(BoardCell cell){
		return adjMtx.get(cell);
	}
	public Set<BoardCell> getTargets(){
		return targets;
	}
	public BoardCell getCell(int x, int y) {
		return grid[x][y];
	}
	
}
