package plane.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.DBUtil;
import plane.dao.FeeInfoDao;
import plane.dao.PlaneInfoDao;
import plane.model.FeeInfo;

public class MakeChart {
	private PlaneInfoDao plaDao;
	private FeeInfoDao feeDao;
	private int id;
	
	// fee정보 반환
	public List<FeeInfo> getFeeInfo(String date, String delo, String arlo, String air, String detime) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			id = plaDao.findId(conn, date, delo, arlo, air, detime);
			
			return feeDao.selectById(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.close(conn);
		}
	}
	
	
	
	
	// 검색 키워드로 찾기 (id) - 보류
	public void searchId(Connection conn, String date, String delo, String arlo, String air, String detime) 
			throws SQLException {
		id = plaDao.findId(conn, date, delo, arlo, air, detime);
	}
}
