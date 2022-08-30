package plane.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import plane.dao.FeeInfoDao;
import plane.dao.PlaneInfoDao;

public class MakeChart {
	private PlaneInfoDao plaDao;
	private FeeInfoDao feeDao;
	private int id;
	
	// 검색 키워드로 찾기 (id)
	public void searchId(Connection conn, String date, String delo, String arlo, String air, String detime) 
			throws SQLException {
		id = plaDao.findId(conn, date, delo, arlo, air, detime);
	}
	
	// 순서가 머리에서 꼬임...
	public void getFeeInfo() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			feeDao.selectById(conn, id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}
}
