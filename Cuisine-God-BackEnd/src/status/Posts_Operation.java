package status;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import util.DAO;


@Path("/post")
public class Posts_Operation {
	
	@POST
	@Path("/create")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertANewPost(String incomingData) {
		String returningString = null;
		JSONObject jobject = new JSONObject();
		JSONArray jarray = new JSONArray();
		DAO dao = new DAO();
		
		try {
			JSONObject data = new JSONObject(incomingData);
			System.out.println("JSON DATA: " + data.toString());
			int http_code = dao.insertIntoPost(data.optString("id"), data.optString("name"),
												data.optString("ingredients"), data.optDouble("timeToCook"),
												data.optString("pictureId"), data.optInt("selfOpinion"),
												data.optString("recipeId"), data.optString("timeStamp"), data.optString("userId"));
			
			if (http_code == 200) {
				jobject.put("HTTP_CODE", "200");
				jobject.put("MSG", "This post has been stored.");
				returningString = jarray.put(jobject).toString();
				
			} else {
				return Response.status(500).entity("Unable to store this member").build();
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		
		
		
		
		return Response.ok(returningString).build();
	}
}
