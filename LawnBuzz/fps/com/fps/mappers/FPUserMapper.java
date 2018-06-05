package com.fps.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.fps.models.Response;
import com.fps.models.User;

public interface FPUserMapper {
	
	@Insert("INSERT INTO  fps.user (firstname,lastname,phone,dob) VALUES "
			+ "(#{user.firstname},#{user.lastname},#{user.phone},#{user.birth})")
	public Response registerFPUser(
			@Param("user") User user);


}
