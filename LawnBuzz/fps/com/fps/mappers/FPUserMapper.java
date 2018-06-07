package com.fps.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.type.JdbcType;

import com.fps.models.Response;
import com.fps.models.User;

public interface FPUserMapper {

	@Insert("INSERT INTO user (firstname,lastname,dob,phone,email,verified) VALUES "
			+ "(#{user.firstname},#{user.lastname},#{user.dob},#{user.phone},#{user.email},#{user.verified})")
	@SelectKey(statement="SELECT LAST_INSERT_ID();", keyProperty="id", before=false, resultType=int.class)
	public int registerFPUser(@Param("user") User user);

	@Select("SELECT * FROM user WHERE id=#{id}")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "firstname", javaType = String.class, column = "firstname"),
			@Result(property = "lastname", javaType = String.class, column = "lastname"),
			@Result(property = "dob", javaType = Date.class, jdbcType=JdbcType.DATE, column = "dob"),
			@Result(property = "phone", javaType = String.class, column = "phone"),
			@Result(property = "email", javaType = String.class, column = "email"),
			@Result(property = "verified", javaType = Boolean.class, column = "verified")

	})
	public User getFpUserById(@Param("id") int id);
	
	@Select("SELECT * FROM user")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "firstname", javaType = String.class, column = "firstname"),
			@Result(property = "lastname", javaType = String.class, column = "lastname"),
			@Result(property = "dob", javaType = Date.class, jdbcType=JdbcType.DATE, column = "dob"),
			@Result(property = "phone", javaType = String.class, column = "phone"),
			@Result(property = "email", javaType = String.class, column = "email"),
			@Result(property = "verified", javaType = Boolean.class, column = "verified")

	})
	public List<User> getFpUsersAll();
}
