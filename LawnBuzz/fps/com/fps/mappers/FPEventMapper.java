package com.fps.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

import com.fps.constants.EventType;
import com.fps.constants.Outcome;
import com.fps.constants.Privledge;
import com.fps.constants.Requirement;
import com.fps.constants.Role;
import com.fps.models.Event;
import com.fps.models.User;

public interface FPEventMapper {
	// ********************************
	// INSERT EVENT MAPPINGS
	// ********************************
	@Insert("INSERT INTO member_event (date,type) VALUES " + "#{event.date},#{event.type})")
	@SelectKey(statement = "SELECT LAST_INSERT_ID();", keyProperty = "event_id", before = false, resultType = int.class)
	public int createEvent(@Param("event") Event event);

	@Insert("INSERT INTO member_event_outcome (user_id,event_id,outcome) VALUES "
			+ "#{user_id},#{event_id},#{outcome})")
	public void createEventOutcome(@Param("user_id") int userId, @Param("event_id") int eventId,
			@Param("outcome") Outcome outcome);

	@Insert("INSERT INTO member_event_requirement (user_id,event_id,requirement) VALUES "
			+ "#{user_id},#{event_id},#{outcome})")
	public void createEventRequirement(@Param("user_id") int userId, @Param("event_id") int eventId,
			@Param("requirement") Requirement requirement);

	@Insert("INSERT INTO member_event_role (user_id,event_id,role) VALUES " + "#{user_id},#{event_id},#{role})")
	public void createEventRole(@Param("user_id") int userId, @Param("event_id") int eventId, @Param("role") Role role);

	// ********************************
	// SELECT EVENT MAPPINGS
	// ********************************
	@Select("SELECT * FROM member_event WHERE event_id=#{event_id}")
	@Results(value = { @Result(property = "eventId", javaType = Integer.class, column = "event_id"),
			@Result(property = "date", javaType = Date.class,jdbcType = JdbcType.DATE, column = "date"),
			@Result(property = "completed", javaType = Boolean.class, column = "completed"),
			@Result(property = "type", javaType = EventType.class, column = "type"),
			@Result(property = "memberIds", javaType = List.class, column = "event_id", many = @Many(select = "getUsersByEvent")),
			@Result(property = "memberRequirements", javaType = Boolean.class, column = "completed"),
			@Result(property = "memberOutcomes", javaType = Boolean.class, column = "verified")

	})
	public Event getEventById(@Param("event_id") int eventId);

	@Select("SELECT user_id FROM member_event_map WHERE event_id=#{event_id}")
	@Results(id="user_id",value = { @Result(property = "user_id", javaType = Integer.class, column = "user_id",id=true)
			
	})
	public List<Integer> getUsersByEvent(@Param("event_id") int eventId);
	
	@Select("SELECT requirement FROM member_event_req WHERE event_id=#{event_id} AND user_id=#{user_id}")
	@Results(id="requirement",value = { @Result(property = "requirement", javaType = Requirement.class, column = "requirement",id=true)
			
	})
	public List<Requirement> getUserEventRequirements(@Param("event_id") int eventId,@Param("user_id") int userId);
	
}
