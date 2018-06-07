package com.fps.testbed;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fps.models.User;
import com.fps.service.impl.FPUserServiceImpl;
import com.lawnbuzz.dao.LawnBuzzDao;
import com.lawnbuzz.util.Util;
import com.owlike.genson.Genson;

public class FPServiceTestbed {
	public static FPUserServiceImpl fpUserService;
	public static Genson gen = new Genson();
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
		LOGGER.info("Successfully Created FPS DAO");
		
		//*************
		//TESTING
		//*************
		
		//testGetFpUserById(1);
		//testRegisterFpUser();
		testGetAllFpUsers();
		cxt.close();
	}
	public static void  testGetFpUserById(int id) {
		LOGGER.info("Testing getFpUserById");
		User result = fpUserService.getUserById(1);
		LOGGER.info(gen.serialize(result));
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
		try {
			users = DataMock.generateRandomUsers();
			for(User user:fpUserService.getAllUsers()) {
				//int id = fpUserService.registerUser(user);
				LOGGER.info("Found Existing User - "+gen.serialize(user));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
