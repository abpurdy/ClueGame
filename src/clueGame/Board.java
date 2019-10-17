package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * A class that controls the Clue board.*/
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
			
			//load board specs from config files
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
	 * @throws FileNotFoundException If the room config file is not found. 
	 * @throws BadConfigFormatException If there is a room entry in the config file with the wrong format.*/
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		
		//open input file and scanner
		FileReader roomFileIn = new FileReader(roomConfigFile);
		Scanner reader = new Scanner(roomFileIn);
		
		//read legend file and store info in legend map
		while(reader.hasNext()) {
			
			String line = reader.nextLine(); //get next line
			String[] lineData = line.split(", "); //split line by comma
			
			//throw exception if line is not configured correctly
			if(lineData[0].length() != 1 || (!lineData[2].equals("Card") && !lineData[2].equals("Other")))
				throw new BadConfigFormatException("An entry in the room config file does not have the correct format.");
			
			legend.put(lineData[0].charAt(0), lineData[1]); //insert map pair
			
		}
		
		reader.close();
		
	}
	
	/**Load the board configuration from the board config file.
	 * @throws FileNotFoundException If the board config file is not found.
	 * @throws BadConfigFormatException If the board does not contain the same amount of columns in every row, or if a room appears on the board that is not in the legend.*/
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		
		//open input and file scanner
		FileReader boardFileIn = new FileReader(boardConfigFile);
		Scanner reader = new Scanner(boardFileIn);
		
		int rowNum = 0; //number of rows in board
		
		//read each line from file
		while(reader.hasNext()) {
			
			//get each cell in row
			String row = reader.nextLine();
			String[] rowData = row.split(",");
			
			//create a new cell for each spot in array
			for(int x = 0; x < rowData.length; x++) {
				
				//ensure that the given room type matches one in the list of rooms
				boolean validRoom = false;
				for(char room : legend.keySet())
					if(rowData[x].charAt(0) == room)
						validRoom = true;
						
				//throw exception if illegal room is found
				if(validRoom == false)
					throw new BadConfigFormatException("An illegal room type was found in the board config file.");
				
				//create new board cell and add it to the board
				BoardCell newCell = new BoardCell(rowNum, x, rowData[x]);
				board[rowNum][x] = newCell;
			}
			
			rowNum++;
			
			//throw an exception if the current number of columns doesn't match the number of columns in the last row
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
