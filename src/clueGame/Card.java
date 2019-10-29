package clueGame;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * A class for a game card.*/
public class Card {

	/**An enum for the different card types.*/
	public enum CardType{
		PERSON, WEAPON, ROOM;
	}
	
	
	//class variables
	
	/**The name of this card.*/
	private String name;
	/**This card's type.*/
	private CardType type;
	
	
	//constructors
	
	/**Create a new card object.
	 * @param name The name of this card.
	 * @param type The card type as a string.*/
	public Card(String name, CardType type) {
		
		//init class variables
		
		this.name = name;
		this.type = type;
	}
	
	
	//class methods
	
	/**Returns true if this card equals the given card.
	 * @param card The card to test against.*/
	public boolean equals(Card card) {
		return (name.equals(card.name) && type == card.type);
	}

}
