package com.fps.service;

import java.util.List;

import com.fps.constants.Role;
import com.fps.models.Calendar;
import com.fps.models.Event;

public interface FPCalendarService {
	public int addCalendar(Calendar calendar);
	public Calendar getCalendar(int calendarId);
	public List<Calendar> getAllCalendars();
	
	
	public int addCalendarMember(int calendarId,int userId);
	public int addCalendarMemberRole(int calendarId,int userId, Role role);
	
	public int removeCalendarMember(int calendarId,int userId);
	public int removeCalendarMemberRole(int calendarId,int userId,Role role);
	
	public int addCalendarEvent(int calendarId, Event event);
	public int addCalendarEvent(int calendarId, int eventId);
	
	public int removeCalendarEvent(int calendarId, Event event);
	public int removeCalendarEvent(int calendarId, int eventId);
		
}
