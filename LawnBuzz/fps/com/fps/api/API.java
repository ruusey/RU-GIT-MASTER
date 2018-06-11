package com.fps.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Service;

import com.fps.models.Event;
import com.fps.models.Member;
import com.lawnbuzz.dao.LawnBuzzDao;
import com.lawnbuzz.rest.Response;

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
	@Path("/member")
	@Produces("application/json")
	public Member getMemberById(@Context HttpServletRequest request, @QueryParam("id") int id) {
		return FPSDao.fpMemberService.getMemberById(id);
	}
	@GET
	@Path("/event")
	@Produces("application/json")
	public Event getEeventById(@Context HttpServletRequest request, @QueryParam("id") int id) {
		return FPSDao.fpEventService.getEventById(id);
	}
	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public void registerFpUser(@Context HttpServletRequest request, Member user) {
		System.out.println("here");
		LawnBuzzDao.fpUserService.addMember(user);
		
				
		
	}
	
}
