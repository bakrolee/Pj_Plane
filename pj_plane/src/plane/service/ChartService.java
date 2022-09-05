package plane.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.DBUtil;
import plane.dao.FeeInfoDao;

@WebServlet("/viewchart")
public class ChartService extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		FeeInfoDao dao = null;
		System.out.println("들어왔나?");
		try {
			conn = ConnectionProvider.getConnection();
			
			dao.selectById(conn, Integer.parseInt(req.getParameter("id")));
			System.out.println(Integer.parseInt(req.getParameter("id")));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		
	}
}
