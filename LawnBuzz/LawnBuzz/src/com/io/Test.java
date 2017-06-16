package com.io;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.lawnbuzz.models.User;
import com.lawnbuzz.serviceimpl.UserServiceImpl;

public class Test {
	public static void main(String[] args){
		//LOAD THE SPRING CONFIG FILE
		ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		//SELECT THE DEFINED USER SERVICE FROM SERVICEIMPL
	    UserServiceImpl service =  (UserServiceImpl)cxt.getBean("userService");
		User ins = new User();
		ins.setId(2);
		ins.setFirstName("TestFirst");
		ins.setLastName("TestLast");
		ins.setEmail("donovanaaronsmgmt@gmail.com");
		
		
		
		service.updateUser(ins);
		List<User> test = service.getAllUsers();
		System.out.println(test.get(1).getEmail());
		
	}
}
