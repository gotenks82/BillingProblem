package Billing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Billing {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    Calendar startDate = Calendar.getInstance(); 
		Calendar endDate = Calendar.getInstance();

		try {
			startDate.setTime(dateFormat.parse("2014-06-10  17:55:00"));
			endDate.setTime(dateFormat.parse("2014-06-10  18:05:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(getTotalAmount(startDate, endDate));

	}
	
	public static double getTotalAmount(Calendar start, Calendar end) {
		
		double result = 0.0;
		if(start!=null && end!= null) {
			BillingConfiguration bConfiguration = new BillingConfiguration();
			result = bConfiguration.getTotal(start, end);
		}		
		return result;
	}

}
