package com.fps.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fps.constants.Role;
import com.fps.models.Calendar;
import com.fps.models.Event;
import com.fps.service.FPCalendarService;
@Service("fpCalendarService")
public class FPCalendarServiceImpl implements FPCalendarService{

	@Override
	public int addCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Calendar getCalendar(int calendarId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Calendar> getAllCalendars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addCalendarMember(int calendarId, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addCalendarMemberRole(int calendarId, int userId, Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCalendarMember(int calendarId, int userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCalendarMemberRole(int calendarId, int userId, Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addCalendarEvent(int calendarId, Event event) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addCalendarEvent(int calendarId, int eventId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCalendarEvent(int calendarId, Event event) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCalendarEvent(int calendarId, int eventId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
