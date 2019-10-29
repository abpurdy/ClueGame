package clueGame;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * A class to track the solution to the game.*/
public class Solution {

	//class variables
	
	/**The person for this solution.*/
	public String person;
	/**The room for this solution.*/
	public String room;
	/**The weapon for this solution.*/
	public String weapon;
	
	
	//constructors
	
	/**Create a new solution.
	 * @param person The person for this solution.
	 * @param room The room for this solution.
	 * @param weapon The weapon for this solution.*/
	public Solution(String person, String room, String weapon) {
		
		//init class variables
		
		this.person = person;
		this.room = room;
		this.weapon = weapon;
		
	}

}
