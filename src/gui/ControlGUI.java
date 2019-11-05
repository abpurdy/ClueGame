package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlGUI extends JFrame {
	
	public JTextField currentPlayer;
	public JTextField currentRoll;
	public JTextField currentGuess;
	public JTextField currentGuessResult;

	public ControlGUI() {

		setTitle("Clue Game");
		setSize(1000, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(createMainPanel());
		
	}
	
	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}
	
	public JPanel createMainPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		JPanel topPanel = new JPanel();
		topPanel.add(createIndicatorPanel());
		topPanel.add(createButtonPanel());
		panel.add(topPanel);
		panel.add(createInfoPanel());
		return panel;
		
	}
	
	public JPanel createButtonPanel() {
		
		JButton nextPlayerButton = new JButton("Next player");
		JButton accusationButton = new JButton("Make accusation");
		JPanel panel = new JPanel();
		panel.add(nextPlayerButton);
		panel.add(accusationButton);
		return panel;
		
	}
	
	public JPanel createIndicatorPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		JLabel label = new JLabel("Whose turn?");
		currentPlayer = new JTextField();
		currentPlayer.setEditable(false);
		panel.add(label);
		panel.add(currentPlayer);
		return panel;
		
	}
	
	public JPanel createInfoPanel() {
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1, 3));
		JLabel dieLabel = new JLabel("Roll:");
		JLabel guessLabel = new JLabel("Guess:");
		JLabel resultLabel = new JLabel("Response:");
		currentRoll = new JTextField();
		currentRoll.setEditable(false);
		currentGuess = new JTextField();
		currentGuess.setEditable(false);
		currentGuessResult = new JTextField();
		currentGuessResult.setEditable(false);
		JPanel diePanel = new JPanel();
		diePanel.setLayout(new GridLayout(1, 2));
		diePanel.add(dieLabel);
		diePanel.add(currentRoll);
		infoPanel.add(diePanel);
		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(1, 2));
		guessPanel.add(guessLabel);
		guessPanel.add(currentGuess);
		infoPanel.add(guessPanel);
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new GridLayout(1, 2));
		resultPanel.add(resultLabel);
		resultPanel.add(currentGuessResult);
		infoPanel.add(resultPanel);
		return infoPanel;
				
	}

}
