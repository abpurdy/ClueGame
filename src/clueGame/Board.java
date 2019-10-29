package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import clueGame.BoardCell;
import clueGame.BoardCell.DoorDirection;

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
	/**The name of the player configuration file.*/
	private String playerConfigFile;
	/**The name of the card configuration file.*/
	private String cardConfigFile;
	/**The singleton instance of this Board class.*/
	private static Board instance = new Board();
	/**A list of the players in the game.*/
	private ArrayList<Player> players;
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
		playerConfigFile = "";
		cardConfigFile = "";
		players = new ArrayList<Player>();
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
			loadPlayerConfig();
			loadCardConfig();

		}
		catch(FileNotFoundException e) {
			System.err.println("The specified config files were not found.");
		}
		catch(BadConfigFormatException e) {
			System.err.println("One or more of the config files had an error.");
		}
		
		calcAdjacencies();

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
			if(lineData[0].length() != 1 || (!lineData[2].equals("Card") && !lineData[2].equals("Other"))) {
				reader.close();
				throw new BadConfigFormatException("An entry in the room config file does not have the correct format.");
			}

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
			if(numColumns != 0 && numColumns != rowData.length) {
				reader.close();
				throw new BadConfigFormatException("Each row in the board must have the same amount of columns.");
			}

			numColumns = rowData.length;

		}

		numRows = rowNum;

		reader.close();
	}
	
	/**Load the players from the player config file.*/
	public void loadPlayerConfig() {}
	
	/**Load the cards from the card config file.*/
	public void loadCardConfig() {}

	/**Calculate the list of adjacent cells for each cell in the board.*/
	private void calcAdjacencies(){
		
		//loop through each cell
		for(int x = 0; x < numRows; x++) {
			for(int y = 0; y < numColumns; y++) {
				
				Set<BoardCell> adjacent = new HashSet<BoardCell>(); //list of adjacent cells to current cell
				
				//if current cell is a walkway, add all nearby walkways or any doors facing the correct direction to the adjacency list
				if(board[x][y].isWalkway()) {
					
					if(!(y-1 < 0) && (board[x][y-1].isWalkway() || (board[x][y-1].isDoorway() && board[x][y-1].getDoorDirection() == BoardCell.DoorDirection.RIGHT)))
						adjacent.add(board[x][y-1]);
					if(!(x-1 < 0) && (board[x-1][y].isWalkway() || (board[x-1][y].isDoorway() && board[x-1][y].getDoorDirection() == BoardCell.DoorDirection.DOWN)))
						adjacent.add(board[x-1][y]);
					if(!(y+1 > numColumns-1) && (board[x][y+1].isWalkway() || (board[x][y+1].isDoorway() && board[x][y+1].getDoorDirection() == BoardCell.DoorDirection.LEFT)))
						adjacent.add(board[x][y+1]);
					if(!(x+1 > numRows-1) && (board[x+1][y].isWalkway() || (board[x+1][y].isDoorway() && board[x+1][y].getDoorDirection() == BoardCell.DoorDirection.UP)))
						adjacent.add(board[x+1][y]);
					
				}
				//if current cell is a doorway, only add the walkway cell next to it to the adjacency list
				else if(board[x][y].isDoorway()) {
					
					if(!(y-1 < 0) && board[x][y-1].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.LEFT)
						adjacent.add(board[x][y-1]);
					if(!(x-1 < 0) && board[x-1][y].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.UP)
						adjacent.add(board[x-1][y]);
					if(!(y+1 > numColumns-1) && board[x][y+1].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.RIGHT)
						adjacent.add(board[x][y+1]);
					if(!(x+1 > numRows-1) && board[x+1][y].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.DOWN)
						adjacent.add(board[x+1][y]);
				}
				
				adjMatrix.put(board[x][y], adjacent); //add adjacency list to matrix
				
			}
		}
		
	}

	/**Calculate the cells that the player can move to given a move length and a starting cell.
	 * @param x The x coordinate of the starting cell.
	 * @param y The y coordinate of the starting cell.
	 * @param pathLength The amount of spaces the player will move.*/
	public void calcTargets(int x, int y, int pathLength){

		Set<BoardCell> visited = new HashSet<BoardCell>(); //list of already visited cells
		
		targets.clear();
		visited.add(board[x][y]); //add start cell to visited list

		calcAllTargets(board[x][y], pathLength, visited); //recursively calculate targets using each cell in range

	}

	/**Recursively calculate cells that the player can move to.
	 * @param startCell The cell to check around.
	 * @param pathLength The remaining distance the player can move.
	 * @param visited The list of cells that have already been visited. */
	private void calcAllTargets(BoardCell startCell, int pathLength, Set<BoardCell> visited){
		
		//check each adjacent cell
		for(BoardCell adjacent : adjMatrix.get(startCell)) {
			
			//if cell is not visited
			if(!visited.contains(adjacent)) {
				
				visited.add(adjacent); //add cell to visited list
				
				//add cell if it is a doorway
				if(adjacent.isDoorway())
					targets.add(adjacent);
				
				//add cell to targets if no moves remain, otherwise go to that cell and check its adjacent cells
				if(pathLength == 1)
					targets.add(adjacent);
				else
					calcAllTargets(adjacent, pathLength-1, visited);
				
				visited.remove(adjacent); //remove cell from visited list
				
			}
			
		}
		
	}
	
	/**Select an answer.*/
	public void selectAnswer() {}
	
	/**Handle a given suggestion.*/
	public void handleSuggestion() {}
	
	/**Check if an accusation matches the solution.*/
	public boolean checkAccusation(Solution accusation) {
		return false;
	}


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
	
	public ArrayList<Player> getPlayers(){
		return players;
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
	
	public void setConfigFiles(String boardConfigFile, String roomConfigFile, String playerConfigFile, String cardConfigFile) {
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		this.playerConfigFile = playerConfigFile;
		this.cardConfigFile = cardConfigFile;
	}

	public BoardCell getCellAt(int x, int y) {
		return board[x][y];
	}


	public Set<BoardCell> getAdjList(int x, int y) {
		// TODO Auto-generated method stub
		return adjMatrix.get(board[x][y]);
	}

}
