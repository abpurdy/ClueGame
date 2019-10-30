package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

public class gameSetupTests {

	private static Board board;

	@Before
	public void init() {

		board = Board.getInstance();
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "WeaponsConfig.txt");
		board.initialize();

	}

	@Test
	public void testPlayerLoading() {

		//create 3 player objects to test the first, third and last players in config file

		Player player1 = new HumanPlayer(0, 4, "Jotaro", "black");
		Player player2 = new ComputerPlayer(15, 0, "Okuyasu", "blue");
		Player player3 = new ComputerPlayer(24, 7, "Josuke", "pink");

		assertTrue(player1.equals(board.getPlayers().get(0)));
		assertTrue(player2.equals(board.getPlayers().get(2)));
		assertTrue(player3.equals(board.getPlayers().get(board.getPlayers().size()-1)));

	}

	@Test
	public void testDeckLoading() {
		//get deck from the board
		ArrayList<Card> deck = board.getDeck();
		//ensure that the deck is the correct size
		assertTrue(deck.size() == 21);



		//check number of each card type in deck
		//counts the number of each type of card in deck
		int personCount = 0;
		int weaponCount = 0;
		int roomCount = 0;
		for (Card card : deck) {
			if(card.getType().equals(CardType.PERSON)) {
				personCount++;
			}
			else if(card.getType().equals(CardType.WEAPON)) {
				weaponCount++;
			}
			else if(card.getType().equals(CardType.ROOM)) {
				roomCount++;
			}
		}
		//checks to make sure type counts are correct
		assertEquals(personCount, 6);
		assertEquals(roomCount, 9);
		assertEquals(weaponCount, 6);

		//ensure that names loaded correctly
		//create cards to test against
		Card person = new Card("Jotaro", CardType.PERSON);
		Card weapon = new Card("Turtle", CardType.WEAPON);
		Card room = new Card("Bathroom", CardType.ROOM);
		//ensure that the deck contains these cards
		assertTrue(deck.contains(person));
		assertTrue(deck.contains(weapon));
		assertTrue(deck.contains(room));
	}

	@Test
	public void testCardDealing() {



	}

}
