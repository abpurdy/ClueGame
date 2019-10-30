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
		ArrayList<Player> players = board.getPlayers();
		Player player1 = players.get(0);
		//checks that all players have roughly the same number of cards
		for(Player player : players) {
			//ensures that all players have the same number of cards or one less than first player
			assertTrue(player1.getMyCards().size()-player.getMyCards().size() == 1 ||
					player1.getMyCards().size()-player.getMyCards().size() == 0);
		}

		//makes sure no two players have the same card
		//goes through each player in the deck and compares their cards to the cards of every other player
		for(Player player2 : players) {
			for(Player player3 : players) {
				if(!player2.equals(player3)) { //doesn't compare the cards of a player to itself
					//ensures that no card in player2's deck is in player3's deck
					for(Card playerCard : player2.getMyCards()) {
						ArrayList<Card> playerCards = player3.getMyCards();
						assertFalse(playerCards.contains(playerCard));
					}
				}
			}
		}
	}
}
