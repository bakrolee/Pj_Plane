package plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.DBUtil;
import plane.model.FeeInfo;

public class FeeInfoDao {
	public int insert(Connection conn, FeeInfo info) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO testfee (plane_id, fee, searchday, searchtime) VALUES (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, info.getPlaneId());
			pstmt.setInt(2, info.getFee());
			pstmt.setString(3, info.getSearchDay());
			pstmt.setString(4, info.getSearchTime()); 
			
			return pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
		}
	}
}
