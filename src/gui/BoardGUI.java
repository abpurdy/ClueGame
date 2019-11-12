package gui;

import javax.swing.JFrame;
import clueGame.Board;

public class BoardGUI extends JFrame{
	
	public static Board board = Board.getInstance();

	public BoardGUI() {
		
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();

		setTitle("Clue Board");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(board);
		
	}
	
	public static void main(String[] args) {
		
		BoardGUI gui = new BoardGUI();
		gui.setVisible(true);
		
	}

}
