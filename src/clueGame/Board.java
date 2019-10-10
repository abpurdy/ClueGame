package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
	
	
	//class variables
	
	/**The maximum board width and height.*/
	public static int MAX_BOARD_SIZE = 50;
	/**Number of rows in the board.*/
	private int numRows;
	/**Number of columns in the board.*/
	private int numColumns;
	/**The name of the board configuration file.*/
	private String boardConfigFile;
	/**The name of the room configuration file.*/
	private String roomConfigFile;
	/**The singleton instance of this Board class.*/
	private static Board instance = new Board();
	/**The room legend.*/
	private Map<Character, String> legend;
	/**The list of adjacent cells for each cell on the board.*/
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	/**The current spaces that the player can move to.*/
	private Set<BoardCell> targets;
	/**The game board.*/
	BoardCell[][] board;

	
	//constructor
	
	private Board() {
		
		//initialize class variables
		
		numRows = 0;
		numColumns = 0;
		boardConfigFile = "";
		roomConfigFile = "";
		legend = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];

	}
	
	
	//class methods
	
	/**Initialize the game board.*/
	public void initialize() {}
	
	/**Load the room configuration from the room config file.*/
	public void loadRoomConfig() {}
	
	/**Load the board configuration from the board config file.*/
	public void loadBoardConfig() {}
	
	/**Load the adjacency matrix for each cell in the board.*/
	public void calcAdjacencies() {}
	
	/**Calculate the current targets the player can move to.
	 * @param cell The cell the player is on.
	 * @param pathLength The amount of spaces the player can move.*/
	public void calcTargets(BoardCell cell, int pathLength) {}


	//getters and setters
	
	public int getNumRows() {
		return numRows;
	}


	public int getNumColumns() {
		return numColumns;
	}


	public static Board getInstance() {
		return instance;
	}


	public Map<BoardCell, Set<BoardCell>> getAdjMatrix() {
		return adjMatrix;
	}


	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public Map<Character, String> getLegend() {
		return legend;
	}


	public void setConfigFiles(String boardConfigFile, String roomConfigFile) {
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}
	
	public BoardCell getCellAt(int x, int y) {
		return board[x][y];
	}

}
