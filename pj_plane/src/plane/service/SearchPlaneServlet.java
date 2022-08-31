package plane.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.DBUtil;
import plane.dao.PlaneInfoDao;
import plane.model.PlaneInfo;

@WebServlet("/searchplane")
public class SearchPlaneServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PlaneInfoDao plaDao = new PlaneInfoDao();
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			List<PlaneInfo> list = new ArrayList<PlaneInfo>();
			
			PlaneRequest preq = createPlaneRequest(req);
			list = plaDao.selectByKeywords(conn, preq);
			
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/view/searchresult.jsp").forward(req, resp);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}

	private PlaneRequest createPlaneRequest(HttpServletRequest req) {
		return new PlaneRequest(req.getParameter("date"), 
								req.getParameter("dep_loc"), req.getParameter("arr_loc"));
	}
}
