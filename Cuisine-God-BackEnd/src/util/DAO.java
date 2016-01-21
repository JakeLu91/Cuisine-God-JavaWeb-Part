package util;
import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

public class DAO {

	private String pin = "910307";
	private String user = "remote";
	private String url = "jdbc:mysql://54.193.102.196:3306/CG";
	private ResultSet rs;
	private Connection connection;
	
	
	
	public int insertIntoPost(String id, String name, String ingredients, double timeToCook, String pictureId, int selfOpinion, String recipeId, String timeStamp, String userId) {
		PreparedStatement update = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pin);
			update = connection.prepareStatement("insert into post(id, name, ingredients, timeToCook, pictureId, selfOpinion, recipeId, timestamp, userId) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			update.setString(1, id);
			update.setString(2, name);
			update.setString(3, ingredients);
			update.setDouble(4, timeToCook);
			update.setString(5, pictureId);
			update.setInt(6, selfOpinion);
			update.setString(7, recipeId);//could be a 0-length string if doesnt have one
			update.setString(8, timeStamp);
			update.setString(9, userId);
			update.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return 500;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return 200;
	}
	
	
	public int insertIntoImage(String id, String path, String userID) {
		PreparedStatement update = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pin);
			update = connection.prepareStatement("insert into image(id, path, userID) values(?, ?, ?)");
			update.setString(1, id);
			update.setString(2, path);
			update.setString(3, userID);
			update.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return 500;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return 200;
	}
	
	public int insertIntoMember(String id, String uname, String pass, int gender, String date) {
		PreparedStatement update = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pin);
			update = connection.prepareStatement("insert into member(id, uname, pass, gender, signDate) values(?, ?, ?, ?, ?)");
			update.setString(1, id);
			update.setString(2, uname);
			update.setString(3, pass);
			update.setInt(4, gender);//only 2 values are valid, 0 is male, 1 is female
			update.setString(5, date);
			update.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return 500;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 200;
	}
	
	public JSONArray getMemberByUname(String u) {
		PreparedStatement query = null;
		JSONArray returnValue = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pin);
			query = connection.prepareStatement("select * from member where uname=?");
			query.setString(1, u);
			rs = query.executeQuery();
			returnValue = ToJSON.toJSONArray(rs);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return returnValue;
		
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
