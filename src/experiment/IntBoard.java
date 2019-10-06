package experiment;

import java.io.BufferedReader;
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
	private Set<BoardCell> targets = new HashSet();
	/**The list of cells in the board.*/
	private BoardCell[][] grid;
	/**A map that stores a list of adjacent cells for each cell in the board.*/
	private Map<BoardCell, Set<BoardCell> > adjMtx = new HashMap();
	
	
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
		
		//check each cell in the board
		for(int i = 0; i < grid.length; i++) {
		    for(int j = 0; j < grid[i].length; j++) {
		    	
		    	Set<BoardCell> list = new HashSet<BoardCell>(); //list of adjacent cells

		    	//check each nearby cell to see if it can be moved to from the current cell
		    	if (i-1 >= 0 && 
		    			(grid[i-1][j].getRoomType().equals(grid[i][j].getRoomType()) || 
		    			(grid[i-1][j].getRoomType().length() == 2 && grid[i-1][j].getRoomType().charAt(1) == 'R') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'L'))) {
		    		list.add(grid[i-1][j]);
		    	}
		    	if (i+1 < grid.length && 
		    			(grid[i+1][j].getRoomType().equals(grid[i][j].getRoomType()) || 
		    			(grid[i+1][j].getRoomType().length() == 2 && grid[i+1][j].getRoomType().charAt(1) == 'L') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'R'))) {
		    		list.add(grid[i+1][j]);
		    	}
		    	if (j-1 >= 0 && 
		    			(grid[i][j-1].getRoomType().equals(grid[i][j].getRoomType()) || 
		    			(grid[i][j-1].getRoomType().length() == 2 && grid[i][j-1].getRoomType().charAt(1) == 'U') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'D'))) {
		    		list.add(grid[i][j-1]);
		    	}
		    	if (j+1 < grid[i].length && 
		    			(grid[i][j+1].getRoomType().equals(grid[i][j].getRoomType()) || 
		    			(grid[i][j+1].getRoomType().length() == 2 && grid[i][j+1].getRoomType().charAt(1) == 'D') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'U'))) {
		    		list.add(grid[i][j+1]);
		    	}
		    	
		    	adjMtx.put(grid[i][j], list); //add list to adjacency map
		    	
		    }
		}
	}
	
	/**Calculate the cells that the player can move to given a move length and a starting cell.
	 * @param startCell The cell to start from.
	 * @param pathLength The amount of spaces the player will move.*/
	public void calcTargets(BoardCell startCell, int pathLength){
		
		Set<BoardCell> visited = new HashSet(); //list of already visited cells
		visited.add(startCell);
		
		Set<BoardCell> options = new HashSet(); //the current available list of cells to move to
		
		targets = calcAllTargets(startCell, pathLength, visited, options); //recursively calculate targets using each cell in range
		
	}
	
	/**Recursively calculate cells that the player can move to.
	 * @param startCell The cell to check around.
	 * @param pathLength The remaining distance the player can move.
	 * @param visited The list of cells that have already been visited.
	 * @param options The current list of targets the player can move to.
	 * @return A set of cells that the player can move to in the given distance from the given cell.*/
	private Set<BoardCell> calcAllTargets(BoardCell startCell, int pathLength, Set<BoardCell> visited, Set<BoardCell> options){
		
		pathLength -= 1;
		visited.add(startCell); //mark current cell as visited
		
		//if player has no remaining distance to move
		if(pathLength == 0) {
			
			//add all adjacent cells to this cell that haven't already been visited to the target list
			for (BoardCell adjacent:adjMtx.get(startCell)) {
				if(!visited.contains(adjacent)) {
					options.add(adjacent);
				}
			}
			
		}
		else {
			
			//check each adjacent cell to current cell
			for (BoardCell adjacent:adjMtx.get(startCell)) {
				
				//add all relevant nearby cells to targets list
				if(adjacent.getRoomType().charAt(0) != startCell.getRoomType().charAt(0)) {
					options.add(adjacent);
				}
				else if(!visited.contains(adjacent)) {
					options.addAll(calcAllTargets(adjacent, pathLength, visited, options)); //check each adjacent cell and get their possible move targets
				}
				
			}
			
		}
		
		return options;
		
	}
	
	/**Initialize the grid of cells.
	 * @throws FileNotFoundException If the game board file does not exist.*/
	private void fillGrid() throws FileNotFoundException, IOException {
		
		BufferedReader csvReader = new BufferedReader(new FileReader("Clue Board.csv")); //open file reader
		
		//load cell data and store new cell in grid
		for(int i = 0; i < ROW_NUM; i++) {
			String row = csvReader.readLine();
		    String[] data = row.split(",");
		    for(int j = 0; j < data.length; j++) {
		    	grid[i][j] = new BoardCell(i,j,data[j]);
		    }
		}
		
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
