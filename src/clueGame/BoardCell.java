package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

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
	
	/**The size of this cell on the screen.*/
	private static final int SIZE = 25;
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
	
	/**Draw this board cell on the gui.
	 * @param g The graphics object to draw.*/
	public void draw(Graphics g, Set<BoardCell> targets, ArrayList<Player> players) {
		
		//draw walkways and rooms
		
		if(isWalkway())
			g.setColor(Color.YELLOW);
		else if(targets.contains(this))
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.GRAY);
		
		g.fillRect(column*SIZE, row*SIZE, SIZE, SIZE);
		
		g.setColor(Color.BLACK);
		g.drawRect(column*SIZE, row*SIZE, SIZE, SIZE);
		
		//draw doorways
		
		if(isDoorway()) {
			
			g.setColor(Color.BLUE);
			
			switch(doorDirection){
			
			case UP:
				g.fillRect(column*SIZE, row*SIZE, SIZE, 5);
				break;
				
			case LEFT:
				g.fillRect(column*SIZE, row*SIZE, 5, SIZE);
				break;
				
			case RIGHT:
				g.fillRect((column*SIZE)+SIZE-5, row*SIZE, 5, SIZE);
				break;
				
			case DOWN:
				g.fillRect(column*SIZE, (row*SIZE)+SIZE-5, SIZE, 5);
				break;
				
			default:
				break;
			
			}
			
		}
		
		//draw players
		
		for(Player player : players) {
			if(player.row == row && player.column == column) {
				g.setColor(player.getColor());
				g.fillOval(column*SIZE, row*SIZE, SIZE, SIZE);
				g.setColor(Color.BLACK);
				g.drawOval(column*SIZE, row*SIZE, SIZE, SIZE);
			}
		}
		
		//draw room labels
		
		if(roomType.length() > 1 && roomType.charAt(1) == 'N') {
			g.setColor(Color.BLUE);
			g.drawString(Board.getInstance().getLegend().get(roomType.charAt(0)), column*SIZE, (row*SIZE)-5);
		}
			
		
	}
	
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
