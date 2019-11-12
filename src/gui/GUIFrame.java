package gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import clueGame.Board;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * Class to control the main game GUI.*/
public class GUIFrame extends JFrame{
	
	//class variables
	
	/**The game board object.*/
	public static Board board = Board.getInstance();
	/**The dialog for the player's notes.*/
	private DetectiveNotesGUI notes;
	
	
	//constructors

	/**Create the GUI frame.
	 * @throws HeadlessException*/
	public GUIFrame() throws HeadlessException {
		
		//board setup
		
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();
		
		//setup gui appearance
		
		setTitle("Clue Game");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ControlGUI control = new ControlGUI();
		DetectiveNotesGUI notes = new DetectiveNotesGUI();
		add(board, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		add(new PlayerCards(), BorderLayout.EAST);
		
		//add menu bar
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createMenu());
		setJMenuBar(menuBar);
		
	}
	
	
	//class methods
	
	/**Create the menu for the gui.
	 * @return The created menu object.*/
	public JMenu createMenu() {
		
		JMenu menu = new JMenu("File"); //create new menu
		
		//add option to display notes
		
		JMenuItem notesOption = new JMenuItem("Show Notes");
		
		//add listener to menu option
		notesOption.addActionListener(new ActionListener() {
			
			//show notes dialog on screen
			public void actionPerformed(ActionEvent e) {
				
				notes = new DetectiveNotesGUI();
				notes.setVisible(true);
				
			}
			
		});
		
		menu.add(notesOption);
		
		//add option to exit
		
		JMenuItem exitOption = new JMenuItem("Exit");
		
		//add listener to exit option
		exitOption.addActionListener(new ActionListener() {
			
			//exit
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		menu.add(exitOption);
		
		return menu;
		
	}
	
	/**Run the game and show the GUI.*/
	public static void main(String[] args) {
		
		GUIFrame gui = new GUIFrame();
		gui.setVisible(true);
		
		JOptionPane.showMessageDialog(gui, "You are " + Board.getInstance().getPlayers().get(0).getName() + ", press Next Player to begin play.", "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);
		
		board.gameTurnFramework();
	}
}
