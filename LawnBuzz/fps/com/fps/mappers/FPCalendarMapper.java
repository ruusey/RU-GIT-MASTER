package com.fps.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.fps.constants.Role;
import com.fps.models.Calendar;

public interface FPCalendarMapper {
	// ********************************
	// SELECT STATEMENTS
	// ********************************
	@Select("SELECT * FROM calendar WHERE id=#{calendar_id} AND deleted='0'")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "title", javaType = String.class, column = "title"),
			@Result(property = "created", javaType = Date.class, jdbcType = JdbcType.DATE, column = "created"),
			@Result(property = "label", javaType = String.class, column = "label"),
			@Result(property = "deleted", javaType = Boolean.class, column = "deleted")

	})
	public Calendar getCalendarById(@Param("calendar_id") int calendarId);
	
	@Select("SELECT * FROM calendar WHERE deleted='0'")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "title", javaType = String.class, column = "title"),
			@Result(property = "created", javaType = Date.class, jdbcType = JdbcType.DATE, column = "created"),
			@Result(property = "label", javaType = String.class, column = "label"),
			@Result(property = "deleted", javaType = Boolean.class, column = "deleted")

	})
	public List<Calendar> getAllCalendars();

	@Select("SELECT event_id FROM calendar_event WHERE calendar_id=#{calendar_id}")
	@Results(value = { @Result(property = "event_id", javaType = Integer.class, column = "event_id") })
	public List<Integer> getCalendarEventsById(@Param("calendar_id") int calendarId);

	@Select("SELECT user_id FROM calendar_member WHERE calendar_id=#{calendar_id}")
	@Results(value = { @Result(property = "user_id", javaType = Integer.class, column = "user_id") })
	public List<Integer> getCalendarMembersById(@Param("calendar_id") int calendarId);
	
	@Select("SELECT role FROM calendar_member_role WHERE calendar_id=#{calendar_id} AND user_id=#{user_id}")
	@Results(value = { @Result(property = "role", javaType = Role.class, column = "role") })
	public List<Role> getCalendarMembersRoleById(@Param("calendar_id") int calendarId,@Param("user_id") int userId);

	// ********************************
	// INSERT STATEMENTS
	// ********************************
	@Insert("INSERT INTO calendar_member (calendar_id,user_id) VALUES (" + "#{calendar_id},#{user_id})")
	public int addCalendarMember(@Param("calendar_id") int calendarId, @Param("user_id") int userId);
	
	@Insert("INSERT INTO calendar_member_role (calendar_id,user_id,role) VALUES (" + "#{calendar_id},#{user_id},#{role})")
	public int addCalendarMemberRole(@Param("calendar_id") int calendarId, @Param("user_id") int userId,@Param("role") Role role);

	@Insert("INSERT INTO calendar_event (calendar_id,event_id) VALUES (" + "#{calendar_id},#{event_id})")
	public int addCalendarEvent(@Param("calendar_id") int calendarId, @Param("event_id") int eventId);
	
	@Insert("INSERT INTO calendar (title,label,created) VALUES (" + "#{calendar.title},#{calendar.label},#{calendar.created})")
	public int addCalendar(@Param("calendar") Calendar calendar);
	
	//**********************************
	//DELETE STATEMENTS
	//**********************************
	
	
	@Delete("DELETE FROM calendar_event WHERE calendar_id=#{calendar_id} AND event_id=#{event_id}")
	public int removeCalendarEvent(@Param("calendar_id") int calendarId, @Param("event_id") int eventId);
	
	@Delete("DELETE FROM calendar_event WHERE calendar_id=#{calendar_id} AND user_id=#{user_id}")
	public int removeCalendarMember(@Param("calendar_id") int calendarId, @Param("user_id") int userId);
	
	@Delete("DELETE FROM calendar_member_role WHERE calendar_id=#{calendar_id } AND user_id=#{user_id} AND role=#{role}")
	public int removeCalendarMemberRole(@Param("calendar_id") int calendarId, @Param("user_id") int userId,@Param("role") Role role);

}
