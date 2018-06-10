package com.fps.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fps.mappers.FPMemberMapper;
import com.fps.models.Member;
import com.fps.service.FPMemberService;

@Service("fpMemberService")
public class FPMemberServiceImpl implements FPMemberService {
	@Autowired
	private FPMemberMapper mapper;

	@Override
	public int addMember(Member user) {
		return mapper.addMember(user);
	}

	@Override
	public Member getMemberById(int userId) {
		return mapper.getMemberById(userId);
	}

	@Override
	public List<Member> getAllMembers() {
		return mapper.getAllMembers();
	}

	@Override
	public int updateMemberFirstName(int userId, String firstname) {
		return mapper.updateMemberFirstname(userId, firstname);
	}

	@Override
	public int updateMemberLastName(int userId, String lastname) {
		return mapper.updateMemberLastname(userId, lastname);
	}

	@Override
	public int updateMemberDOB(int userId, Date dob) {
		return mapper.updateMemberDOB(userId, dob);
	}

	@Override
	public int updateMemberPhone(int userId, String phone) {
		return mapper.updateMemberPhone(userId, phone);
	}

	@Override
	public int updateMemberEmail(int userId, String email) {
		return mapper.updateMemberEmail(userId, email);
	}

	@Override
	public int updateMemberVerified(int userId, boolean verified) {
		return mapper.updateMemberVerified(userId, verified);
	}

	@Override
	public int updateMemberUsername(int userId, String username) {
		return mapper.updateMemberUsername(userId, username);
	}

	@Override
	public int updateMemberScore(int userId, double score) {
		return mapper.updateMemberScore(userId, score);
	}

	@Override
	public int updateMemberDeleted(int userId, boolean deleted) {
		return mapper.updateMemberDeleted(userId, deleted);
	}

	

	

}
