package com.fps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fps.constants.Role;
import com.fps.mappers.FPCalendarMapper;
import com.fps.models.Calendar;
import com.fps.service.FPCalendarService;

@Service("fpCalendarService")
public class FPCalendarServiceImpl implements FPCalendarService {
	@Autowired
	private FPCalendarMapper mapper;

	@Override
	public int addCalendar(Calendar calendar) {
		return mapper.addCalendar(calendar);
	}

	@Override
	public Calendar getCalendarById(int calendarId) {
		return mapper.getCalendarById(calendarId);
	}

	@Override
	public List<Calendar> getAllCalendars() {
		return mapper.getAllCalendars();
	}

	@Override
	public int addCalendarMember(int calendarId, int userId) {
		return mapper.addCalendarMember(calendarId, userId);
	}

	@Override
	public int removeCalendarMember(int calendarId, int userId) {
		return mapper.removeCalendarMember(calendarId, userId);
	}

	@Override
	public int addCalendarEvent(int calendarId, int eventId) {
		return mapper.addCalendarEvent(calendarId, eventId);
	}

	@Override
	public int removeCalendarEvent(int calendarId, int eventId) {
		return mapper.removeCalendarEvent(calendarId, eventId);
	}

	@Override
	public List<Integer> getCalendarEventsById(int calendarId) {
		return mapper.getCalendarEventsById(calendarId);
	}

	@Override
	public List<Integer> getCalendarMembersById(int calendarId) {
		return mapper.getCalendarMembersById(calendarId);
	}

	@Override
	public int addCalendarMemberRole(int calendarId, int userId, Role role) {
		return mapper.addCalendarMemberRole(calendarId, userId, role);
	}

	@Override
	public int removeCalendarMemberRole(int calendarId, int userId, Role role) {
		return mapper.removeCalendarMemberRole(calendarId, userId, role);
	}

	@Override
	public List<Role> getCalendarMembersRoleById(int calendarId, int userId) {
		return mapper.getCalendarMembersRoleById(calendarId, userId);
	}

}
