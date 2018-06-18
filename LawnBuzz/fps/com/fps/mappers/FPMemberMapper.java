package com.fps.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.fps.models.Member;

public interface FPMemberMapper {

	@Insert("INSERT INTO user (firstname,lastname,dob,phone,email,verified,username,identifier,score) VALUES "
			+ "(#{user.firstname},#{user.lastname},#{user.dob},#{user.phone},#{user.email},#{user.verified},#{user.username},#{user.identifier},#{user.score})")
	@SelectKey(statement="SELECT LAST_INSERT_ID();", keyProperty="id", before=false, resultType=int.class)
	public int addMember(@Param("user") Member user);

	@Select("SELECT * FROM user WHERE id=#{user_id} AND deleted='0'")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "firstname", javaType = String.class, column = "firstname"),
			@Result(property = "lastname", javaType = String.class, column = "lastname"),
			@Result(property = "dob", javaType = Date.class, jdbcType=JdbcType.DATE, column = "dob"),
			@Result(property = "phone", javaType = String.class, column = "phone"),
			@Result(property = "email", javaType = String.class, column = "email"),
			@Result(property = "verified", javaType = Boolean.class, column = "verified"),
			@Result(property = "username", javaType = String.class, column = "username"),
			@Result(property = "identifier", javaType = String.class, column = "identifier"),
			@Result(property = "score", javaType = Double.class, column = "score")

	})
	public Member getMemberById(@Param("user_id") int id);
	
	@Select("SELECT * FROM user WHERE deleted='0'")
	@Results(value = { @Result(property = "id", javaType = Integer.class, column = "id"),
			@Result(property = "firstname", javaType = String.class, column = "firstname"),
			@Result(property = "lastname", javaType = String.class, column = "lastname"),
			@Result(property = "dob", javaType = Date.class, jdbcType=JdbcType.DATE, column = "dob"),
			@Result(property = "phone", javaType = String.class, column = "phone"),
			@Result(property = "email", javaType = String.class, column = "email"),
			@Result(property = "verified", javaType = Boolean.class, column = "verified"),
			@Result(property = "username", javaType = String.class, column = "username"),
			@Result(property = "identifier", javaType = String.class, column = "identifier"),
			@Result(property = "score", javaType = Double.class, column = "score")

	})
	public List<Member> getAllMembers();
	
	
	@Update("UPDATE user SET deleted=#{deleted} WHERE id=#{user_id}")
	public int updateMemberDeleted(@Param("user_id") int userId, @Param("deleted") boolean deleted);

	@Update("UPDATE user SET firstname=#{firstname} WHERE id=#{user_id}")
	public int updateMemberFirstname(@Param("user_id") int userId, @Param("firstname") String firstname);
	
	@Update("UPDATE user SET lastname=#{lastname} WHERE id=#{user_id}")
	public int updateMemberLastname(@Param("user_id") int userId, @Param("lastname") String lastname);
	
	@Update("UPDATE user SET dob=#{dob} WHERE id=#{user_id}")
	public int updateMemberDOB(@Param("user_id") int userId, @Param("dob") Date dob);
	
	@Update("UPDATE user SET phone=#{phone} WHERE id=#{user_id}")
	public int updateMemberPhone(@Param("user_id") int userId, @Param("phone") String phone);
	
	@Update("UPDATE user SET email=#{email} WHERE id=#{user_id}")
	public int updateMemberEmail(@Param("user_id") int userId, @Param("email") String email);
	
	@Update("UPDATE user SET verified=#{verified} WHERE id=#{user_id}")
	public int updateMemberVerified(@Param("user_id") int userId, @Param("verified") boolean verified);

	@Update("UPDATE user SET username=#{username} WHERE id=#{user_id}")
	public int updateMemberUsername(@Param("user_id") int userId, @Param("username") String username);
	
	@Update("UPDATE user SET identifier=#{identifier} WHERE id=#{user_id}")
	public int updateMemberIdentifier(@Param("user_id") int userId, @Param("identifier") String identifier);
	
	@Update("UPDATE user SET score=#{score} WHERE id=#{user_id}")
	public int updateMemberScore(@Param("user_id") int userId, @Param("score") double score);
}
