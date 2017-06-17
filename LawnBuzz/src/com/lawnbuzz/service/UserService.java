package com.lawnbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawnbuzz.mappers.UserMapper;
import com.lawnbuzz.models.User;

public interface UserService {
	List<User> getAllUsers();
	void addUser(User user);
	void updateUser(User user);
}
