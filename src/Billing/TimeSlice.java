package Billing;

import java.util.Calendar;

public class TimeSlice {
	private int startHour;
	private int endHour;
	private double price;
	
	public TimeSlice(int s, int e, double p) {
		this.startHour = s;
		this.endHour = e;
		this.price = p;
	}
	
	
	public double getTotal(Calendar start, Calendar end) {
		double total = 0.0;
		//check if range is across multiple days
		Calendar midnight = (Calendar) start.clone();
		clearTime(midnight);
		midnight.set(Calendar.HOUR_OF_DAY, 0);
		midnight.add(Calendar.DATE, 1);
		if (end.after(midnight)) {
			total += getTotal(midnight, end); //elaborate "today's quote" and recursively call method on future slices
			end = midnight;
		}		
		Calendar s = (Calendar) start.clone();
		clearTime(s);
		s.set(Calendar.HOUR_OF_DAY, startHour);
		Calendar e = (Calendar) start.clone(); 
		clearTime(e);
		if(endHour != 24) {
			e.set(Calendar.HOUR_OF_DAY, endHour);
		} else {
			e.set(Calendar.HOUR_OF_DAY, 0);
			e.add(Calendar.DATE, 1);
		}
		
		if( start.compareTo(e) <=0 && end.compareTo(s) >= 0 ) {
			if (s.before(start)) s = (Calendar) start.clone();
			if (e.after(end)) e = (Calendar) end.clone() ;
			double minutes = Math.floor((e.getTimeInMillis() - s.getTimeInMillis())/(60 * 1000));
			if(minutes >0 ) total += minutes*price;
		} 
		return total;
	}
	
	private static void clearTime(Calendar c) {
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		c.clear(Calendar.HOUR_OF_DAY);
	}
	

	public int getMaxMinutesPerSlice() {
		return (endHour - startHour)*60;
	}
	
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getEndHour() {
		return endHour;
	}
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	public int getStartHour() {
		return startHour;
	}
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	
}
