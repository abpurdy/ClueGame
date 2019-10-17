package clueGame;

/**Class for a cell that represents a space on the game board.
 * @author Tanner Lorenz\
 * @author Austin Purdy*/
public class BoardCell {
	
	/**Enum that represents each direction a door can be in.*/
	public enum DoorDirection{
		UP, DOWN, LEFT, RIGHT, NONE;
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
		this.row = row;
		this.column = column;
		this.roomType = roomType;
		
		if(roomType.length()>1) {
			switch(roomType.charAt(1)) {
				case 'D':
					this.doorDirection = DoorDirection.DOWN;
					break;
				case 'U':
					this.doorDirection = DoorDirection.UP;
					break;
				case 'L':
					this.doorDirection = DoorDirection.LEFT;
					break;
				case 'R':
					this.doorDirection = DoorDirection.RIGHT;
					break;
				default:
					this.doorDirection = DoorDirection.NONE;
			}
		}
		else {
			this.doorDirection = DoorDirection.NONE;
		}
	}
	
	
	//class methods
	
	public boolean isWalkway() {
		if(roomType.charAt(0) == 'W') {
			return true;
		}
		return false;
	}
	
	public boolean isRoom() {
		if(roomType.charAt(0) == 'W') {
			return false;
		}
		return true;
	}
	
	public boolean isDoorway() {
		if(doorDirection.equals(DoorDirection.NONE)) {
			return false;
		}
		else {
			return true;
		}
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
