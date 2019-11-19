package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.Card;
import clueGame.Solution;

public class SuggestionDialog extends JDialog {
	
	//class variables
	
	/**Text field to display the current room.*/
	private JTextField room;
	/**Combo box for person selection.*/
	private JComboBox people;
	/**Combo box for weapon selection.*/
	private JComboBox weapons;
	

	public SuggestionDialog() {
		
		setLayout(new GridLayout(4, 2));
		setSize(300, 200);
		
		room = new JTextField();
		room.setEditable(false);
		
		createLayout();
		
	}
	
	
	//class methods
	
	private void createLayout() {
		
		add(new JLabel("Your room"));
		add(room);
		add(new JLabel("Person"));
		
		String[] peopleList = new String[Board.getInstance().getPlayers().size()];
		for(int x = 0; x < Board.getInstance().getPlayers().size(); x++)
			peopleList[x] = Board.getInstance().getPlayers().get(x).getName();
		
		people = new JComboBox(peopleList);
		add(people);
		
		add(new JLabel("Weapon"));
			
		String[] weaponList = new String[Board.getInstance().getWeaponDeck().size()];
		for(int x = 0; x < Board.getInstance().getWeaponDeck().size(); x++)
			weaponList[x] = Board.getInstance().getWeaponDeck().get(x).getName();
		
		weapons = new JComboBox(weaponList);
		add(weapons);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//create suggestion from current player choices
				
				Solution suggestion = new Solution(people.getSelectedItem().toString(), room.getText(), weapons.getSelectedItem().toString());
				GUIFrame.gui.control.setCurrentGuess(people.getSelectedItem().toString(), room.getText(), weapons.getSelectedItem().toString());
				
				//handle suggestion
				GUIFrame.gui.control.setGuessResult(Board.handleSuggestion(suggestion, Board.getInstance().getPlayers().get(0)));
				setVisible(false);
				
			}
		});
		add(submit);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				
			}
		});
		add(cancel);
		
	}
	
	
	//getters and setters
	
	public void setRoom(String room) {
		this.room.setText(room);
	}

}
