package status;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import util.DAO;



@Path("/friends")
public class Friends_Operation {
	
	@Path("/add")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertANewFollowing(String incomingData) {
		String returnString;
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		DAO dao = new DAO();
		
		try {
			JSONObject data = new JSONObject(incomingData);
			int http_code = dao.insertIntoFriends(data.optString("id"), data.optString("following"));
			
			if (http_code == 200) {
				jsonObject.put("http_code", "200");
				jsonObject.put("MSG", "This following has been stored");
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
	
	
	@Path("/getAFollowing")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMember(@QueryParam("id") String id, @QueryParam("following") String following) {
		String returnString = null;
		JSONArray json = new JSONArray();
		DAO dao = new DAO();
		if (id == null || following == null) {
			return Response.status(400).entity("Error: please specify query").build();
		}
		try {
			json = dao.getAFollowing(id, following);
			returnString = json.toString().replaceAll("\\\\", "");
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}
	
	
	@Path("/delete")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(String incomingData) {
		
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		DAO dao = new DAO();
		String id;
		String following;
		System.out.println(incomingData);
		
		try {
			JSONObject data = new JSONObject(incomingData);
			id = data.optString("id");
			following = data.optString("following");
			http_code = dao.deleteFollowing(id, following);
			
			if (http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "This following has been deleted successfully");
				
			} else {
				
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return Response.status(500).entity("Server was not able to process your request").build();
			
		}
		
		return Response.ok(returnString).build();
		
	}
	
	
	
	
	
}
