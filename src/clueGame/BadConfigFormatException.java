package clueGame;

/**@author Tanner Lorenz
 * @author Austin Purdy
 * Exception that is thrown when a Clue config file has an error.*/
public class BadConfigFormatException extends Exception {
	
	//constructors
	
	/**Create a new BadConfigFormatException with a default error message.*/
	public BadConfigFormatException() {
		super("Config file format is incorrect.");
	}
	
	/**Create a new BadConfigFormatException with the specified error message.*/
	public BadConfigFormatException(String message) {
		super(message);
	}

}
