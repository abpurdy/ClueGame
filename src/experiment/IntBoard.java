package experiment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private final int ROW_NUM = 25;
	private final int COL_NUM = 24;
	private BoardCell[][] grid = new BoardCell[ROW_NUM][COL_NUM];
	private Map<BoardCell, Set<BoardCell> > adjMtx = new HashMap();
	
	private void calcAdjacencies() throws IOException{
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
