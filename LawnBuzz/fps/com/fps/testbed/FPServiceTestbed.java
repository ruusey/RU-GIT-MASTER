package com.fps.testbed;

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
		System.out.println();
		testGetFpUserById(1);
		cxt.close();
	}
	public R  testGetFpUserById(int id) {
		LOGGER.info("Testing getFpUserById");
		User result = fpUserService.getFpUserById(1);
		if(result!=null) {
			LOGGER.info("Successfully Retrieved User!");
			LOGGER.info(gen.serialize(result));
		}else {
			LOGGER.error("Error Retrieving User!");
			LOGGER.error(gen.serialize(result));
		}
	}

}
