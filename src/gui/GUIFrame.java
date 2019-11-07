package gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GUIFrame extends JFrame{

	public GUIFrame() throws HeadlessException {
		setTitle("Clue Game");
		setSize(1000, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ControlGUI control = new ControlGUI();
		DetectiveNotesGUI notes = new DetectiveNotesGUI();
		add(control);
		add(notes);
	}
	
	public static void main(String[] args) {
		GUIFrame gui = new GUIFrame();
		gui.setVisible(true);
	}
}
