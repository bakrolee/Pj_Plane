package plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBUtil;
import plane.model.FeeInfo;

public class FeeInfoDao {
	public int insert(Connection conn, FeeInfo info, String searchDay, String searchTime) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO fee (plane_id, fee, searchday, searchtime) VALUES (?, ?, ?, ?)";
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
	
	// id로 찾기
	public List<FeeInfo> selectById(Connection conn, int id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT fee, searchday, searchtime FROM plane WHERE plane_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			List<FeeInfo> list = new ArrayList<>();
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fee = rs.getInt("fee");
				String searchDay = rs.getString("searchday");
				String searchTime = rs.getString("searchtime");
				list.add(new FeeInfo(fee, searchDay, searchTime));
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
	}
}
