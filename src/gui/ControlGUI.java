package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * Class that holds functionality for the "control" portion of the gui.*/
public class ControlGUI extends JPanel {
	
	//class variables
	
	/**Text field to display current player.*/
	public JTextField currentPlayer;
	/**Text field to display current dice roll.*/
	public JTextField currentRoll;
	/**Text field to display current guess.*/
	public JTextField currentGuess;
	/**Text field to display the result of the guess.*/
	public JTextField currentGuessResult;

	
	//constructors
	
	/**Create the control gui.*/
	public ControlGUI() {
		
		//setup gui appearance
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		JPanel topPanel = new JPanel();
		topPanel.add(createIndicatorPanel());
		topPanel.add(createButtonPanel());
		panel.add(topPanel);
		panel.add(createInfoPanel());
		add(panel);
		
	}
	
	
	//class methods
	
	/**Create the panel of buttons.
	 * @return The created panel.*/
	public JPanel createButtonPanel() {
		
		//create and add buttons to panel
		
		JButton nextPlayerButton = new JButton("Next player");
		nextPlayerButton.addActionListener(new NextPlayerListener());
		JButton accusationButton = new JButton("Make accusation");
		JPanel panel = new JPanel();
		panel.add(nextPlayerButton);
		panel.add(accusationButton);
		
		return panel;
		
	}
	
	/**Create panel that contains the turn indicator.
	 * @return The created panel.*/
	public JPanel createIndicatorPanel() {
		
		//add label and text field
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		JLabel label = new JLabel("Whose turn?");
		currentPlayer = new JTextField();
		currentPlayer.setEditable(false);
		currentPlayer.setText(Board.getInstance().getCurrentPlayer().getName());
		panel.add(label);
		panel.add(currentPlayer);
		
		return panel;
		
	}
	
	/**Create the panel that controls the text fields.
	 * @return The created panel.*/
	public JPanel createInfoPanel() {
		
		//setup labels and panel
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1, 3));
		JLabel dieLabel = new JLabel("Roll:");
		JLabel guessLabel = new JLabel("Guess:");
		JLabel resultLabel = new JLabel("Response:");
		
		//setup text fields
		
		currentRoll = new JTextField();
		currentRoll.setEditable(false);
		currentGuess = new JTextField();
		currentGuess.setEditable(false);
		currentGuessResult = new JTextField();
		currentGuessResult.setEditable(false);
		
		//setup dice panel
		
		JPanel diePanel = new JPanel();
		diePanel.setLayout(new GridLayout(1, 2));
		diePanel.add(dieLabel);
		diePanel.add(currentRoll);
		infoPanel.add(diePanel);
		
		//setup guess panel
		
		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(1, 2));
		guessPanel.add(guessLabel);
		guessPanel.add(currentGuess);
		infoPanel.add(guessPanel);
		
		//setup guess result panel
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new GridLayout(1, 2));
		resultPanel.add(resultLabel);
		resultPanel.add(currentGuessResult);
		infoPanel.add(resultPanel);
		
		return infoPanel;
				
	}
	
	/**Action listener for the Next Player button.*/
	private class NextPlayerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			Board.getInstance().nextTurn();
			currentRoll.setText(Integer.toString(Board.rollDice()));
			currentPlayer.setText(Board.getInstance().getCurrentPlayer().getName());
			
		}
		
	}

}
