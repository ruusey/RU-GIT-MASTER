package com.lawnbuzz.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
	
	public static boolean isDateBefore(String reference, String dateToCheck){
		Date d1 = parseDate(reference);
		Date d2 = parseDate(dateToCheck);
		return d2.after(d1);	
	}
	public static Date parseDate(String date){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		Date d = null;
		try {
		  d = formatter.parse(date);
		} catch (ParseException e) {
		  e.printStackTrace();
		}
		return d;
	}
	public static String getCurrentDateTime(){
		return new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(Calendar.getInstance().getTime());
	}
}
