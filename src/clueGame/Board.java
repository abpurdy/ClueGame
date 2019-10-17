package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

	}
	
	
	//class methods
	
	/**Initialize the game board.*/
	public void initialize(){
		
		try {
			loadRoomConfig();
			loadBoardConfig();
		}
		catch(FileNotFoundException e) {
			System.err.println("The specified config files were not found.");
		}
		catch(BadConfigFormatException e) {
			System.err.println("One or more of the config files had an error.");
		}
		
	}
	
	/**Load the room configuration from the room config file.
	 * @throws FileNotFoundException If the room config file is not found 
	 * @throws BadConfigFormatException */
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		
		//open input file and scanner
		FileReader roomFileIn = new FileReader(roomConfigFile);
		Scanner reader = new Scanner(roomFileIn);
		
		//read legend file and store info in legend map
		while(reader.hasNext()) {
			
			String line = reader.nextLine(); //get next line
			String[] lineData = line.split(", "); //split line by comma
			
			if(lineData[0].length() != 1 || lineData[2] != "Card" || lineData[2] != "Other")
				throw new BadConfigFormatException("An entry in the room config file does not have the correct format.");
			
			legend.put(lineData[0].charAt(0), lineData[1]); //insert map pair
			
		}
		reader.close();
	}
	
	/**Load the board configuration from the board config file.
	 * @throws FileNotFoundException 
	 * @throws BadConfigFormatException */
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		
		FileReader boardFileIn = new FileReader(boardConfigFile);
		Scanner reader = new Scanner(boardFileIn);
		
		int rowNum = 0;
		
		while(reader.hasNext()) {
			
			String row = reader.nextLine();
			String[] rowData = row.split(",");
			
			for(int x = 0; x < rowData.length; x++) {
				
				//ensure that the given room type matches one in the list of rooms
				boolean validRoom = false;
				for(char room : legend.keySet())
					if(rowData[x].charAt(0) == room)
						validRoom = true;
						
				if(validRoom == false)
					throw new BadConfigFormatException("An illegal room type was found in the board config file.");
				
				BoardCell newCell = new BoardCell(rowNum, x, rowData[x]);
				board[rowNum][x] = newCell;
			}
			
			rowNum++;
			
			if(numColumns != 0 && numColumns != rowData.length)
				throw new BadConfigFormatException("Each row in the board must have the same amount of columns.");
			
			numColumns = rowData.length;
			
		}
		
		numRows = rowNum;
		
		reader.close();
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
