package com.fps.testbed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fps.constants.Outcome;
import com.fps.constants.Requirement;
import com.fps.constants.Role;
import com.fps.models.Event;
import com.fps.models.Member;
import com.fps.service.impl.FPCalendarServiceImpl;
import com.fps.service.impl.FPEventServiceImpl;
import com.fps.service.impl.FPMemberServiceImpl;
import com.fps.util.Util;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

public class FPServiceTestbed {
	public static FPMemberServiceImpl fpMemberService;
	public static FPEventServiceImpl fpEventService;
	public static FPCalendarServiceImpl fpCalendarService;
	static Gson gson = new Gson();  
	static Logger LOGGER = Logger.getLogger(FPServiceTestbed.class.getName());
	public static void main(String[] args) {
		
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
		
		//*************
		//TESTING
		//*************
		//testGetEventById(1);
		//testGetFpUserById(2);
		//fpCalendarService.addCalendarMemberRole(1, 1, Role.Admin);
		display(fpCalendarService.getCalendarById(1));
		List<Integer> events = fpCalendarService.getCalendarEventsById(1);
		for(int i : events) {
			Event e = fpEventService.getEventById(i);
			display(e);
			display(fpMemberService.getMemberById(e.getMemberIds().get(0)));
		}
		
		
		//testRegisterFpUser();
		//testGetAllFpUsers();
		//testGetUserIdsByEvent(1);
		//testGetUserEventRequirements(1,1);
		//testAddUserEventRequirement(1,1,Requirement.CompleteByDeadline);
		//testGetUserEventRequirements(1,1);
		//testGetUserEventOutcomes(1,1);
		cxt.close();
	}
	
public static void testGetEventById(int id) {
		
		Event result = fpEventService.getEventById(id);
		display(result);
		for(int i : result.getMemberIds()) {
			testGetFpUserById(i);
			testGetUserEventRequirements(result.getEventId(),i);
			testRemoveUserEventRequirement(result.getEventId(),i,Requirement.ArriveOnTime);
			testGetUserEventRequirements(result.getEventId(),i);
		}
	}
	public static void testGetFpUserById(int id) {
		
		Member result = fpMemberService.getMemberById(id);
		display(result);
	}
	public static void testRegisterFpUser() {
		ArrayList<Member> users=null;
		try {
			users = DataMock.generateRandomUsers();
			for(Member user:users) {
				int id = fpMemberService.addMember(user);
				//LOGGER.info("Added User("+id+") " + gen.serialize(user));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testGetAllFpUsers() {
		List<Member> users=null;
		//users = DataMock.generateRandomUsers();
		users=fpMemberService.getAllMembers();
		display(users);
	}
	
	public static void testGetUserIdsByEvent(int eventId) {
		List<Integer> ids = fpEventService.getUserIdsByEvent(eventId);
		
		display(ids);
		testGetFpUserById(ids.get(0));
		
	}
	public static void testGetUserEventRequirements(int eventId, int userId) {
		List<Requirement> reqs=fpEventService.getUserEventRequirements(eventId, userId);
		display(reqs);
	}
	public static void testGetUserEventOutcomes(int eventId, int userId) {
		List<Outcome> reqs=fpEventService.getUserEventOutcomes(eventId, userId);
		display(reqs);
	}
	public static void testAddUserEventOutcome(int eventId, int userId, Outcome outcome) {
		fpEventService.addEventMemberOutcome(eventId, userId, outcome);
	}
	public static void testAddUserEventRequirement(int eventId, int userId, Requirement requirement) {
		fpEventService.addEventMemberRequirement(eventId, userId, requirement);
	}
	public static void testRemoveUserEventOutcome(int eventId, int userId, Outcome outcome) {
		fpEventService.removeEventMemberOutcome(eventId, userId, outcome);
	}
	public static void testRemoveUserEventRequirement(int eventId, int userId, Requirement requirement) {
		fpEventService.removeEventMemberRequirement(eventId, userId, requirement);
	}
	public static void display(Object o) {
		System.out.println();
		System.out.println("##########################QUERY_RESULT############################");
		System.out.println(gson.toJson(o));
		System.out.println("##################################################################");
		System.out.println();
	}
	

}
