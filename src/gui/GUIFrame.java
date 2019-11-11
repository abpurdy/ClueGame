package gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import clueGame.Board;

public class GUIFrame extends JFrame{
	
	public static Board board = Board.getInstance();

	public GUIFrame() throws HeadlessException {
		
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();
		
		setTitle("Clue Game");
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ControlGUI control = new ControlGUI();
		DetectiveNotesGUI notes = new DetectiveNotesGUI();
		add(board, BorderLayout.CENTER);
		//add(control);
		//add(notes);
		
	}
	
	public static void main(String[] args) {
		GUIFrame gui = new GUIFrame();
		gui.setVisible(true);
	}
}
