package util;

import java.sql.ResultSet;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;



public class ToJSON {
	
	
	public static JSONArray toJSONArray(ResultSet rs) {
		JSONArray json = new JSONArray();

		try {
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				
				for (int i = 1; i < numColumns + 1; i ++) {
					String column_name = rsmd.getColumnName(i);
					if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
						obj.put(column_name, rs.getInt(column_name));
					} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
						obj.put(column_name, rs.getString(column_name));
					} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
						obj.put(column_name, rs.getDate(column_name));
					} else if (rsmd.getColumnType(i) == java.sql.Types.LONGVARCHAR) {
						obj.put(column_name, rs.getString(column_name));
					}
					
				}
				json.put(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return json;
	}
}
