package com.fps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
