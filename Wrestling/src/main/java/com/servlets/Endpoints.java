package com.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.models.Event;
import com.models.MatchLogItem;
import com.models.MatchResult;
import com.models.Response;
import com.models.Stat;
import com.models.StatUpdate;
import com.models.TopStat;
import com.models.Wrestler;
import com.owlike.genson.Genson;
import com.sms.Sender;
import com.writers.ManagerIO;

@Path("/Wrestling")
public class Endpoints {
	Genson gen = new Genson();
	ManagerIO IO = new ManagerIO();
	Sender sender = new Sender();

	@POST
	@Path("/AddWrestler")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addWrestler(Wrestler w) {

		Response response = new Response();
		boolean success = false;
		try {
			success = IO.addWrestler(w);
			response.setMessage("Succesfully Added wrestler: " + w.name
					+ " with id=" + w.getId());
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		response.setSuccess(success);
		addStatRow(w);
		return response;
	}

	@POST
	@Path("/SetBulletin")
	@Produces("application/json")
	@Consumes(MediaType.WILDCARD)
	public Response setBulletin(String html) {

		Response response = new Response();
		boolean success = false;
		try {
			success = IO.setBulletin(html);
			response.setMessage("Successfully Updated The Bulletin");
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		response.setSuccess(success);
		return response;
	}

	@GET
	@Path("/GetBulletin")
	@Produces(MediaType.WILDCARD)
	public String getBulletin() {

		try {
			return IO.getBulletin();

		} catch (Exception e) {

		}
		return null;
	}

	@POST
	@Path("/Update")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateWrestler(Wrestler w) {
		Response response = new Response();
		try {
			response.setSuccess(IO.updateWrestler(w));
			response.setMessage("Updated Wrestler Information For " + w.name);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@POST
	@Path("/CreateStatRow")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addStatRow(Wrestler w) {
		Response response = new Response();

		try {
			response.setSuccess(IO.createStatRow(w));
			;
			response.setMessage("Created Stat Row For " + w.name);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@GET
	@Path("/DeleteWrestler")
	@Produces("application/json")
	public Response deleteWrestler(@QueryParam("wrestlerId") int wrestlerId) {
		Response response = new Response();
		boolean success = false;
		try {
			success = IO.deleteWrestler(wrestlerId);
			response.setMessage("Succesfully Removed Wrestler: " + wrestlerId);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		response.setSuccess(success);
		return response;

	}

	@GET
	@Path("/GetTopStats")
	@Produces("application/json")
	public List<TopStat> getTopStats(@QueryParam("teamId") int teamId) {
		ArrayList<String> statsToGet = new ArrayList<String>();
		statsToGet.add("takedown");
		statsToGet.add("escape");
		statsToGet.add("nearfall");
		statsToGet.add("pin");
		statsToGet.add("win");
		statsToGet.add("teampoint");
		statsToGet.add("reversal");
		statsToGet.add("forfeit");
		try {
			return IO.getTopStats(statsToGet, teamId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/GetWrestler")
	@Produces("application/json")
	public Wrestler getWrestler(@QueryParam("wrestlerId") int wrestlerId) {

		try {
			return IO.getWrestler(wrestlerId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GET
	@Path("/GetAll")
	@Produces("application/json")
	public List<Wrestler> getAll(@QueryParam("teamId") int teamId) {

		try {
			if (teamId == 0) {
				return IO.getAllWrestlers();
			} else {
				return IO.getTeam(teamId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/GetStats")
	@Produces("application/json")
	public List<Stat> getStats() {

		try {
			return IO.getStats();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GET
	@Path("/IndividualStats")
	@Produces("application/json")
	public Stat individualStats(@QueryParam("wrestlerId") int wrestlerId) {

		try {
			return IO.getStats(wrestlerId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/UpdateStats")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateStats(List<StatUpdate> stats) {
		Response response = new Response();

		try {
			response.setSuccess(IO.updateStats(stats));
			;
			response.setMessage("Updated Stats");
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@POST
	@Path("/SendSMS")
	@Consumes(MediaType.WILDCARD)
	@Produces("application/json")
	public Response sendSMS(String s) {

		Response response = new Response();
		try {
			response.setSuccess(sender.send(s));
			response.setMessage("Sending SMS to Wrestlers");
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@POST
	@Path("/LogMatch")
	@Consumes("application/json")
	@Produces("application/json")
	public Response LogMatch(List<MatchLogItem> list) {
		System.out.println(gen.serialize(list));
		Response response = new Response();
		try {
			response.setSuccess(IO.LogMatch(list));
			response.setMessage("Successfully Logged Match");
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@GET
	@Path("/GetMatchLogs")
	@Produces("application/json")
	public List<MatchLogItem> GetMatchLogs(@QueryParam("date") String date) {
		if (date == null) {
			try {
				return IO.getLogs();
			} catch (Exception e) {
				System.out.println("Failed Retriving Logged Matches"
						+ e.getMessage());
			}
		} else {
			try {
				return IO.getLog(date);
			} catch (Exception e) {
				System.out.println("Failed Retriving Logged Matches"
						+ e.getMessage());
			}
		}

		return null;
	}

	@GET
	@Path("/GetResult")
	@Produces("application/json")
	public MatchResult getMatchResult(@QueryParam("date") String date) {
		try {
			return IO.getMatchResult(date);
		} catch (Exception e) {
			System.out
					.println("Failed Retriving Match Result" + e.getMessage());
		}
		return null;
	}

	@POST
	@Path("/AddEvent")
	@Consumes("application/json")
	@Produces("application/json")
	public Response AddEvent(Event e) {
		Response response = new Response();
		try {
			response.setSuccess(IO.createEvent(e));
			response.setMessage("Successfully Added Event: " + e.getEventName());
		} catch (Exception e2) {
			response.setMessage(e2.getMessage());
		}
		return response;
	}

	@GET
	@Path("/GetEvents")
	@Produces("application/json")
	public List<Event> GetEvents() {

		try {
			return IO.getEvents();

		} catch (Exception e2) {
			System.out.println("Failed To Retrieve Events:" + e2.getMessage());
		}
		return null;
	}

	@GET
	@Path("/GetCurrentMatch")
	@Produces("application/json")
	public List<MatchLogItem> GetCurrentMatch() {

		try {
			return IO.getCurrentMatch();

		} catch (Exception e2) {
			System.out.println("Failed To Retrieve Current Match:"
					+ e2.getMessage());
		}
		return null;
	}

	@POST
	@Path("/UpdateCurrentMatch")
	@Consumes("application/json")
	@Produces("application/json")
	public Response UpdateCurrentMatch(MatchLogItem item) {
		Response response = new Response();
		try {
			response.setSuccess(IO.updateCurrentMatch(item));
			response.setMessage("Successfully Updated Current Match");
		} catch (Exception e2) {
			response.setMessage(e2.getMessage());
		}
		return response;
	}

	@GET
	@Path("/TruncateCurrentMatch")
	@Produces("application/json")
	public Response deleteEvent() {
		Response response = new Response();
		boolean success = false;
		try {
			success = IO.truncateCurrentMatch();
			response.setMessage("Succesfully Truncated Current Match");
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		response.setSuccess(success);
		return response;

	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/upload")
	public String componentUpload(
			@FormDataParam("executable_file") FormDataBodyPart executable,
			@FormDataParam("supporting_files") List<FormDataBodyPart> supporting)
			throws Exception {

		BodyPartEntity executablePart = (BodyPartEntity) executable.getEntity();
		String executableName = executable.getContentDisposition()
				.getFileName();
		InputStream isExecutable = executable.getValueAs(InputStream.class);

		OutputStream out = null;
		boolean success = true;
		try {

			// results.add("WRITING FILE TO > "+tempDirectory+executableName);
			out = new FileOutputStream(new File("S:/temp/testing/exec/"+executableName));
			int read = 0;
			byte[] bytes = new byte[1024];
			int totalBytes = 0;
			while ((read = isExecutable.read(bytes)) != -1) {
				out.write(bytes, 0, read);
				totalBytes = totalBytes + bytes.length;
			}

		} catch (Exception e) {
			success = false;

		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}

		}

		for (int i = 0; i < supporting.size(); i++) {

			BodyPartEntity supportingPart = (BodyPartEntity) supporting.get(i)
					.getEntity();
			String supportingFileName = supporting.get(i)
					.getContentDisposition().getFileName();
			InputStream is = supporting.get(i).getValueAs(InputStream.class);
			out = null;

			try {
				String targetFile = "S:/temp/testing/sup/"+supportingFileName;

				out = new FileOutputStream(new File(targetFile));
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {

					out.write(bytes, 0, read);
				}

			} catch (Exception e) {

				success = false;
				break;
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}

			}

		}
		return null;

	}
}
