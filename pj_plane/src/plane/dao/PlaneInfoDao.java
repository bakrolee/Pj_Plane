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
		String sql = "INSERT INTO plane (date, dep_loc, arr_loc, airline, dep_time, arr_time) "
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
	
	// id 찾기
	public int findId(Connection conn, String date, String delo, String arlo, 
			String air, String detime) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM plane WHERE date = ? AND dep_loc = ? AND arr_loc = ? "
				+ "AND airline = ? AND dep_time = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, delo);
			pstmt.setString(3, arlo);
			pstmt.setString(4, air);
			pstmt.setString(5, detime);
			System.out.println("<id검색> 날짜: " + date + "출발지: " + delo + "도착지 : " + arlo
					+ "항공사: " + air + "출발시간: " + detime);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("id=" + rs.getInt("id"));
				return rs.getInt("id");
			}
			System.out.println("id=못찾음");
			return -1;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
	}
	
	// 새로운 id 지정해주기
	public int newId(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM plane order by id desc limit 1";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("id=" + rs.getInt("id"));
				return rs.getInt("id");
			}
			System.out.println("id=못찾음");
			return -2;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
		}
	}
	
	// 삭제 (기간 만료시 지워야 됨)
	public int delete(Connection conn, String nowDate, String nowTime) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM plane WHERE (date < ?) OR (date = ? AND dep_time < ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nowDate);
			pstmt.setString(2, nowDate);
			pstmt.setString(3, nowTime);
			
			return pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
		}
	}
}
