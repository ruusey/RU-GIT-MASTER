package com.fps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.fps.constants.Role;

public class Util {

	public static Date stringToDate(String dateString) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
		    return df.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return null;
	}
	public static String generateUserGUID() {
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
	}
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
	public static Date parseDateSimple(String date){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
		Role[] roles = Role.values();
		for(int x = 0; x<roles.length;x++){
			if(x==roles.length-1){
				result+=roles[x].name()+"'";
			}else{
				result+=roles[x].name()+"','";
			}
		}
		return result+");";
	}
}
