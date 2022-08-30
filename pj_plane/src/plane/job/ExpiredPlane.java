package plane.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import jdbc.ConnectionProvider;
import jdbc.DBUtil;
import plane.dao.PlaneInfoDao;
import plane.service.WhatDay;

@DisallowConcurrentExecution
public class ExpiredPlane implements InterruptableJob {
	private Thread currentThread;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		currentThread = Thread.currentThread();
		PlaneInfoDao dao = new PlaneInfoDao();
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			dao.delete(conn, WhatDay.isToday(), WhatDay.curTime());
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally {
			DBUtil.close(conn);
		}
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		if (currentThread != null) {
			currentThread.interrupt();
		}
	}
}
