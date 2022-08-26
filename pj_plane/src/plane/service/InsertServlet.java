package plane.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.DBUtil;
import plane.dao.PlaneInfoDao;
import plane.model.PlaneInfo;

@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CollectInfo ci = new CollectInfo();
		PlaneInfoDao plDao = new PlaneInfoDao();
		
		String depLoc = "PUS";
		String arrLoc = "CJU";
		String today = WhatDay.isToday();
		int size = ci.find(depLoc, arrLoc, today);
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			for (int i = 0; i < size; i++) {
				PlaneInfo info = new PlaneInfo(null, depLoc, arrLoc, today, 
						ci.getAirlines().get(i), ci.getDeptimes().get(i), ci.getArrtimes().get(i));
				plDao.insert(conn, info);
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
