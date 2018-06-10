package com.lawnbuzz.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Service;

import com.fps.models.Member;
import com.lawnbuzz.dao.LawnBuzzDao;

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
	@GET
	@Path("/getFpUser")
	@Produces("application/json")
	public Member getFpUserById(@Context HttpServletRequest request, @QueryParam("id") int id) {
		return LawnBuzzDao.fpUserService.getMemberById(id);
	}
	@GET
	@Path("/getAllFpUsers")
	@Produces("application/json")
	public List<Member> getAllFpUsers(@Context HttpServletRequest request) {
		return LawnBuzzDao.fpUserService.getAllMembers();
	}
	@POST
	@Path("/registerFpUser")
	@Produces("application/json")
	public void registerFpUser(@Context HttpServletRequest request, Member user) {
		System.out.println("here");
		LawnBuzzDao.fpUserService.addMember(user);
		
				
		
	}
	
}
