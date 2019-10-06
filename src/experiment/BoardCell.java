package experiment;

/**Class for a cell that represents a space on the game board.*/
public class BoardCell {
	
	//class variables
	
	/**The row of this cell.*/
	int row;
	/**The column of this cell*/
	int column;
	/**The room this cell is in.*/
	String roomType;
	
	
	//constructor
	
	/**Create a new board cell with the given room type at the given row and column.*/
	public BoardCell(int row, int column, String roomType) {
		super();
		this.row = row;
		this.column = column;
		this.roomType = roomType;
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
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

}
