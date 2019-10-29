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
	public Card(String name, String type) {
		
		//init class variables
		
		this.name = name;
		
		//get card type from string
		switch(type) {
		
			case "weapon":
				this.type = CardType.WEAPON;
				break;
				
			case "person":
				this.type = CardType.PERSON;
				break;
				
			case "room":
				this.type = CardType.ROOM;
				break;
				
			default:
				this.type = null;
				break;
			
		}
		
	}
	
	
	//class methods
	
	/**Returns true if this card equals the given card.
	 * @param card The card to test against.*/
	public boolean equals(Card card) {
		return (name.equals(card.name) && type == card.type);
	}

}
