package com.fps.mappers;

import java.sql.Date;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.fps.models.Response;
import com.fps.models.User;

public interface FPUserMapper {
	
	@Insert("INSERT INTO  fps.user (firstname,lastname,phone,dob) VALUES "
			+ "(#{user.firstname},#{user.lastname},#{user.phone},#{user.birth})")
	public Response registerFPUser(
			@Param("user") User user);

	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "firstname", column = "firstname"),
			@Result(property = "lastname", column = "lastname"),
			@Result(property = "phone", column = "phone"),
			@Result(property = "email", column = "email"),
			@Result(property = "verified", column = "verified")
			
	 })
	@Select("SELECT * FROM fps.user WHERE id=#{id}")
	
	public User getFpUserById(@Param("id") int id);
}
