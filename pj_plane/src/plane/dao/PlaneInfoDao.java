package plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jdbc.DBUtil;
import plane.model.PlaneInfo;

public class PlaneInfoDao {
	// 삽입
	public int insert(Connection conn, PlaneInfo info) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO test (date, dep_loc, arr_loc, airline, dep_time, arr_time) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getDate());
			pstmt.setString(2, info.getDepLoc());
			pstmt.setString(3, info.getArrLoc());
			pstmt.setString(4, info.getAirLine()); 
			pstmt.setString(5, info.getDepTime());
			pstmt.setString(6, info.getArrTime());
			
			return pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
		}
	}
	
	public int findId(Connection conn, String date, String delo, String arlo, 
			String air, String detime) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM test WHERE date = ? AND dep_loc = ? AND arr_loc = ? "
				+ "AND airline = ? AND dep_time = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, delo);
			pstmt.setString(3, arlo);
			pstmt.setString(4, air);
			pstmt.setString(5, detime);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
			return -1;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
	}
	
	// 삭제 (기간 만료시 지워야 됨)
	public int delete(Connection conn, String nowDate, String nowTime) throws SQLException {
		PreparedStatement pstmt = null;
//		String sql = "DELETE FROM plane WHERE date = ? AND dep_time <= ?";
		String sql = "DELETE FROM test WHERE date = ? AND dep_time <= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nowDate);
			pstmt.setString(2, nowTime);
			
			return pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
		}
	}
}
