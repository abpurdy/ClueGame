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
		
		/*for(int x = 0; x < numRows; x++) {
			for(int y = 0; y < numColumns; y++) {
				System.out.print(board[x][y].getRoomType() + ",");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println(numRows);
		
		for(BoardCell[] row : board) {
			for(int x = 0; x < numColumns; x++)
				System.out.print(row[x].getRoomType() + ",");
			System.out.println();
		}*/
		
		calcAdjacencies();
		
		//System.out.println(board[4][3]);

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

	private void calcAdjacencies(){
		
		for(int x = 0; x < numRows; x++) {
			for(int y = 0; y < numColumns; y++) {
				
				if(x == 4 && y == 4) { // && x-1 >= 0 && y-1 >= 0 && y+1 < numRows && x-1 < numColumns
					System.out.println("Above: " + board[x][y-1] + " Right: " + board[x+1][y] + " Below : " + board[x][y+1] + " Left: " + board[x-1][y]);
				}
				
				Set<BoardCell> adjacent = new HashSet<BoardCell>();
				
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
				else if(board[x][y].isDoorway()) {
					
					//TODO
					
					if(!(y-1 < 0) && board[x][y-1].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.LEFT)
						adjacent.add(board[x][y-1]);
					if(!(x-1 < 0) && board[x-1][y].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.UP)
						adjacent.add(board[x-1][y]);
					if(!(y+1 > numColumns-1) && board[x][y+1].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.RIGHT)
						adjacent.add(board[x][y+1]);
					if(!(x+1 > numRows-1) && board[x+1][y].isWalkway() && board[x][y].getDoorDirection() == DoorDirection.DOWN)
						adjacent.add(board[x+1][y]);
					
				}
				
				adjMatrix.put(board[x][y], adjacent);
				
			}
			
		}
	}

	/**Calculate the cells that the player can move to given a move length and a starting cell.
	 * @param x The x coordinate of the starting cell.
	 * @param y The y coordinate of the starting cell.
	 * @param pathLength The amount of spaces the player will move.*/
	public void calcTargets(int x, int y, int pathLength){

		Set<BoardCell> visited = new HashSet<BoardCell>(); //list of already visited cells

		Set<BoardCell> options = new HashSet<BoardCell>(); //the current available list of cells to move to

		targets = calcAllTargets(board[x][y], pathLength, new HashSet<BoardCell>(), options); //recursively calculate targets using each cell in range*/

	}

	/**Recursively calculate cells that the player can move to.
	 * @param startCell The cell to check around.
	 * @param pathLength The remaining distance the player can move.
	 * @param visited The list of cells that have already been visited.
	 * @param options The current list of targets the player can move to.
	 * @return A set of cells that the player can move to in the given distance from the given cell.*/
	private Set<BoardCell> calcAllTargets(BoardCell startCell, int pathLength, Set<BoardCell> visited, Set<BoardCell> options){

		pathLength--;
		visited.add(startCell);

		if(pathLength == 0) {
			for (BoardCell adjacent:adjMatrix.get(startCell)) {
				if(!visited.contains(adjacent)) {
					options.add(adjacent);
				}
			}
		}
		else {
			for (BoardCell adjacent:adjMatrix.get(startCell)) {
				if(adjacent.isDoorway()) {
					options.add(adjacent);
				}
				else if(!visited.contains(adjacent)) {
					Set<BoardCell> Value = new HashSet<BoardCell>();
					Value.addAll(visited);
					options.addAll(calcAllTargets(adjacent, pathLength, Value, options));
				}
			}
		}
		return options;
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


	public Set<BoardCell> getAdjList(int x, int y) {
		
		System.out.println("Adjacent to " + board[x][y] + ":");
		
		for(BoardCell cell : adjMatrix.get(board[x][y]))
			System.out.print(cell + " ");
		System.out.println();
		
		// TODO Auto-generated method stub
		return adjMatrix.get(board[x][y]);
	}

}
