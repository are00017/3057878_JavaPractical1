package com.stir.cscu9t4practical1;

import java.util.Calendar;

public class CycleEntry extends Entry{
	private String terrain;
	private String tempo; 
	  
	  public CycleEntry (String n, int d, int m, int y, int h, int min, int s, float dist,String terrain,String tempo) {
		  super();
		  this.name = n;
		  Calendar inst = Calendar.getInstance();
		  inst.set(y,m-1,d,h,min,s);
		  this.dateAndTime = inst;
		  this.distance = dist;
		  
		  this.terrain = terrain;
		  this.tempo = tempo;
	  } //constructor
	
	  public String getTerrain () {
		    return terrain;
		  } //getTerrain
	  
	  public String getTempo () {
		    return tempo;
		  } //getTempo
	  
	  public String getEntry () {
		   String result = getName()+" cycled " + getDistance() + " km in "
		             +getHour()+":"+getMin()+":"+ getSec() + " on "
		             +getDay()+"/"+getMonth()+"/"+getYear()+" on " + getTerrain() + " at "
		             +getTempo() + " tempo\n";
		   return result;
}
	  }
