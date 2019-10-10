package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;

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
		
		//load config files
		try {
			loadRoomConfig();
			loadBoardConfig();	
		}
		catch(FileNotFoundException e) {
			System.err.println("The specified configuration files were not found.");
			e.printStackTrace();
		}

	}
	
	
	//class methods
	
	/**Initialize the game board.*/
	public void initialize() {}
	
	/**Load the room configuration from the room config file.
	 * @throws FileNotFoundException If the room config file is not found */
	public void loadRoomConfig() throws FileNotFoundException {
		
		//open input file and scanner
		FileReader roomFileIn = new FileReader(roomConfigFile);
		Scanner reader = new Scanner(roomFileIn);
		
		//read legend file and store info in legend map
		while(reader.hasNext()) {
			
			String line = reader.nextLine(); //get next line
			String[] lineData = line.split(","); //split line by comma
			legend.put(lineData[0].charAt(0), lineData[1]); //insert map pair
			
		}
		
	}
	
	/**Load the board configuration from the board config file.*/
	public void loadBoardConfig() {
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(boardConfigFile));
			String row = csvReader.readLine();
			while(row != null) {
				row = csvReader.readLine();
			    String[] data = row.split(",");
			    numColumns = data.length;
			    for(int j = 0; j < data.length; j++) {
			    	board[numRows][j] = new BoardCell(numRows,j,data[j]);
			    }
			    row = csvReader.readLine();
			    numRows+=1;
			}
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			
		}
	}
	
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
