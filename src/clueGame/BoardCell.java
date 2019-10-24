package clueGame;

/**Class for a cell that represents a space on the game board.
 * @author Tanner Lorenz\
 * @author Austin Purdy*/
public class BoardCell {
	
	/**Enum that represents each direction a door can be in.*/
	public enum DoorDirection{
		
		UP("U"), DOWN("D"), LEFT("L"), RIGHT("R"), NONE("");
		
		/**The character that represents the direction of this door.*/
		public String direction;
		
		/**Create a new DoorDirection.
		 * @param direction The character representing this DoorDirection.*/
		private DoorDirection(String direction) {
			this.direction = direction;
		}
		
	}
	
	//class variables
	
	/**The row of this cell.*/
	private int row;
	/**The column of this cell*/
	private int column;
	/**The room this cell is in.*/
	private String roomType;
	/**The direction the door on this cell is facing, or NONE if there is no door.*/
	private DoorDirection doorDirection;
	
	
	//constructor
	
	/**Create a new board cell with the given room type at the given row and column.*/
	public BoardCell(int row, int column, String roomType) {
		
		//initialize class variables
		
		this.row = row;
		this.column = column;
		this.roomType = roomType;
		
		//determine door direction
		
		if(roomType.length() == 1)
			doorDirection = DoorDirection.NONE;
		else {
			
			//check each door direction and find the one that matches the character given by roomType
			for(DoorDirection d : DoorDirection.values()) {
				if(d.direction.equals(roomType.substring(1))) {
					doorDirection = d;
					break;
				}
			}
			
			if(doorDirection == null)
				doorDirection = DoorDirection.NONE;
			
		}
	}
	
	
	//class methods
	
	/**Returns true if this cell is a walkway.*/
	public boolean isWalkway() {
		return roomType.charAt(0) == 'W';
	}
	
	/**Returns true if this cell is in a room.*/
	public boolean isRoom() {
		return roomType.charAt(0) != 'W';
	}
	
	/**Return true if this cell is a doorway.*/
	public boolean isDoorway() {
		return !doorDirection.equals(DoorDirection.NONE);
	}
	
	
	/**Return the string representation of this board cell.*/
	public String toString() {
		return roomType + " (" + row + "," + column + ")";
	}
	
	//getters and setters
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getRoomType() {
		return roomType;
	}
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getInitial() {
		return roomType.charAt(0);
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

}
