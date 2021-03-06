package status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import util.DAO;

@Path("/member")
public class Member_Operation {
	
	
	@Path("/getMemberByUname")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMember(@QueryParam("uname") String uname) {
		String returnString = null;
		JSONArray json = new JSONArray();
		DAO dao = new DAO();
		if (uname == null) {
			return Response.status(400).entity("Error: please specify query").build();
		}
		try {
			json = dao.getMemberByUname(uname);
			returnString = json.toString().replaceAll("\\\\", "");
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		return Response.ok(returnString).build();
	}
	
	
	
	@Path("/add")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNewMember(String incomingData) {
		String returnString;
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		DAO dao = new DAO();
		
		try {
			JSONObject data = new JSONObject(incomingData);
			int http_code = dao.insertIntoMember(data.optString("id"),
												data.optString("uname"),
												data.optString("pass"),
												data.optInt("gender"),
												data.optString("date"));
			
			if (http_code == 200) {
				jsonObject.put("http_code", "200");
				jsonObject.put("MSG", "This member has been stored");
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to store this member").build();
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		
		
		return Response.ok(returnString).build();
	}
}
