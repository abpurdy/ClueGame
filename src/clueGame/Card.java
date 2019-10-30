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
	//getters and setters
	public String getName() {
		return name;
	}


	public CardType getType() {
		return type;
	}


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		//return true if object is compared with itself
		if (o == this) {
			return true;
		}
		//Check if o is an instance of Card
		if(!(o instanceof Card)) {
			return false;
		}
		//check to see if the values of the card are equal
		Card card = (Card) o;
		return (name.equals(card.name) && type == card.type);
	}
}
