package Billing;

import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.SSLContext;

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
		Calendar s = (Calendar) start.clone();
		s.clear(Calendar.MINUTE);
		s.clear(Calendar.SECOND);
		s.set(Calendar.HOUR_OF_DAY, startHour);
		Calendar e = (Calendar) end.clone(); 
		e.clear(Calendar.MINUTE);
		e.clear(Calendar.SECOND);
		if(endHour != 24) {
			e.set(Calendar.HOUR_OF_DAY, endHour);
		}else {
			e.set(Calendar.HOUR_OF_DAY, 0);
			e.add(Calendar.DATE, 1);
		}
		
		if( start.before(e) && end.after(s) ) {
			if (s.before(start)) s = (Calendar) start.clone();
			if (e.after(end)) e = (Calendar) end.clone() ;
			double days = daysBetween(s, e);
			total += days*getMaxMinutesPerSlice()*price;
			e.set(Calendar.YEAR, s.get(Calendar.YEAR));
			e.set(Calendar.MONTH, s.get(Calendar.MONTH));
			e.set(Calendar.DATE, s.get(Calendar.DATE));
			double minutes = Math.floor((e.getTimeInMillis() - s.getTimeInMillis())/(60 * 1000));
			if(minutes >0 ) total += minutes*price;
		} 
		return total;
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
	
	private long daysBetween(Calendar startDate, Calendar endDate) {  
		//assert: startDate must be before endDate  
		Calendar date = (Calendar) startDate.clone();
		date.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, endDate.get(Calendar.MINUTE));
		date.set(Calendar.MINUTE, endDate.get(Calendar.MINUTE));
		date.set(Calendar.MILLISECOND, endDate.get(Calendar.MILLISECOND));
		long daysBetween = 0;  
		while (date.before(endDate)) {  
			date.add(Calendar.DAY_OF_MONTH, 1);  
			daysBetween++;  
		}  
		return daysBetween;  
	}  
	
	
}
