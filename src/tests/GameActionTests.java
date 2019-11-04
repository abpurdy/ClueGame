package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameActionTests {

	private static Board board;

	@BeforeClass
	public static void setup() {
		//TODO setup board object
		board = Board.getInstance();
		board.setConfigFiles("Clue Board.csv", "ClueRooms.txt", "PlayerConfig.txt", "CardConfig.txt");
		board.initialize();
	}

	@Test
	public void testCPUTargetSelection() {
		ComputerPlayer compPlayer = new ComputerPlayer(4,4,"Test","white");
		board.calcTargets(7, 3, 1);
		//First case, no rooms in list
		//resets the cell count map
		Map <BoardCell, Integer> cellCount = new HashMap <BoardCell, Integer>();

		//test random target selection
		for(int i = 0; i < 100000; i++) {
			//selects the targets 100000 times and keeps track of how many times each target is chosen
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, 0);
			cellCount.put(chosen, cellCount.get(chosen)+1);
		}

		//ensures that each cell was chosen at least once
		assertTrue(cellCount.size() == 3);
		//makes sure that each target was chosen a significant number of times
		for(int count : cellCount.values()) {
			assertTrue(count >= 5000);
		}

		board.calcTargets(7, 3, 2);
		cellCount = new HashMap <BoardCell, Integer>();
		//second case room is in list and not visited previously
		//test random target selection
		for(int i = 0; i < 100; i++) {
			//selects the targets 100 times and keeps track of how many times each target is chosen
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, 0);
			cellCount.put(chosen, cellCount.get(chosen)+1);
		}
		//tests that the doorway is chosen every time
		for(BoardCell cell : cellCount.keySet()) {
			if(cell.isDoorway()) {
				//there is only one doorway as a target option so it should be chosen every time
				assertTrue(cellCount.get(cell) == 100);
			}
			else {
				//none of the other options should ever be chosen
				assertTrue(cellCount.get(cell) == 0);
			}
		}


		//third case, room is in list and visited previously
		//resets the cell count map
		cellCount = new HashMap <BoardCell, Integer>();
		//sets the previous cell to the one doorway in the target options
		//so selection should now be random
		compPlayer.setPreviousCell(board.getCellAt(6, 2));

		//test random target selection
		for(int i = 0; i < 100000; i++) {
			//selects the targets 100000 times and keeps track of how many times each target is chosen
			BoardCell chosen = compPlayer.pickLocation(board.getTargets());
			cellCount.putIfAbsent(chosen, 0);
			cellCount.put(chosen, cellCount.get(chosen)+1);
		}

		//ensures that each cell was chosen at least once
		assertTrue(cellCount.size() == 7);
		//makes sure that each target was chosen a significant number of times
		for(int count : cellCount.values()) {
			assertTrue(count >= 1000);
		}

	}

	@Test
	public void testAccusationCheck() {
		//case where accusation is correct
		Solution accusation = board.getSolution();
		assertTrue(board.checkAccusation(accusation));
		//case where weapon is incorrect
		accusation = new Solution(board.getSolution().person, board.getSolution().room, "wrong");
		assertFalse(board.checkAccusation(accusation));
		//case where person is incorrect
		accusation = new Solution("wrong", board.getSolution().room, board.getSolution().weapon);
		assertFalse(board.checkAccusation(accusation));
		//case where room in incorrect
		accusation = new Solution(board.getSolution().person, "wrong", board.getSolution().weapon);
		assertFalse(board.checkAccusation(accusation));
	}

	@Test
	public void testDisproveSuggestion() {
		
		Player player = new ComputerPlayer(0, 0, "Test", "white");
		Solution suggestion = new Solution("Jotaro", "Long Room", "Turtle");
		
		//test 1 matching card
		
		player.giveCard(new Card("Jotaro", CardType.PERSON));
		player.giveCard(new Card("War Room", CardType.ROOM));
		player.giveCard(new Card("Arrow", CardType.WEAPON));
		
		assertTrue(player.disproveSuggestion(suggestion).equals(new Card("Jotaro", CardType.PERSON)));
		
		//test more than 1 matching card
		
		player.setMyCards(new ArrayList<Card>());
		
		player.giveCard(new Card("Jotaro", CardType.PERSON));
		player.giveCard(new Card("Long Room", CardType.ROOM));
		player.giveCard(new Card("Arrow", CardType.WEAPON));
		
		assertTrue(player.disproveSuggestion(suggestion).equals(new Card("Jotaro", CardType.PERSON)) || player.disproveSuggestion(suggestion).equals(new Card("Long Room", CardType.ROOM)));
		
		//test no matching cards
		
		player.setMyCards(new ArrayList<Card>());
		
		player.giveCard(new Card("Okuyasu", CardType.PERSON));
		player.giveCard(new Card("War Room", CardType.ROOM));
		player.giveCard(new Card("Arrow", CardType.WEAPON));
		
		assertTrue(player.disproveSuggestion(suggestion) == null);
		
	}

	@Test
	public void testSuggestionHandling() {

	}

	@Test
	public void testCreateSuggestion() {
		//create computer player to perform tests
		ComputerPlayer compPlayer = new ComputerPlayer(6,2,"Test","white");

		//Makes sure the room matches current location
		assertTrue(compPlayer.createSuggestion(board).room.equals("Library"));

		//tests that weapons and people are randomly select one of the cards not seen
		//add cards to the computer players hand
		ArrayList<Card> hand = new ArrayList<Card>();
		hand = compPlayer.getMyCards();
		Card myCard = new Card("Arrow", CardType.WEAPON);
		hand.add(myCard);
		myCard = new Card("Turtle", CardType.WEAPON);
		hand.add(myCard);
		myCard = new Card("Jotaro", CardType.PERSON);
		hand.add(myCard);
		compPlayer.setMyCards(hand);

		//add cards to the computer players hand
		ArrayList<Card> seen = new ArrayList<Card>();
		seen = compPlayer.getSeenCards();
		Card seenCard = new Card("Rock", CardType.WEAPON);
		seen.add(seenCard);
		seenCard = new Card("Kakyoin", CardType.PERSON);
		seen.add(seenCard);
		seenCard = new Card("Okuyasu", CardType.PERSON);
		seen.add(seenCard);
		compPlayer.setSeenCards(seen);

		Map <String, Integer> personCount = new HashMap <String, Integer>();
		Map <String, Integer> weaponCount = new HashMap <String, Integer>();
		for(int i = 0; i < 100000; i++) {
			//makes suggestion 100000 times and keeps track of which people and weapons are chosen
			Solution suggestion = compPlayer.createSuggestion(board);
			personCount.putIfAbsent(suggestion.person, 0);
			personCount.put(suggestion.person, personCount.get(suggestion.person)+1);
			weaponCount.putIfAbsent(suggestion.weapon, 0);
			weaponCount.put(suggestion.weapon, weaponCount.get(suggestion.weapon)+1);
		}
		//makes sure that the correct number of available weapons and people were chosen
		assertTrue(personCount.size() == 3);
		assertTrue(weaponCount.size() == 3);
		//ensures that the correct available weapons and people were chosen
		assertTrue(personCount.containsKey("Giorno") && personCount.containsKey("Bruno") && personCount.containsKey("Josuke"));
		assertTrue(weaponCount.containsKey("Statue") && weaponCount.containsKey("Bomb") && weaponCount.containsKey("Mask"));
		//makes sure that each weapon and person was chosen a significant number of times
		for(int count : personCount.values()) {
			assertTrue(count >= 5000);
		}
		for(int count : weaponCount.values()) {
			assertTrue(count >= 5000);
		}

		//add cards to the computer players hand so that there is only one weapon and person option left
		seen = new ArrayList<Card>();
		seen = new ArrayList<Card>(compPlayer.getSeenCards());
		seenCard = new Card("Statue", CardType.WEAPON);
		seen.add(seenCard);
		seenCard = new Card("Bomb", CardType.WEAPON);
		seen.add(seenCard);
		seenCard = new Card("Giorno", CardType.PERSON);
		seen.add(seenCard);
		seenCard = new Card("Bruno", CardType.PERSON);
		seen.add(seenCard);
		compPlayer.setSeenCards(seen);
		
		//make a suggestion
		Solution suggestion = compPlayer.createSuggestion(board);
		//makes sure the person and weapon in the suggestion are the only remaining options
		assertTrue(suggestion.person.equals("Josuke") && suggestion.weapon.equals("Mask"));
	}

}
