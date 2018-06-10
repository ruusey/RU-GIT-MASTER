package com.fps.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.fps.constants.EventType;
import com.fps.models.Calendar;
import com.fps.models.Event;

public interface FPCalendarMapper {
	@Select("SELECT * FROM calendar WHERE id=#{calendar_id}")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "title", javaType = String.class, column = "title"),
			@Result(property = "created", javaType = Date.class, jdbcType = JdbcType.DATE, column = "created"),
			@Result(property = "label", javaType = String.class, column = "label"),
			@Result(property = "deleted", javaType = Boolean.class, column = "deleted")
			

	})
	public Calendar getCalendarById(@Param("calendar_id") int calendarId);
	
	@Select("SELECT * FROM member_event WHERE id=#{calendar_id}")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "title", javaType = String.class, column = "title"),
			@Result(property = "created", javaType = Date.class, jdbcType = JdbcType.DATE, column = "created"),
			@Result(property = "label", javaType = String.class, column = "label"),
			@Result(property = "deleted", javaType = Boolean.class, column = "deleted")
			

	})
	public List<Event> getCalendarEventsById(@Param("calendar_id") int calendarId);
}
