package experiment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	//variables
	public final static int ROW_NUM = 25;
	public final static int COL_NUM = 24;
	//instance variables
	private Set<BoardCell> targets = new HashSet();
	private BoardCell[][] grid = new BoardCell[ROW_NUM][COL_NUM];
	private Map<BoardCell, Set<BoardCell> > adjMtx = new HashMap();
	
	public IntBoard() {
		
		try {
			fillGrid();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find the file specified.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		calcAdjacencies();
		
		Set<BoardCell> adjList = adjMtx.get(grid[0][0]);
		
	}
	
	//initializes the adjacency matrix 
	private void calcAdjacencies(){
		
		for(int i = 0; i < ROW_NUM; i++) {
		    for(int j = 0; j < COL_NUM; j++) {
		    	Set<BoardCell> list = new HashSet<BoardCell>();
		    	//adds cell to list if you are allowed to move to it
		    	if (i-1 >= 0 && 
		    			(grid[i-1][j].getRoomType().equals(grid[i][j].getRoomType()) || 
		    			(grid[i-1][j].getRoomType().length() == 2 && grid[i-1][j].getRoomType().charAt(1) == 'R') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'L'))) {
		    		list.add(grid[i-1][j]);
		    	}
		    	if (i+1 < ROW_NUM && 
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
		    	if (j+1 < COL_NUM && 
		    			(grid[i][j+1].getRoomType().equals(grid[i][j].getRoomType()) || 
		    			(grid[i][j+1].getRoomType().length() == 2 && grid[i][j+1].getRoomType().charAt(1) == 'D') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'U'))) {
		    		list.add(grid[i][j+1]);
		    	}
		    	
		    	//adds list to adjacency matrix
		    	adjMtx.put(grid[i][j], list);
		    }
		}
	}
	
	//calculates targets with recursion 
	public void calcTargets(BoardCell startCell, int pathLength){
		Set<BoardCell> visited = new HashSet();
		visited.add(startCell);
		Set<BoardCell> options = new HashSet();
		
		targets = calcAllTargets(startCell, pathLength, visited, options);
	}
	private Set<BoardCell> calcAllTargets(BoardCell startCell, int pathLength, Set<BoardCell> visited, Set<BoardCell> options){
		pathLength -= 1;
		visited.add(startCell);
		
		if(pathLength == 0) {
			for (BoardCell adjacent:adjMtx.get(startCell)) {
				if(!visited.contains(adjacent)) {
					targets.add(adjacent);
				}
			}
		}
		else {
			for (BoardCell adjacent:adjMtx.get(startCell)) {
				if(adjacent.getRoomType().charAt(0) != startCell.getRoomType().charAt(0)) {
					options.add(adjacent);
				}
				else if(!visited.contains(adjacent)) {
					options.addAll(calcAllTargets(adjacent, pathLength, visited, options));
				}
			}
		}
		return options;
	}
	
	//initializes grid
	private void fillGrid() throws FileNotFoundException, IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader("Clue Board.csv"));
		
		for(int i = 0; i < ROW_NUM; i++) {
			String row = csvReader.readLine();
		    String[] data = row.split(",");
		    for(int j = 0; j < data.length; j++) {
		    	grid[i][j] = new BoardCell(i,j,data[j]);
		    }
		}
	}
	//Getters and setters
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
