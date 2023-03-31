// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.util.*;

public class TrainingRecord {
	private List<Entry> tr;

	// true if current record holds duplicate entry
	public Boolean contains = false;

	public TrainingRecord() {
		tr = new ArrayList<Entry>();
	} // constructor

	/**
	 * Add a record to the list if duplicate does not exist
	 * @param e Entry to add
	 */
	public void addEntry(Entry e) {
		ListIterator<Entry> iter = tr.listIterator();
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getName().equals(e.getName()) && current.getDay() == e.getDay()
					&& current.getMonth() == e.getMonth() && current.getYear() == e.getYear()) {
				contains = true;
			}
		}
		if (!contains) {
			tr.add(e);
		}
	} // addClass

	/**
	 * Will traverse through the record to retrieve a specific athletes' total
	 * weekly distance
	 * 
	 * @param name Athlete's name to search up
	 * @return the Athlete's total distance of the week
	 */
	public String weeklyEntry(String name) {
		ListIterator<Entry> iter = tr.listIterator();
		ArrayList<Float> entries = new ArrayList<Float>(); // will store all the entries distance
		String result = "Cannot Find any Entries";

		// initializing the week prior
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DATE, -7);

		// get the current and the starting week
		Calendar current = Calendar.getInstance();
		current.add(Calendar.DATE, 1);
		Calendar prior = Calendar.getInstance();
		prior.setTime(start.getTime());

		/*
		 * Will start search from 7 days before, including today: (Ex: Today = 30 ;
		 * algorithm will search entries from 23-30). Will match the name and date and
		 * will iterate through the list of entries.
		 */
		for (Date date = prior.getTime(); prior.before(current); prior.add(Calendar.DATE, 1), date = prior.getTime()) {
			while (iter.hasNext()) {
				Entry ent = iter.next();
				if (ent.getName().equalsIgnoreCase(name) && ent.getDate().get(Calendar.DATE) == date.getDate()) {
					entries.add((float) ent.getDistance()); // will add any matching entries to the array
				}
			}
			iter = tr.listIterator();
		}

		if (entries.isEmpty()) {
			return result;
		}

		float weeklyTotal = 0; // will hold the sum of all entries in the array 
		ListIterator<Float> entIter = entries.listIterator();
		while (entIter.hasNext()) {
			weeklyTotal = weeklyTotal + entIter.next();
		}

		result = "Total weekly distance of "+ name + " is " + String.valueOf(weeklyTotal)+"m";
		return result;
	} // weeklyEntry

	/**
	 * Removes a specific entry
	 * @param name Name of the Athlete
	 * @param d day of entry
	 * @param m month of entry
	 * @param y year of entry
	 * @return Confirmation of removal of entry
	 */
	public String removeEntry(String name, int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "Entry removed.";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getName().equals(name) && current.getDay() == d && current.getMonth() == m
					&& current.getYear() == y) {
				tr.remove(current);
			}
		}
		return result;
	} // removeEntry

	// look up the entry of a given day and month
	public String lookupEntry(int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "No entries found";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
				result = current.getEntry();
		}
		return result;
	} // lookupEntry

	// look up all the entries of a given day and month
	public String lookupEntries(int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		ArrayList<String> entries = new ArrayList<String>(); // will hold all the entry results of a given date.
		String result = "Sorry couldn't find anything for this date";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
				entries.add(current.getEntry());
		}

		if (entries.isEmpty()) {
			return result;
		}

		String dateEnt = ""; // will hold all entries as a single string
		ListIterator<String> entIter = entries.listIterator();
		while (entIter.hasNext()) {
			dateEnt = dateEnt.concat(entIter.next());
		}

		result = dateEnt;
		return result;
	} // lookupEntry

	// Count the number of entries
	public int getNumberOfEntries() {
		return tr.size();
	}

	// Clear all entries
	public void clearAllEntries() {
		tr.clear();
	}
} // TrainingRecord