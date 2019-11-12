package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerCards extends JPanel {

	public PlayerCards() {
		setLayout(new GridLayout(0, 1));
		add(new JLabel("Your cards:"));
	}

}
