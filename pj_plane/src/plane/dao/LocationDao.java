package plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBUtil;

public class LocationDao {
	public List<String> selectAll(Connection conn) throws SQLException {
		List<String> code = new ArrayList<String>();
		String sql = "SELECT code FROM location";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				String loc = rs.getString("code");
				code.add(loc);
			}
			return code;
		} 
	}
}
