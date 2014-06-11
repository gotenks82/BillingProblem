package Billing;

import java.util.ArrayList;
import java.util.Calendar;

public class BillingConfiguration {
	private ArrayList<TimeSlice> timeSlices;
	
	public BillingConfiguration() {
		timeSlices = new ArrayList<TimeSlice>();
		timeSlices.add(new TimeSlice(0, 6, 1));
		timeSlices.add(new TimeSlice(6, 12, 2));
		timeSlices.add(new TimeSlice(12, 18, 4));
		timeSlices.add(new TimeSlice(18, 24, 3));
	}
	
	public double getTotal(Calendar start, Calendar end) {
		double result = 0.0;
		for (TimeSlice timeSlice : timeSlices) {
			result += timeSlice.getTotal(start, end);
		}
		return result;
	}
	

}
