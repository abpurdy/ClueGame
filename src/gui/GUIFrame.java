package gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clueGame.Board;

public class GUIFrame extends JFrame{
	
	private DetectiveNotesGUI notes;
	
	public static Board board = Board.getInstance();

	public GUIFrame() throws HeadlessException {
		
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();
		
		setTitle("Clue Game");
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ControlGUI control = new ControlGUI();
		DetectiveNotesGUI notes = new DetectiveNotesGUI();
		add(board, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		add(new PlayerCards(), BorderLayout.EAST);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createMenu());
		setJMenuBar(menuBar);
		
	}
	
	public JMenu createMenu() {
		
		JMenu menu = new JMenu("File");
		JMenuItem notesOption = new JMenuItem("Show Notes");
		notesOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notes = new DetectiveNotesGUI();
				notes.setVisible(true);
			}
		});
		menu.add(notesOption);
		return menu;
		
	}
	
	public static void main(String[] args) {
		GUIFrame gui = new GUIFrame();
		gui.setVisible(true);
	}
}
