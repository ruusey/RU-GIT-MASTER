package com.fps.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fps.constants.APIStatus;
import com.fps.models.Event;
import com.fps.models.Member;
import com.fps.models.Pong;
import com.fps.util.APIUtils;
import com.fps.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/v1")
@Api(value = "/v1")
public class API {

  @ApiOperation("Handle ping request from client")
  @ApiResponses({@ApiResponse(code = 200, message = "OK: pong", response = Pong.class)})
  @GET
  @Path("/ping")
  @Produces(MediaType.APPLICATION_JSON)
  public Response ping(@Context HttpServletRequest request) {
    String timeStamp =
        new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(Calendar.getInstance().getTime());
    Pong p = new Pong(request.getRemoteAddr(), timeStamp, "pong");
    return Response.ok(p).build();
  }

  @GET
  @Path("/member/{member_id}")
  @Produces("application/json")
  @ApiOperation(
      value = "Find a Member by ID.",
      notes = "Returns the Member associated with the ID passed.",
      response = Member.class)
  @ApiResponses({
    @ApiResponse(code = 200, message = "Member retrieved succesfully.", response = Member.class),
    @ApiResponse(
        code = 400,
        message = "Member not found.",
        response = WebApplicationException.class)
  })
  public Response getMemberById(
      @Context HttpServletRequest request,
      @ApiParam(value = "ID of the Member to be retrieved.", required = true)
          @PathParam("member_id")
          int memberId)
      throws WebApplicationException {
    Member mem = FPSDao.fpMemberService.getMemberById(memberId);
    if (mem != null) {
      return Response.ok("Member retrieved succesfully")
          .entity(FPSDao.fpMemberService.getMemberById(memberId))
          .build();
    } else {
      throw APIUtils.buildWebApplicationException(
          Status.BAD_REQUEST,
          APIStatus.ERROR,
          "Member not found",
          "The Member ID supplied does not match any existing members.");
    }
  }

  @GET
  @Path("/event/{event_id}")
  @Produces("application/json")
  @ApiOperation(
      value = "Find an Event by ID",
      notes = "Returns the Event associated with the ID passed",
      response = Event.class)
  public Response getEventById(
      @Context HttpServletRequest request,
      @ApiParam(value = "ID of the Event to be retrieved", required = true) @PathParam("event_id")
          int eventId) {
    return Response.ok("Event retrieved succesfully")
        .entity(FPSDao.fpEventService.getEventById(eventId))
        .build();
  }

  @GET
  @Path("/event/{event_id}/members")
  @Produces("application/json")
  @ApiOperation(
      value = "List the Members associated with an event",
      notes = "Returns the Member's associated with the Event ID passed",
      response = List.class)
  public Response getEventMembersById(
      @Context HttpServletRequest request,
      @ApiParam(value = "ID of the Event to be retrieved", required = true) @PathParam("event_id")
          int eventId) {
    List<Integer> memberIds = FPSDao.fpEventService.getUserIdsByEvent(eventId);
    List<Member> members = new ArrayList<Member>();
    for (int i : memberIds) {
      members.add(FPSDao.fpMemberService.getMemberById(i));
    }

    return Response.ok("Event Members retrieved succesfully").entity(members).build();
  }

  @GET
  @Path("/calendar/{calendar_id}")
  @Produces("application/json")
  @ApiOperation(
      value = "Find a Calendar by ID",
      notes = "Returns the Calendar associated with the ID passed",
      response = Event.class)
  public Response getCalendarById(
      @Context HttpServletRequest request,
      @ApiParam(value = "ID of the Calendar to be retrieved", required = true)
          @PathParam("calendar_id")
          int calendarId) {
    com.fps.models.Calendar ca = FPSDao.fpCalendarService.getCalendarById(calendarId);
    ca.setEvents(FPSDao.fpCalendarService.getCalendarEventsById(calendarId));
    ca.setMembers(FPSDao.fpCalendarService.getCalendarMembersById(calendarId));
    return Response.ok("Calendar retrieved succesfully").entity(ca).build();
  }

  @POST
  @Path("/member/{member_id}/update")
  @ApiOperation(
      value = "Update a Member's data",
      notes = "Updates Member data based on the parameters passed",
      response = Event.class)
  @Consumes("application/json")
  public Response updateMember(
      @Context HttpServletRequest request,
      @ApiParam(value = "ID of the Member to be updated", required = true) @PathParam("member_id")
          int memberId,
      @QueryParam("first_name") String firstName,
      @QueryParam("last_name") String lastName,
      @QueryParam("username") String username,
      @QueryParam("dob") String dob,
      @QueryParam("phone") String phone,
      @QueryParam("email") String email) {
    int updated = -1;

    if (firstName != null) {
      updated = FPSDao.fpMemberService.updateMemberFirstName(memberId, firstName);
    }

    if (lastName != null) {
      updated = FPSDao.fpMemberService.updateMemberLastName(memberId, lastName);
    }

    if (dob != null) {
      updated = FPSDao.fpMemberService.updateMemberDOB(memberId, Util.parseDateSimple(dob));
    }

    if (phone != null) {
      updated = FPSDao.fpMemberService.updateMemberPhone(memberId, phone);
    }

    if (email != null) {
      updated = FPSDao.fpMemberService.updateMemberEmail(memberId, email);
    }

    if (username != null) {
      updated = FPSDao.fpMemberService.updateMemberUsername(memberId, username);
    }

    if (updated > 0) {
      return Response.ok("Member update successful").build();
    } else {
      return Response.ok("Member update unsucessful").build();
    }
  }
  //
  //  @POST
  //  @Path("/event/{event_id}/update")
  //  @Produces("application/json")
  //  @Consumes("application/json")
  //  public void updateEvent(
  //      @Context HttpServletRequest request,
  //      @PathParam("event_id") int eventId,
  //      @QueryParam("date") String date,
  //      @QueryParam("type") EventType type,
  //      @QueryParam("description") String launchCount) {}
}
