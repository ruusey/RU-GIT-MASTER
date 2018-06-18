package com.fps.api;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fps.service.impl.FPCalendarServiceImpl;
import com.fps.service.impl.FPEventServiceImpl;
import com.fps.service.impl.FPMemberServiceImpl;
import com.fps.util.Util;
import com.google.gson.Gson;

@Component
public class FPSDao {
	public static FPMemberServiceImpl fpMemberService;
	public static FPEventServiceImpl fpEventService;
	public static FPCalendarServiceImpl fpCalendarService;
	static Gson gson = new Gson();  
	static Logger LOGGER = Logger.getLogger(FPSDao.class.getName());
	static {
		long startTime = System.currentTimeMillis();
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		LOGGER.info(
				"Initialized FPS class " + cxt.getApplicationName() + " in " + Util.getTimeSince(startTime));
		LOGGER.info("Registering FPS services...");
		startTime = System.currentTimeMillis();
		fpMemberService = (FPMemberServiceImpl) cxt.getBean("fpMemberService");
		LOGGER.info("Successfully registered fpUserService in " + Util.getTimeSince(startTime));
		
		
		
		startTime = System.currentTimeMillis();
		fpEventService = (FPEventServiceImpl) cxt.getBean("fpEventService");
		LOGGER.info("Successfully registered fpEventService in " + Util.getTimeSince(startTime));
		
		startTime = System.currentTimeMillis();
		fpCalendarService = (FPCalendarServiceImpl) cxt.getBean("fpCalendarService");
		LOGGER.info("Successfully registered fpCalendarService in " + Util.getTimeSince(startTime));
		
		LOGGER.info("Successfully Initialized FPS DAO");
		cxt.close();
	}

}
