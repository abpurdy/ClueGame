package clueGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPanel;

/**Class for a cell that represents a space on the game board.
 * @author Tanner Lorenz\
 * @author Austin Purdy*/
public class BoardCell {
	
	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of BoardCell or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof BoardCell)) { 
            return false; 
        } 
          
        // typecast o to BoardCell so that we can compare data members  
        BoardCell cell = (BoardCell) o; 
          
        // Compare the data members and return accordingly  
        return (cell.row == this.row && cell.column == this.column && cell.doorDirection == this.doorDirection);
    	
	}

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
	 * @param g The graphics object to draw.
	 * @param targets The list of cells that are currently targets.
	 * @param players The list of players.*/
	public void draw(Graphics g, Set<BoardCell> targets, ArrayList<Player> players) {
		
		//draw walkways and rooms
		
		if(isWalkway() && !targets.contains(this))
			g.setColor(Color.YELLOW);
		else if(targets.contains(this)) {
			g.setColor(Color.CYAN);
		}
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
	
	public boolean containsClick(int mouseX, int mouseY) {
		Rectangle rect = new Rectangle(column*SIZE, row*SIZE, SIZE, SIZE);
		if (rect.contains(new Point(mouseX, mouseY))) 
			return true;
		return false;
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
	
	/**Whether or not the mouse is currently on this cell.*/
	public boolean containsMouse(int mouseX, int mouseY) {
		
		if(row*SIZE < mouseY && (row*SIZE)+SIZE > mouseY && column*SIZE < mouseX && (column*SIZE)+SIZE > mouseX)
			return true;
		
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
