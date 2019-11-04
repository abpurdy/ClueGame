package clueGame;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**@author Austin Purdy
 * @author Tanner Lorenz
 * A class to track a player in the game.*/
public class Player {
	
	//class variables
	
	/**Whether or not this player is human.*/
	private boolean human;
	/**This player's name.*/
	private String name;
	/*The row this player is at.**/
	protected int row;
	/**The column this player is at.*/
	protected int column;
	/**This player's color.*/
	private Color color;
	/**Array of the players cards**/
	protected ArrayList<Card> myCards;
	/**Arraylist of the cards the player has seen**/
	protected ArrayList<Card> seenCards;
	
	//constructors

	/**Create a new player object.
	 * @param human Whether or not this is a human player.
	 * @param row This player's starting row.
	 * @param column This player's starting column.
	 * @param name This player's name.
	 * @param color This player's color as a String, eg. "white"*/
	public Player(boolean human, int row, int column, String name, String color) {
		
		//init class variables
		
		this.human = human;
		this.row = row;
		this.column = column;
		this.name = name;
		
		myCards = new ArrayList<Card>();
		seenCards = new ArrayList<Card>();
		
		//create color object from string
		try {
			Field field = Class.forName("java.awt.Color").getField(color);
			this.color = (Color) field.get(null);
		}
		catch(Exception e){
			this.color = null;
		}
		
	}
	
	
	//class methods
	
	/**Add a card to this player's hand.
	 * @param card The card to add.*/
	public void giveCard(Card card) {
		myCards.add(card);
	}
	
	public ArrayList<Card> getMyCards() {
		return myCards;
	}


	public void setMyCards(ArrayList<Card> myCards) {
		this.myCards = myCards;
	}


	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}


	public void setSeenCards(ArrayList<Card> seenCards) {
		this.seenCards = seenCards;
	}


	/**Disprove the current suggestion.
	 * @return A Card object.*/
	public Card disproveSuggestion(Solution suggestion) {
		
		ArrayList<Card> matchingCards = new ArrayList<Card>(); //list of cards in player's hand that match suggestion
		
		//get all matching cards
		for(Card card : myCards)
			if(card.getName().equals(suggestion.person) || card.getName().equals(suggestion.room) || card.getName().equals(suggestion.weapon))
				matchingCards.add(card);
				
		if(matchingCards.size() == 0)
			return null;
		else if(matchingCards.size() == 1)
			return matchingCards.get(0);
		else
			return matchingCards.get(0); //TODO make random
		
	}
	
	/**Test if this player object is equal to another player object.
	 * @return True if both players are equal.*/
	public boolean equals(Player player) {
		return (name.equals(player.name) && color.equals(player.color) && human == player.human 
				&& row == player.row && column == player.column);
	}

}
