package status;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

import util.DAO;

@Path("/image")
public class Image_Operation {
	private static final String upload_location = "/Users/jakeLu/Desktop/";
	
	//this mehtod copied from http://examples.javacodegeeks.com/enterprise-java/rest/jersey/jersey-file-upload-example/
	@Path("/store")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response storeANewImage(FormDataMultiPart form) {
		String returnString;
		FormDataBodyPart filePart = form.getField("file");
        ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
        InputStream fileInputStream = filePart.getValueAs(InputStream.class);
        String filePath = upload_location + headerOfFilePart.getFileName();
		
		saveFile(fileInputStream, filePath);
		
		returnString = "File saved to server location : " + filePath;
		
		FormDataBodyPart timeStampPart = form.getField("time");
		FormDataBodyPart datePart = form.getField("date");
		FormDataBodyPart userPart = form.getField("userName");
		System.out.println("" + timeStampPart.getValue() + datePart.getValue() + userPart.getValue());
		
		return Response.ok(returnString).build();
	}
	
	
	//this mehtod copied from http://examples.javacodegeeks.com/enterprise-java/rest/jersey/jersey-file-upload-example/
	private void saveFile(InputStream uploadedIputStream, String serverLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedIputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
				
			}
			out.flush();
			out.close();
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	
}
