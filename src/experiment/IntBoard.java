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
	private final int ROW_NUM = 25;
	private final int COL_NUM = 24;
	private Set<BoardCell> targets = new HashSet();
	private BoardCell[][] grid = new BoardCell[ROW_NUM][COL_NUM];
	private Map<BoardCell, Set<BoardCell> > adjMtx = new HashMap();
	
	private void calcAdjacencies() throws IOException{
		FillGrid();
		
		for(int i = 0; i < ROW_NUM; i++) {
		    for(int j = 0; j < COL_NUM; j++) {
		    	Set<BoardCell> list = new HashSet<BoardCell>();
		    	
		    	if (i-1 >= 0 && 
		    			(grid[i-1][j].getRoomType() == grid[i-1][j].getRoomType() || 
		    			(grid[i-1][j].getRoomType().length() == 2 && grid[i-1][j].getRoomType().charAt(1) == 'R') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'L'))) {
		    		list.add(new BoardCell(i-1,j,grid[i-1][j].getRoomType()));
		    	}
		    	if (i+1 < ROW_NUM && 
		    			(grid[i+1][j].getRoomType() == grid[i+1][j].getRoomType() || 
		    			(grid[i+1][j].getRoomType().length() == 2 && grid[i+1][j].getRoomType().charAt(1) == 'L') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'R'))) {
		    		list.add(new BoardCell(i+1,j,grid[i+1][j].getRoomType()));
		    	}
		    	if (i-1 >= 0 && 
		    			(grid[i][j-1].getRoomType() == grid[i][j-1].getRoomType() || 
		    			(grid[i][j-1].getRoomType().length() == 2 && grid[i][j-1].getRoomType().charAt(1) == 'U') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'D'))) {
		    		list.add(new BoardCell(i,j-1,grid[i][j-1].getRoomType()));
		    	}
		    	if (i-1 >= 0 && 
		    			(grid[i][j+1].getRoomType() == grid[i][j+1].getRoomType() || 
		    			(grid[i][j+1].getRoomType().length() == 2 && grid[i][j+1].getRoomType().charAt(1) == 'D') ||
		    			(grid[i][j].getRoomType().length() == 2 && grid[i][j].getRoomType().charAt(1) == 'U'))) {
		    		list.add(new BoardCell(i,j+1,grid[i][j+1].getRoomType()));
		    	}
		    	
		    	adjMtx.put(new BoardCell(i,j,grid[i][j].getRoomType()), list);
		    }
		}
	}
	public void calcTargets(BoardCell startCell, int pathLength){
		
	}
	private void FillGrid() throws FileNotFoundException, IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader("Clue Board.csv"));
		
		for(int i = 0; i < ROW_NUM; i++) {
			String row = csvReader.readLine();
		    String[] data = row.split(",");
		    for(int j = 0; j < data.length; j++) {
		    	grid[i][j] = new BoardCell(i,j,data[j]);
		    }
		}
	}
	public Set<BoardCell> getAdjList(BoardCell cell){
		return adjMtx.get(cell);
	}
}
