// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.util.ArrayList;

public class TrainingRecordGUI extends JFrame implements ActionListener {

	private JTextField name = new JTextField(30);
	private JTextField day = new JTextField(2);
	private JTextField month = new JTextField(2);
	private JTextField year = new JTextField(4);
	private JTextField hours = new JTextField(2);
	private JTextField mins = new JTextField(2);
	private JTextField secs = new JTextField(2);
	private JTextField dist = new JTextField(4);
	private JLabel labn = new JLabel(" Name:");
	private JLabel labd = new JLabel(" Day:");
	private JLabel labm = new JLabel(" Month:");
	private JLabel laby = new JLabel(" Year:");
	private JLabel labh = new JLabel(" Hours:");
	private JLabel labmm = new JLabel(" Mins:");
	private JLabel labs = new JLabel(" Secs:");
	private JLabel labdist = new JLabel(" Distance (km):");
	private JButton addR = new JButton("Add");
	private JButton lookUpByDate = new JButton("Look Up");
	private JButton findAllByDate = new JButton("Find All By Date");
	private JButton weeklyDistance = new JButton("Weekly Distance");
	
	// Remove Button
	private JButton remove = new JButton("Remove Entry");

	// Radio Buttons
	private JRadioButton cycle = new JRadioButton("Cycle");
	private JRadioButton sprint = new JRadioButton("Sprint");
	private JRadioButton swim = new JRadioButton("Swim");
	private JRadioButton entry = new JRadioButton("Run");

	private ButtonGroup g = new ButtonGroup();

	// Cycling
	private JTextField terrain = new JTextField(30);
	private JTextField tempo = new JTextField(10);
	private JLabel labTerrain = new JLabel(" Terrain:");
	private JLabel labTempo = new JLabel(" Tempo:");

	// Sprint
	private JTextField meters = new JTextField(4);
	private JTextField reps = new JTextField(4);
	private JTextField rec = new JTextField(4);
	private JLabel labMeters = new JLabel(" Meters:");
	private JLabel labReps = new JLabel(" Repetitions:");
	private JLabel labRec = new JLabel(" Recovery:");

	// Swim
	private JTextField location = new JTextField(20);
	private JLabel labLoc = new JLabel(" Location:");

	private TrainingRecord myAthletes = new TrainingRecord();

	private JTextArea outputArea = new JTextArea(5, 50);

	public static void main(String[] args) {
		TrainingRecordGUI applic = new TrainingRecordGUI();
	} // main

	// set up the GUI
	public TrainingRecordGUI() {
		super("Training Record");
		setLayout(new FlowLayout());
		
		// add buttons to ButtonGroup (to be able to only choose one)
		g.add(entry);
		g.add(cycle);
		g.add(sprint);
		g.add(swim);

		// add radioButtons
		add(entry);
		entry.addActionListener(this);
		add(cycle);
		cycle.addActionListener(this);
		add(sprint);
		sprint.addActionListener(this);
		add(swim);
		swim.addActionListener(this);

		add(labn);
		add(name);
		name.setEditable(false);
		add(labd);
		add(day);
		day.setEditable(false);
		add(labm);
		add(month);
		month.setEditable(false);
		add(laby);
		add(year);
		year.setEditable(false);
		add(labh);
		add(hours);
		hours.setEditable(false);
		add(labmm);
		add(mins);
		mins.setEditable(false);
		add(labs);
		add(secs);
		secs.setEditable(false);
		add(labdist);
		add(dist);
		dist.setEditable(false);
		

		// cycle
		add(labTerrain);
		add(terrain);
		labTerrain.setVisible(false);
		terrain.setVisible(false);
		add(labTempo);
		add(tempo);
		labTempo.setVisible(false);
		tempo.setVisible(false);

		// sprint
		add(labMeters);
		add(meters);
		labMeters.setVisible(false);
		meters.setVisible(false);
		add(labReps);
		add(reps);
		labReps.setVisible(false);
		reps.setVisible(false);
		add(labRec);
		add(rec);
		labRec.setVisible(false);
		rec.setVisible(false);

		// swim
		add(labLoc);
		add(location);
		labLoc.setVisible(false);
		location.setVisible(false);
		
		terrain.setEditable(false);
		tempo.setEditable(false);

		meters.setEditable(false);
		reps.setEditable(false);
		rec.setEditable(false);

		location.setEditable(false);

		//buttons
		add(addR);
		addR.addActionListener(this);
		addR.setEnabled(false);
		add(lookUpByDate);
		lookUpByDate.addActionListener(this);
		lookUpByDate.setEnabled(false);
		add(findAllByDate);
		findAllByDate.addActionListener(this);
		findAllByDate.setEnabled(false);
		// removes entry
		add(remove);
		remove.addActionListener(this);
		remove.setEnabled(false);
		// weekly distance
		add(weeklyDistance);
		weeklyDistance.addActionListener(this);
		weeklyDistance.setEnabled(false);
		
		add(outputArea);
		outputArea.setEditable(false);
		setSize(720, 200);
		setVisible(true);
		blankDisplay();
	} // constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event) {
		String message = "";
		if (event.getSource() == addR) {
			message = addEntry("generic");
		}
		if (event.getSource() == lookUpByDate) {
			message = lookupEntry();
		}
		if (event.getSource() == findAllByDate) {
			message = lookupAllEntry();
		}
		if (event.getSource() == remove) {
			message = removeEntry();
		}
		if (event.getSource() == weeklyDistance) {
			message = weeklyEntry();
		}
		outputArea.setText(message);
		blankDisplay();

		/*
		 *  If any type is chosen, the common textFields will be enabled,
		 *  if a specific type is chose, their respective textFields will appear.
		 *  changing the type will dynamically change the textFields depending
		 *  on what is needed.
		 */
		if (entry.isSelected() || cycle.isSelected() || sprint.isSelected() || swim.isSelected()) {
			name.setEditable(true);
			day.setEditable(true);
			month.setEditable(true);
			year.setEditable(true);
			hours.setEditable(true);
			mins.setEditable(true);
			secs.setEditable(true);
			dist.setEditable(true);
			addR.setEnabled(true);
			lookUpByDate.setEnabled(true);
			findAllByDate.setEnabled(true);
			remove.setEnabled(true);
			weeklyDistance.setEnabled(true);
			if (cycle.isSelected()) {
				dist.setEditable(true);
				terrain.setEditable(true);
				tempo.setEditable(true);

				labdist.setVisible(true);
				dist.setVisible(true);
				labTerrain.setVisible(true);
				terrain.setVisible(true);
				labTempo.setVisible(true);
				tempo.setVisible(true);

				meters.setEditable(false);
				reps.setEditable(false);
				rec.setEditable(false);

				labMeters.setVisible(false);
				meters.setVisible(false);
				labReps.setVisible(false);
				reps.setVisible(false);
				labRec.setVisible(false);
				rec.setVisible(false);

				location.setEditable(false);
				
				labLoc.setVisible(false);
				location.setVisible(false);
			} else if (sprint.isSelected()) {
				dist.setEditable(false);
				terrain.setEditable(false);
				tempo.setEditable(false);

				labdist.setVisible(false);
				dist.setVisible(false);
				labTerrain.setVisible(false);
				terrain.setVisible(false);
				labTempo.setVisible(false);
				tempo.setVisible(false);
				
				meters.setEditable(true);
				reps.setEditable(true);
				rec.setEditable(true);
				
				labMeters.setVisible(true);
				meters.setVisible(true);
				labReps.setVisible(true);
				reps.setVisible(true);
				labRec.setVisible(true);
				rec.setVisible(true);

				location.setEditable(false);
				
				labLoc.setVisible(false);
				location.setVisible(false);
			} else if (swim.isSelected()) {
				dist.setEditable(true);
				terrain.setEditable(false);
				tempo.setEditable(false);
				
				labdist.setVisible(true);
				dist.setVisible(true);
				labTerrain.setVisible(false);
				terrain.setVisible(false);
				labTempo.setVisible(false);
				tempo.setVisible(false);

				meters.setEditable(false);
				reps.setEditable(false);
				rec.setEditable(false);
				
				labMeters.setVisible(false);
				meters.setVisible(false);
				labReps.setVisible(false);
				reps.setVisible(false);
				labRec.setVisible(false);
				rec.setVisible(false);

				location.setEditable(true);
				
				labLoc.setVisible(true);
				location.setVisible(true);
			} else {
				dist.setEditable(true);
				terrain.setEditable(false);
				tempo.setEditable(false);
				
				labdist.setVisible(true);
				dist.setVisible(true);
				labTerrain.setVisible(false);
				terrain.setVisible(false);
				labTempo.setVisible(false);
				tempo.setVisible(false);

				meters.setEditable(false);
				reps.setEditable(false);
				rec.setEditable(false);
				
				labMeters.setVisible(false);
				meters.setVisible(false);
				labReps.setVisible(false);
				reps.setVisible(false);
				labRec.setVisible(false);
				rec.setVisible(false);

				location.setEditable(false);
				
				labLoc.setVisible(false);
				location.setVisible(false);
			}
		}
	} // actionPerformed
	
	/**
	 * Adds an entry to the records depending on the type selected
	 * @param what The type of entry
	 * @return Confirmation to the GUI text area
	 */
	public String addEntry(String what) {
		String message = "Record added\n";
		System.out.println("Adding " + what + " entry to the records");
		
		// try-catch will detect invalid input (empty inputs, wrong data types, etc.)
		try {
			String n = name.getText();
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			float km = java.lang.Float.parseFloat(dist.getText());
			int h = Integer.parseInt(hours.getText());
			int mm = Integer.parseInt(mins.getText());
			int s = Integer.parseInt(secs.getText());

			/*
			 * Depending on the chosen type, the entry will
			 * instantiate additional inputs to include in the
			 * entry.
			 */
			if (cycle.isSelected()) {
				String terr = terrain.getText();
				String tem = tempo.getText();
				Entry c = new CycleEntry(n, d, m, y, h, mm, s, km, terr, tem);
				myAthletes.addEntry((CycleEntry) c);
			} else if (sprint.isSelected()) {
				int met = Integer.parseInt(meters.getText());
				int rep = Integer.parseInt(reps.getText());
				int recov = Integer.parseInt(rec.getText());
				Entry spr = new SprintEntry(n, d, m, y, h, mm, s, met, rep, recov);
				myAthletes.addEntry((SprintEntry) spr);
			} else if (swim.isSelected()) {
				String where = location.getText();
				Entry swim = new SwimEntry(n, d, m, y, h, mm, s, km, where);
				myAthletes.addEntry((SwimEntry) swim);
			} else {
				Entry run = new Entry(n, d, m, y, h, mm, s, km);
				myAthletes.addEntry(run);
			}
			return message;
		} catch (IllegalArgumentException i) {
			return message = "Enter valid input.";
		}
	}
	
	// returns weekly distance of a certain athlete
	public String weeklyEntry() {
		String message = "Total Weekly Distance\n";
		try {
		if (!name.getText().equals("")) {
			String entName = name.getText();
			outputArea.setText("removing record ...");
			message = myAthletes.weeklyEntry(entName);
			}
		return message;
		} catch (IllegalArgumentException i) {
			return message = "Enter Valid Input";
		}
	}
	
	// removes an entry of the specific name and date
	public String removeEntry() {
		String message = "Removed Record\n";
		try {
		if (!name.getText().equals("")) {
			String entName = name.getText();
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			outputArea.setText("removing record ...");
			message = myAthletes.removeEntry(entName, d, m, y);
			}
		return message;
		} catch (IllegalArgumentException i) {
			return message = "Enter Valid Input";
		}
	}

	// will look up the latest entry of a date
	public String lookupEntry() {
		String message = "";
		try {
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			outputArea.setText("looking up record ...");
			message = myAthletes.lookupEntry(d, m, y);
			return message;
		} catch (IllegalArgumentException i) {
			message = "Enter valid input.";
			return message;
		}
	}

	// will look up all entries of a given date
	public String lookupAllEntry() {
		String message = "";
		try {
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			outputArea.setText("looking up record ...");
			message = myAthletes.lookupEntries(d, m, y);
			return message;
		} catch (IllegalArgumentException i) {
			message = "Enter valid a date.";
			return message;
		}
	}

	public void blankDisplay() {
		name.setText("");
		day.setText("");
		month.setText("");
		year.setText("");
		hours.setText("");
		mins.setText("");
		secs.setText("");
		dist.setText("");
		tempo.setText("");
		terrain.setText("");
		meters.setText("");
		reps.setText("");
		rec.setText("");
		location.setText("");

	}// blankDisplay
		// Fills the input fields on the display for testing purposes only

	public void fillDisplay(Entry ent) {
		name.setText(ent.getName());
		day.setText(String.valueOf(ent.getDay()));
		month.setText(String.valueOf(ent.getMonth()));
		year.setText(String.valueOf(ent.getYear()));
		hours.setText(String.valueOf(ent.getHour()));
		mins.setText(String.valueOf(ent.getMin()));
		secs.setText(String.valueOf(ent.getSec()));
		dist.setText(String.valueOf(ent.getDistance()));
	}
} // TrainingRecordGUI
