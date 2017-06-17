package com.io;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

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
	@Path("/ping")
	@Produces("application/json")
	public Response getUser(@Context HttpServletRequest request, @QueryParam("id") int id) {
		return null;
				
		
	}
	
}
