package util;
import java.sql.*;

public class DAO {

	private String pin = "910307";
	private String user = "remote";
	private String url = "jdbc:mysql://54.193.102.196:3306/project";
	@SuppressWarnings("unused")
	private ResultSet rs;
	private Connection connection;
	
	public int insertIntoMember(String id, String uname, String pass, int gender) {
		PreparedStatement update = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pin);
			update = connection.prepareStatement("insert into member(id, uname, pass, gender) values(?, ?, ?, ?)");
			update.setString(1, id);
			update.setString(2, uname);
			update.setString(3, pass);
			update.setInt(4, gender);//only 2 values are valid, 0 is male, 1 is female
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
	
}
