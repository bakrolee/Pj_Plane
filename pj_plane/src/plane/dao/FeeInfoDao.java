package plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.DBUtil;
import plane.model.FeeInfo;

public class FeeInfoDao {
	public int insert(Connection conn, FeeInfo info, String searchDay, String searchTime) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO testfee (plane_id, fee, searchday, searchtime) VALUES (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, info.getPlaneId());
			pstmt.setInt(2, info.getFee());
			pstmt.setString(3, searchDay);
			pstmt.setString(4, searchTime);

			return pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
		}
	}
}
