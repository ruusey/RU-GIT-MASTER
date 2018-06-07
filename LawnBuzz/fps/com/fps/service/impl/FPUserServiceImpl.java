package com.fps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fps.mappers.FPUserMapper;
import com.fps.models.Response;
import com.fps.models.User;
import com.fps.service.FPUserService;

@Service("fpUserService")
public class FPUserServiceImpl implements FPUserService {
	@Autowired
	private FPUserMapper mapper;

	@Override
	public int registerUser(User user) {
		int id = -1;
		try {
			
			id= mapper.registerFPUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return id;

	}

	@Override
	public User getUserById(int id) {
		User res = null;
		try {
			res = mapper.getFpUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<User> getAllUsers() {
		return mapper.getFpUsersAll();
	}

	@Override
	public Response deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
