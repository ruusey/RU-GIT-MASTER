package com.io;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.lawnbuzz.models.GeoLocation;
import com.lawnbuzz.models.ServiceProvider;
import com.lawnbuzz.models.User;
import com.lawnbuzz.serviceimpl.ServiceProviderServiceImpl;
import com.lawnbuzz.serviceimpl.UserServiceImpl;

public class Test {
	public static void main(String[] args){
		//LOAD THE SPRING CONFIG FILE
		ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		//SELECT THE DEFINED USER SERVICE FROM SERVICEIMPL
	    //UserServiceImpl service =  (UserServiceImpl)cxt.getBean("userService");
	    
	    ServiceProviderServiceImpl service =  (ServiceProviderServiceImpl)cxt.getBean("serviceProviderService");
	    
	    ServiceProvider sp = new ServiceProvider();
	    sp.setEmail("ruusey@gmail.com");
	    sp.setUserName("ruusey");
	    sp.setFirstName("Robert");
	    sp.setLastName("Usey");
	    List<com.lawnbuzz.models.Service> sList = new ArrayList<com.lawnbuzz.models.Service>();
	    sList.add(com.lawnbuzz.models.Service.LAWN_CARE);
	    sList.add(com.lawnbuzz.models.Service.HOME_CARE);
	    GeoLocation geo = new GeoLocation(33.7490,84.3880,com.lawnbuzz.util.Util.getCurrentDateTime());
	    
	    
	    sp.setServices(sList);
	    sp.setLoc(geo);
	    sp.setRating(0);
	    
	    service.registerServiceProvider(sp);
	    
	    List<ServiceProvider> test = service.getServiceProviders();
	    System.out.println(test.hashCode());
//		User ins = new User();
//		ins.setId(2);
//		ins.setFirstName("TestFirst");
//		ins.setLastName("TestLast");
//		ins.setEmail("thisisatest@gmail.com");
//		
//		
//		
//		service.updateUser(ins);
//		List<User> test = service.getAllUsers();
//		System.out.println(test.get(1).getEmail());
		
	}
}
