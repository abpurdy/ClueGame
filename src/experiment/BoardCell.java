package experiment;

public class BoardCell {
	
	public BoardCell(int row, int column, String roomType) {
		super();
		this.row = row;
		this.column = column;
		this.roomType = roomType;
	}
	
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

	int row;
	int column;
	String roomType;
}
