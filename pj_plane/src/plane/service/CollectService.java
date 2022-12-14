package plane.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.DBUtil;
import plane.dao.FeeInfoDao;
import plane.dao.LocationDao;
import plane.dao.PlaneInfoDao;
import plane.model.FeeInfo;
import plane.model.PlaneInfo;
import plane.model.SearchKeyWord;

public class CollectService {
	private LocationDao locDao = new LocationDao();
	private PlaneInfoDao plaDao = new PlaneInfoDao();
	private FeeInfoDao feeDao = new FeeInfoDao();
	private CollectInfo ci;
	private List<String> totalAirCode;
	
	public CollectInfo getCi() {
		return ci;
	}

	public void setCi(CollectInfo ci) {
		this.ci = ci;
	}

	public List<SearchKeyWord> allRoute (String date) {
		totalLoc();
//		int size = totalAirCode.size() * (totalAirCode.size() - 1);
		
		String depLoc;
		String arrLoc;
		List<SearchKeyWord> list = new ArrayList<>();
		
		for (int i = 0; i < totalAirCode.size(); i++) {
			depLoc = totalAirCode.get(i);
			for (int j = 0; j < totalAirCode.size(); j++) {
				if (depLoc != totalAirCode.get(j)) {
					arrLoc = totalAirCode.get(j);
//					oneRoutePlane(depLoc, arrLoc, date);
					list.add(new SearchKeyWord(date, depLoc, arrLoc));
				}
			}
		}
		return list;
	}

	public void totalLoc() {
		Connection conn = null;
		totalAirCode = new ArrayList<>();
		try {
			conn = ConnectionProvider.getConnection();
			totalAirCode = locDao.selectAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	public void oneRouteFee (String depLoc, String arrLoc, String date) {
		ci = new CollectInfo();
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int size = 0;
			try {
				size = ci.find(depLoc, arrLoc, date);
				System.out.println("사이즈: " + size);
			} catch (IOException e) {
				// 커스텀 예외 설정(추후)
				e.printStackTrace();
			}
			
			String searchDay = WhatDay.isToday();
			String searchTime = WhatDay.curTime();

			int id;
			for (int i = 0; i < size; i++) {
				id = findId(conn, date, depLoc, arrLoc, 
						ci.getAirlines().get(i), ci.getDeptimes().get(i));
				// id가 없을 때 처리
				if (id == -1) {
					plaDao.insert(conn, 
							new PlaneInfo(null, depLoc, arrLoc, date, ci.getAirlines().get(i),
									ci.getDeptimes().get(i), ci.getArrtimes().get(i)));
					id = plaDao.newId(conn);
				}
				feeDao.insert(conn, 
						new FeeInfo(id, ci.getFees().get(i)), searchDay, searchTime);
			}
			conn.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DBUtil.rollback(conn);
		} finally {
			DBUtil.close(conn);
		} 
		
	}
	
	private int findId(Connection conn, String date, String depLoc, 
			String arrLoc, String air, String depTime) throws SQLException {
		return plaDao.findId(conn, date, depLoc, arrLoc, air, depTime);
	}

	public void oneRoutePlane (String depLoc, String arrLoc, String date) {
		ci = new CollectInfo();

		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int size = 0;
			try {
				size = ci.find(depLoc, arrLoc, date);
			} catch (IOException e) {
				// 커스텀 예외 설정(추후)
				e.printStackTrace();
			}

			int dupl;
			for (int i = 0; i < size; i++) {
				// 중복체크 더 효율적으로 할 방법 없는지... 나중에 
				dupl = findId(conn, date, depLoc, arrLoc, 
						ci.getAirlines().get(i), ci.getDeptimes().get(i));
				if (dupl != -1) {
					plaDao.insert(conn, 
							new PlaneInfo(null, depLoc, arrLoc, date, ci.getAirlines().get(i),
									ci.getDeptimes().get(i), ci.getArrtimes().get(i)));
				}
			}
			conn.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
			DBUtil.rollback(conn);
		} finally {
			DBUtil.close(conn);
		}
	}
}
