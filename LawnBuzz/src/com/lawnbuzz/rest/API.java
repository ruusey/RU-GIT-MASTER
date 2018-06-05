package com.lawnbuzz.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Service;

import com.fps.models.User;
import com.lawnbuzz.dao.LawnBuzzDao;
import com.lawnbuzz.models.JobRequest;
import com.lawnbuzz.models.ServiceProvider;

@Service("api")
@Path("/")
public class API {
	
	@GET
	@Path("/ping")
	@Produces("application/json")
	public Response ping(@Context HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(Calendar.getInstance().getTime());
		return new Response(true,ip+" received a response from the API at "+timeStamp);
	}
	@POST
	@Path("/registerFpUser")
	@Produces("application/json")
	public void registerFpUser(@Context HttpServletRequest request, User user) {
		System.out.println("here");
		LawnBuzzDao.fpUserService.registerFPUser(user);
		
				
		
	}
	@GET
	@Path("/registerFpUser")
	@Produces("application/json")
	public void registerFpUserTest(@Context HttpServletRequest request, User user) {
		System.out.println("here");
		LawnBuzzDao.fpUserService.registerFPUser(user);
		
				
		
	}
	@POST
	@Path("/registerClient")
	@Produces("application/json")
	public Response registerClient(@Context HttpServletRequest request, @DefaultValue("10") @QueryParam("radius") int radius) {
		Test.testSPRegister();
		
			return new Response();	
		
	}
	@GET
	@Path("/getjobs")
	@Produces("application/json")
	public List<JobRequest> getJobs(@Context HttpServletRequest request, @DefaultValue("10") @QueryParam("radius") int radius) {
		ServiceProvider logged = LawnBuzzDao.serviceProviderService.getServiceProviderById(1);
		return LawnBuzzDao.jobService.getJobsInRadius(logged.getLoc(), radius);
				
		
	}
	@GET
	@Path("/getuser")
	@Produces("application/json")
	public ServiceProvider getUser(@Context HttpServletRequest request,   @QueryParam("id") int id) {
		//ServiceProvider logged = LawnBuzzDao.serviceProviderService.getServiceProviderById(1);
		return LawnBuzzDao.serviceProviderService.getServiceProviderById(id);
				
		
	}
	@GET
	@Path("/getjob")
	@Produces("application/json")
	public JobRequest getJob(@Context HttpServletRequest request,  @QueryParam("id") int id) {
		//ServiceProvider logged = LawnBuzzDao.serviceProviderService.getServiceProviderById(1);
		return LawnBuzzDao.jobService.getJobById(id);
				
		
	}
	
}
