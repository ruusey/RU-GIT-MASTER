package com.fps.service;

import java.util.List;

import com.fps.models.Response;
import com.fps.models.User;

public interface FPUserService {
	public int registerUser(User user);
	public User getUserById(int id);
	public List<User> getAllUsers();
	public Response deleteUser(int id);
	public Response updateUser(User newUser);
}
