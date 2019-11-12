package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.Card;
import clueGame.Card.CardType;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * Class that controls the player's card display.*/
public class PlayerCards extends JPanel {
	
	//constructors

	/**Create the card display.*/
	public PlayerCards() {
		
		setLayout(new GridLayout(0, 1));
		add(new JLabel("Your cards:"));
		add(createCardPanels());
		
	}
	
	private JPanel createCardPanels() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		
		JPanel personPanel = new JPanel();
		personPanel.setLayout(new GridLayout(0, 1));
		JLabel personLabel = new JLabel("People");
		personPanel.add(personLabel);
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(0, 1));
		JLabel roomLabel = new JLabel("Rooms");
		roomPanel.add(roomLabel);
		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(0, 1));
		JLabel weaponLabel = new JLabel("Weapons");
		weaponPanel.add(weaponLabel);
		
		//add each card to its respective panel
		for(Card card : Board.getInstance().getPlayers().get(0).getMyCards()) {
			
			JTextField cardName = new JTextField(card.getName());
			cardName.setEditable(false);
			
			if(card.getType() == CardType.PERSON)
				personPanel.add(cardName);
			else if(card.getType() == CardType.ROOM)
				roomPanel.add(cardName);
			else
				weaponPanel.add(cardName);
			
		}
		
		panel.add(personPanel);
		panel.add(roomPanel);
		panel.add(weaponPanel);
		
		return panel;
		
	}

}
