package com.fps.testbed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fps.constants.Requirement;
import com.fps.models.User;
import com.fps.service.impl.FPEventServiceImpl;
import com.fps.service.impl.FPUserServiceImpl;
import com.lawnbuzz.dao.LawnBuzzDao;
import com.lawnbuzz.util.Util;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

public class FPServiceTestbed {
	public static FPUserServiceImpl fpUserService;
	public static FPEventServiceImpl fpEventService;
	public static Genson gen = new GensonBuilder().setSkipNull(true).create();
	static Logger LOGGER = Logger.getLogger(LawnBuzzDao.class.getName());
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		LOGGER.info(
				"Initialized FPS class " + cxt.getApplicationName() + " in " + Util.getTimeSince(startTime));
		LOGGER.info("Registering FPS services...");
		startTime = System.currentTimeMillis();
		fpUserService = (FPUserServiceImpl) cxt.getBean("fpUserService");
		LOGGER.info("Successfully registered fpUserService in " + Util.getTimeSince(startTime));
		
		
		
		startTime = System.currentTimeMillis();
		fpEventService = (FPEventServiceImpl) cxt.getBean("fpEventService");
		LOGGER.info("Successfully registered fpEventService in " + Util.getTimeSince(startTime));
		LOGGER.info("Successfully Initialized FPS DAO");
		
		//*************
		//TESTING
		//*************
		
		//testGetFpUserById(1);
		//testRegisterFpUser();
		//testGetAllFpUsers();
		//testGetUserIdsByEvent(1);
		testGetUserEventRequirements(1,1);
		cxt.close();
	}
	public static User  testGetFpUserById(int id) {
		LOGGER.info("Testing getFpUserById");
		User result = fpUserService.getUserById(1);
		LOGGER.info(gen.serialize(result));
		return result;
	}
	public static void testRegisterFpUser() {
		ArrayList<User> users=null;
		try {
			users = DataMock.generateRandomUsers();
			for(User user:users) {
				int id = fpUserService.registerUser(user);
				LOGGER.info("Added User("+id+") " + gen.serialize(user));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testGetAllFpUsers() {
		ArrayList<User> users=null;
		//users = DataMock.generateRandomUsers();
		for(User user:fpUserService.getAllUsers()) {
			//int id = fpUserService.registerUser(user);
			LOGGER.info("Found Existing User - "+gen.serialize(user));
		}
	}
	
	public static void testGetUserIdsByEvent(int eventId) {
		List<Integer> ids = fpEventService.getUserIdsByEvent(eventId);
		
		display(ids);
		display(testGetFpUserById(ids.get(0)));
		
	}
	public static void testGetUserEventRequirements(int eventId, int userId) {
		List<Requirement> reqs=fpEventService.getUserEventRequirements(eventId, userId);
		display(reqs);
	}
	
	public static void display(Object o) {
		System.out.println();
		System.out.println("##########################QUERY_RESULT############################");
		System.out.println(gen.serialize(o));
		System.out.println("##################################################################");
		System.out.println();
	}

}
