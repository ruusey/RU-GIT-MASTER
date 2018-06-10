package com.fps.service;

import java.util.Date;
import java.util.List;

import com.fps.models.Response;
import com.fps.models.Member;

public interface FPMemberService {
	public int addMember(Member user);

	public Member getMemberById(int userId);

	public List<Member> getAllMembers();

	public int updateMemberFirstName(int userId, String firstname);

	public int updateMemberLastName(int userId, String lastname);

	public int updateMemberDOB(int userId, Date dob);

	public int updateMemberPhone(int userId, String phone);

	public int updateMemberEmail(int userId, String email);

	public int updateMemberVerified(int userId, boolean verified);

	public int updateMemberUsername(int userId, String username);

	public int updateMemberScore(int userId, double score);

	public int updateMemberDeleted(int userId, boolean deleted);

}
