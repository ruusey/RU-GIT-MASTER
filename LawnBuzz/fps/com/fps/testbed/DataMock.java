package com.fps.testbed;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.fps.models.Member;
import com.fps.util.Util;

public class DataMock {

	public DataMock() {
		// TODO Auto-generated constructor stub
	}
	public static ArrayList<Member> generateRandomUsers() throws IOException{
		ArrayList<Member> users = new ArrayList<Member>();
		Random r = new Random(System.currentTimeMillis());
		Reader in = new FileReader("resources/data.csv");
		
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("first", "last", "dob","phone","email","verified").parse(in);
		int itr = 0;
		for (CSVRecord record : records) {
			if(itr==0) {
				itr++;
				continue;
			}
			String firstName = record.get("first");
		    String lastName = record.get("last");
		    String username = firstName.substring(0,1)+lastName;
		    String identifier = Util.generateUserGUID();
		    double score = 0.0 + (99.9 - 0.0) * r.nextDouble();
		    Date dob = Util.stringToDate(record.get("dob"));
		    String phone = record.get("phone");
		    String email = record.get("email");
		    boolean verified = Boolean.parseBoolean(record.get("verified"));
		    int id = r.nextInt(500);
		    
		    Member user = new Member(id,firstName,lastName,phone,email,dob,verified,username,identifier,score);
		    users.add(user);
		    
		}
		return users;
	}

}
