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
		this.doorDirection = DoorDirection.NONE;
	}
	
	
	//class methods
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
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
