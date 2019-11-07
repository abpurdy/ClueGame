package gui;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotesGUI extends JPanel {
	private JTextField name;

	public DetectiveNotesGUI() {
		setLayout(new GridLayout(3,2));
		JPanel panel = createPeoplePanel();
		add(panel);
		panel = createPersonGuess();
		add(panel);
		panel = createRoomsPanel();
		add(panel);
		panel = createRoomGuess();
		add(panel);
		panel = createWeaponsPanel();
		add(panel);
		panel = createWeaponGuess();
		add(panel);
	}

	private JPanel createWeaponGuess() {
		JPanel panel = new JPanel();

		String weaponList[] = {"Arrow", "Turtle", "Rock", "Statue", "Bomb", "Mask"};
		JComboBox weaponBox = new JComboBox(weaponList);

		panel.add(weaponBox);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));

		return panel;
	}

	private JPanel createRoomGuess() {
		JPanel panel = new JPanel();

		String roomList[] = {"Bathroom","Living Room","Observatory","Long Room","War Room","Kitchen","Library","Office"};
		JComboBox roomBox = new JComboBox(roomList);

		panel.add(roomBox);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));

		return panel;
	}

	private JPanel createPersonGuess() {
		JPanel panel = new JPanel();

		String personList[] = {"Jotaro","Kakyoin","Okuyasu","Giorno","Bruno","Josuke"};
		JComboBox personBox = new JComboBox(personList);

		panel.add(personBox);
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));

		return panel;
	}

	private JPanel createWeaponsPanel() {
		JPanel panel = new JPanel();

		JCheckBox arrow = new JCheckBox("Arrow");
		JCheckBox turtle = new JCheckBox("Turtle");
		JCheckBox rock = new JCheckBox("Rock");
		JCheckBox statue = new JCheckBox("Statue");
		JCheckBox bomb = new JCheckBox("Bomb");
		JCheckBox mask = new JCheckBox("Mask");

		panel.add(arrow);
		panel.add(turtle);
		panel.add(rock);
		panel.add(statue);
		panel.add(bomb);
		panel.add(mask);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));

		return panel;
	}

	private JPanel createRoomsPanel() {
		JPanel panel = new JPanel();

		JCheckBox library = new JCheckBox("Library");
		JCheckBox bathroom = new JCheckBox("Bathroom");
		JCheckBox observatory = new JCheckBox("Observatory");
		JCheckBox longRoom = new JCheckBox("Long Room");
		JCheckBox livingRoom = new JCheckBox("Living Room");
		JCheckBox kitchen = new JCheckBox("Kitchen");
		JCheckBox office = new JCheckBox("Office");
		JCheckBox warRoom = new JCheckBox("War Room");
		JCheckBox ballRoom = new JCheckBox("Ball Room");

		panel.add(library);
		panel.add(ballRoom);
		panel.add(bathroom);
		panel.add(observatory);
		panel.add(longRoom);
		panel.add(kitchen);
		panel.add(office);
		panel.add(warRoom);
		panel.add(livingRoom);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));

		return panel;
	}

	private JPanel createPeoplePanel() {
		JPanel panel = new JPanel();

		JCheckBox jotaro = new JCheckBox("Jotaro");
		JCheckBox kakyoin = new JCheckBox("Kakyoin");
		JCheckBox okuyasu = new JCheckBox("Okuyasu");
		JCheckBox giorno = new JCheckBox("Giorno");
		JCheckBox bruno = new JCheckBox("Bruno");
		JCheckBox josuke = new JCheckBox("Josuke");

		panel.add(jotaro);
		panel.add(kakyoin);
		panel.add(okuyasu);
		panel.add(giorno);
		panel.add(bruno);
		panel.add(josuke);

		panel.setBorder(new TitledBorder (new EtchedBorder(), "People"));

		return panel;
	}
}
