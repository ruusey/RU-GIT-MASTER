package com.lawnbuzz.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.lawnbuzz.models.Service;

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
	public static String getTimeSince(long start){
		return (System.currentTimeMillis()-start)+"ms";
	}
	public static String getJobEnumSQLString(){
		String result = "ENUM('";
		Service[] services = Service.values();
		for(int x = 0; x<services.length;x++){
			if(x==services.length-1){
				result+=services[x].name()+"'";
			}else{
				result+=services[x].name()+"','";
			}
		}
		return result+");";
	}
}
