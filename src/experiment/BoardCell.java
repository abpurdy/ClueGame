package experiment;

public class BoardCell {
	
	public BoardCell(int row, int column, String roomType) {
		super();
		this.row = row;
		this.column = column;
		this.roomType = roomType;
	}
	int row;
	int column;
	String roomType;
}
