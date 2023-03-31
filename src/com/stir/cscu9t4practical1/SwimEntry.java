package com.stir.cscu9t4practical1;

import java.util.Calendar;

public class SwimEntry extends Entry{
	private String loc;
	  
	  public SwimEntry (String n, int d, int m, int y, int h, int min, int s, float dist, String loc) {
		  super();
		  this.name = n;
		  Calendar inst = Calendar.getInstance();
		  inst.set(y,m-1,d,h,min,s);
		  this.dateAndTime = inst;
		  this.distance = dist;
		  
		  this.loc = loc;
	  } //constructor
	  
	  /* getWhere will return a string that formats the entry
	   * based on the location given to it.
	   */
	  public String getWhere() {
		  if (loc.equalsIgnoreCase("outdoors")) {
			  return loc;
		  } else {
			  return "in a " + loc;
		  }
	  }
	  
	  public String getEntry () {
		   String result = getName()+" swam " + getDistance() + " km " + getWhere() + " in "
		             +getHour()+":"+getMin()+":"+ getSec() + " on "
		             +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
		   return result;
}
}