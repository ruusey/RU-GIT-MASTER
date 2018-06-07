package com.fps.service.impl;

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
	public void registerFPUser(User user) {
		Response res = new Response(0, null);
		try {
			mapper.registerFPUser(user);
			res.setMsg("Succesfully added user!");
			res.setStatus(400);
		}catch(Exception e){
			e.printStackTrace();
			res.setMsg("Error adding user!");
			res.setStatus(500);
		}
		
	}

	@Override
	public User getFpUserById(int id) {
		User res = null;
		try {
			res= mapper.getFpUserById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

	

}
