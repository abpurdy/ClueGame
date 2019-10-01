package experiment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private BoardCell[][] grid;
	private Map<BoardCell, Set<BoardCell> > adjMtx = new HashMap();
	
	private void calcAdjacencies(){
		
	}
	public Set<BoardCell> getAdjList(BoardCell cell){
		return adjMtx.get(cell);
	}
}
