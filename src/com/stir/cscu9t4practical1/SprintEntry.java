package com.stir.cscu9t4practical1;

import java.util.Calendar;

public class SprintEntry extends Entry{
	private int meters;
	private int reps;
	private int rec; //mins of recovery
	  
	  public SprintEntry (String n, int d, int m, int y, int h, int min, int s,int meters, int reps, int rec) {
		  super();
		  this.name = n;
		  Calendar inst = Calendar.getInstance();
		  inst.set(y,m-1,d,h,min,s);
		  this.dateAndTime = inst;
		  this.distance = (float)meters/100; // divides to get a float number
		  
		  this.meters = meters;
		  this.reps = reps;
		  this.rec = rec;
	  } //constructor
	  
	  public int getMeters() {
		    return meters;
		  } //getMeters
	  
	  public int getRepetitions () {
		    return reps;
		  } //getRepetitions
	  
	  public int getRecovery () {
		    return rec;
		  } //getRecovery
	  
	  public String getEntry () {
		   String result = getName()+" sprinted " + getRepetitions() + "x" + getMeters() + "m in "
		             +getHour()+":"+getMin()+":"+ getSec() + " with "
		             +getRecovery() + " minutes recovery on "
		             +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
		   return result;
}
}